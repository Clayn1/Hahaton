package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.dto.AuthResponse;
import com.pivo.hahatonbe.model.dto.UserDetailsWithId;
import com.pivo.hahatonbe.model.dto.UserLogin;
import com.pivo.hahatonbe.model.dto.UserRegister;
import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.model.enums.Roles;
import com.pivo.hahatonbe.rest.service.JwtUserDetailsService;
import com.pivo.hahatonbe.rest.service.ScheduleService;
import com.pivo.hahatonbe.rest.service.UserService;
import com.pivo.hahatonbe.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserLogin user) {
        AuthResponse authResponse = new AuthResponse();
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            if (auth.isAuthenticated()) {
                UserDetailsWithId userDetails = userDetailsService.loadUserByUsername(user.getEmail());
                String token = "Bearer " + jwtTokenUtil.generateToken(userDetails);
                authResponse.setToken(token);
                authResponse.setIsError(false);
                return ResponseEntity.ok(authResponse);
            } else {
                authResponse.setIsError(true);
                authResponse.setErrorMessage("Unauthorized");
                return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
            }
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            authResponse.setIsError(true);
            authResponse.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(authResponse, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
            authResponse.setIsError(true);
            authResponse.setErrorMessage("Email does not exist");
            return new ResponseEntity<>(authResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> saveUser(@RequestBody UserRegister newUser) {
        AuthResponse authResponse = new AuthResponse();
        newUser.setRole(Roles.ROLE_STUDENT.toString());
        userService.createUser(newUser, authResponse);
        if (!authResponse.getIsError()) {
            UserDetailsWithId userDetails = userDetailsService.loadUserByUsername(newUser.getEmail());
            String token = "Bearer " + jwtTokenUtil.generateToken(userDetails);
            authResponse.setToken(token);
        }
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<AuthResponse> saveTeacher(@RequestBody UserRegister newUser) {
        AuthResponse authResponse = new AuthResponse();
        newUser.setRole(Roles.ROLE_TEACHER.toString());
        User user = userService.createUser(newUser, authResponse);
        scheduleService.createScheduleByUserId(user.getId());
        return ResponseEntity.ok(authResponse);
    }
}
