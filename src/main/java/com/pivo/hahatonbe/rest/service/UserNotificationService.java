package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.model.entity.UserNotification;
import com.pivo.hahatonbe.rest.repository.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserNotificationService {
    @Autowired
    private UserNotificationRepository userNotificationRepository;
    @Autowired
    private UserService userService;

    public List<UserNotification> getAllByToken(String token) {
        return userNotificationRepository.findAllByUserId(userService.getUserByToken(token).getId());
    }

    public UserNotification sendNotificationToUser(UserNotification userNotification, Integer userId) {
        userNotification.setUser(User.builder().id(userId).build());
        userNotification.setDate(new Date());
        return userNotificationRepository.save(userNotification);
    }

    public void sendNotificationToAll(UserNotification userNotification, List<Integer> userIds) {
        userNotificationRepository.saveAll(userIds.stream().map(id -> UserNotification.builder()
                .user(User.builder().id(id).build())
                .content(userNotification.getContent())
                .subject(userNotification.getSubject())
                .date(userNotification.getDate())
                .build()).collect(Collectors.toList()));
    }
}
