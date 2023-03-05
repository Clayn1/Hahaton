package com.pivo.hahatonbe.rest.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.pivo.hahatonbe.model.dto.Note;
import com.pivo.hahatonbe.model.entity.Auditory;
import com.pivo.hahatonbe.model.entity.Event;
import com.pivo.hahatonbe.model.entity.FirebaseToken;
import com.pivo.hahatonbe.model.entity.Location;
import com.pivo.hahatonbe.model.entity.Schedule;
import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.model.entity.UserNotification;
import com.pivo.hahatonbe.rest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    @Autowired
    private UserNotificationService userNotificationService;

    public Event createEvent(Event event, Integer scheduleId, Integer locationId, Integer auditoryId) {
        event.setLocation(Location.builder().id(locationId).build());
        event.setSchedule(Schedule.builder().id(scheduleId).build());
        event.setAuditory(Auditory.builder().id(auditoryId).build());
        return eventRepository.save(event);
    }

    public Event getEventById(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        eventOptional.ifPresent(eventPresent -> eventPresent.setDayOfWeek(new SimpleDateFormat("EE").format(eventPresent.getDate())));
        return eventOptional.orElse(null);
    }

    public Event updateEvent(Event event) throws FirebaseMessagingException {
        Event eventById = getEventById(event.getId());
        String subject = "Event has been updated";
        String content = "Event: ${:1} on ${:2} has been changed, please check it's new status"
                .replace("${:1}", eventById.getName())
                .replace("${:2}", dateMap(eventById.getDate()));
        eventById.setDate(event.getDate());
        eventById.setAuditory(event.getAuditory());
        eventById.setLocation(event.getLocation());
        List<User> users = eventById.getSchedule().getGroup().getUsers();
        List<FirebaseToken> firebaseTokens = new ArrayList<>();
        users.forEach(user -> firebaseTokens.addAll(user.getTokens()));
        userNotificationService.sendNotificationToAll(
                UserNotification.builder()
                        .subject(subject)
                        .content(content)
                        .date(new Date())
                        .build(),
                users.stream().map(User::getId).collect(Collectors.toList())
        );
        firebaseMessagingService.sendNotificationToAll(
                Note.builder()
                        .subject(subject)
                        .content(content)
                        .build(), firebaseTokens);
        return eventRepository.save(eventById);
    }

    public List<Event> getEventByLocationId(Integer locationId) {
        return eventRepository.getEventsByLocationId(locationId).stream()
                .peek(event -> event
                        .setDayOfWeek(
                                new SimpleDateFormat("EE").format(event.getDate())))
                .collect(Collectors.toList());
    }

    public List<Event> getEventByAuditoryId(Integer auditoryId) {
        return eventRepository.getEventsByAuditoryId(auditoryId).stream()
                .peek(event -> event
                        .setDayOfWeek(
                                new SimpleDateFormat("EE").format(event.getDate())))
                .collect(Collectors.toList());
    }

    private String dateMap(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM hh:mm");
        return dateFormat.format(timestamp);
    }
}
