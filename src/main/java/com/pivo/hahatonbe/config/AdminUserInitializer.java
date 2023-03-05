package com.pivo.hahatonbe.config;

import com.pivo.hahatonbe.model.dto.AuthResponse;
import com.pivo.hahatonbe.model.dto.UserRegister;
import com.pivo.hahatonbe.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Value("${spring.security.user.name}")
    private String adminUserName;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Value("${spring.security.user.roles}")
    private String adminRole;

    @Override
    public void run(String... args) {
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail(adminUserName);
        userRegister.setPassword(adminPassword);
        userRegister.setRole(adminRole);
        try {
            userService.createUser(userRegister, new AuthResponse());
        } catch (Exception ignored) {}
    }
}
