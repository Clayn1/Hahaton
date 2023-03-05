package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.dto.UserData;
import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.Schedule;
import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.rest.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserService userService;

    public Schedule getScheduleByGroupId(Integer id) {
        Schedule schedule = scheduleRepository.getScheduleByGroupId(id).orElse(Schedule.builder().events(new ArrayList<>()).build());
        schedule.setEvents(
                schedule.getEvents().stream()
                        .peek(event -> event
                                .setDayOfWeek(
                                        new SimpleDateFormat("EE").format(event.getDate())))
                        .collect(Collectors.toList()));
        return schedule;
    }

    public Schedule getScheduleByUserId(Integer id) {
        System.out.println(id);
        Schedule schedule = scheduleRepository.getScheduleByUserId(id).orElse(Schedule.builder().events(new ArrayList<>()).build());
        schedule.setEvents(
                schedule.getEvents().stream()
                        .peek(event -> event
                                .setDayOfWeek(
                                        new SimpleDateFormat("EE").format(event.getDate())))
                        .collect(Collectors.toList()));
        return schedule;
    }

    public Schedule getScheduleByUserToken(String token) {
        UserData userByToken = userService.getUserByToken(token);
        Schedule scheduleByUserId = getScheduleByUserId(userByToken.getId());
        scheduleByUserId.setEvents(
                scheduleByUserId.getEvents().stream()
                        .peek(event -> event
                                .setDayOfWeek(
                                        new SimpleDateFormat("EE").format(event.getDate())))
                        .collect(Collectors.toList()));
        return scheduleByUserId;
    }

    public Schedule createScheduleByGroupId(Integer groupId) {
        return scheduleRepository.save(Schedule.builder()
                .group(Group.builder()
                        .id(groupId)
                        .build())
                .build());
    }

    public Schedule createScheduleByUserId(Integer userId) {
        return scheduleRepository.save(Schedule.builder()
                .user(User.builder()
                        .id(userId)
                        .build())
                .build());
    }
}
