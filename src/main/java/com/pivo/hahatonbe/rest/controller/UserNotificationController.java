package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.entity.UserNotification;
import com.pivo.hahatonbe.rest.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userNotifications")
public class UserNotificationController {
    @Autowired
    private UserNotificationService userNotificationService;

    @GetMapping("/user")
    private List<UserNotification> getNotificationsByUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return userNotificationService.getAllByToken(token);
    }

    @PostMapping("/user/{id}")
    private UserNotification sendNotification(@RequestBody UserNotification userNotification, @PathVariable("id") Integer id) {
        return userNotificationService.sendNotificationToUser(userNotification, id);
    }
}
