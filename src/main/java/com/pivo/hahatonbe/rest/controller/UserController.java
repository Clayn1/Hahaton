package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.dto.UserData;
import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public UserData getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping
    public UserData getUserByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return userService.getUserByToken(token);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
