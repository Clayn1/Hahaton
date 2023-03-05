package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.entity.Schedule;
import com.pivo.hahatonbe.rest.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/group/{groupId}")
    public Schedule getScheduleByGroupId(@PathVariable("groupId") Integer groupId) {
        return scheduleService.getScheduleByGroupId(groupId);
    }
    @GetMapping("/user/{id}")
    public Schedule getScheduleByUserId(@PathVariable("id") Integer id) {
        return scheduleService.getScheduleByUserId(id);
    }
    @GetMapping
    public Schedule getScheduleByUserToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return scheduleService.getScheduleByUserToken(token);
    }
    @PostMapping("/group/{groupId}")
    public Schedule createScheduleByGroupId(@PathVariable("groupId") Integer groupId) {
        return scheduleService.createScheduleByGroupId(groupId);
    }
    @PostMapping("/user/{userId}")
    public Schedule createScheduleByUserId(@PathVariable("userId") Integer userId) {
        return scheduleService.createScheduleByUserId(userId);
    }
}
