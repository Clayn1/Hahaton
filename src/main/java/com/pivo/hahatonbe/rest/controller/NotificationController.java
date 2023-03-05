package com.pivo.hahatonbe.rest.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.pivo.hahatonbe.model.dto.Note;
import com.pivo.hahatonbe.rest.service.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/send")
    public String sendNotification(@RequestBody Note note, @RequestParam String token) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, token);
    }

    @PostMapping("/token")
    public void saveToken(@RequestParam("token") String fbToken, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        firebaseMessagingService.addToken(fbToken, token);
    }

    @DeleteMapping("/token")
    public void deleteToken(@RequestParam("token") String fbToken) {
        firebaseMessagingService.removeToken(fbToken);
    }
}
