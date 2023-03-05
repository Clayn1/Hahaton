package com.pivo.hahatonbe.rest.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.pivo.hahatonbe.model.entity.Event;
import com.pivo.hahatonbe.rest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/schedule/{scheduleId}/location/{locationId}/auditory/{auditoryId}")
    public Event createEvent(@PathVariable("scheduleId") Integer scheduleId,
                             @PathVariable("locationId") Integer locationId,
                             @PathVariable("auditoryId") Integer auditoryId,
                             @RequestBody Event event) {
        return eventService.createEvent(event, scheduleId, locationId, auditoryId);
    }

    @PutMapping
    public Event updateEvent(@RequestBody Event event) throws FirebaseMessagingException {
        return eventService.updateEvent(event);
    }

    @GetMapping("/location/{id}")
    public List<Event> getEventsByLocation(@PathVariable("id") Integer id) {
        return eventService.getEventByLocationId(id);
    }

    @GetMapping("/auditory/{id}")
    public List<Event> getEventsByAuditory(@PathVariable("id") Integer id) {
        return eventService.getEventByAuditoryId(id);
    }
}
