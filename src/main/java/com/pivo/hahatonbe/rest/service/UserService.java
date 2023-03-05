package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.dto.AuthResponse;
import com.pivo.hahatonbe.model.dto.UserData;
import com.pivo.hahatonbe.model.dto.UserRegister;
import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.Schedule;
import com.pivo.hahatonbe.model.entity.University;
import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.model.enums.Roles;
import com.pivo.hahatonbe.rest.repository.UserRepository;
import com.pivo.hahatonbe.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User createUser(UserRegister newUser, AuthResponse authResponse) {
        User user = new User();
        user.setId(newUser.getId());
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setRole(newUser.getRole());
        if (Roles.ROLE_STUDENT.toString().equals(newUser.getRole())) {
            user.setGroup(Group.builder().id(newUser.getGroupId()).build());
        } else if (Roles.ROLE_TEACHER.toString().equals(newUser.getRole())) {
            user.setUniversity(University.builder().id(newUser.getUniversityId()).build());
        }
        user.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        try {
            authResponse.setIsError(false);
            System.out.println(123);
            return userRepository.save(user);
        } catch (Exception e) {
            authResponse.setIsError(true);
            authResponse.setErrorMessage("Email is already used");
        }
        return null;
    }

    public Integer createEmptyUser(String email) {
        User user = new User();
        user.setEmail(email);
        return userRepository.save(user).getId();
    }

    public UserData getUserByEmail(String email) {
        System.out.println(email);
        UserData userByEmail = userRepository.findUserByEmail(email);
        userByEmail.setIsTeacher(userByEmail.getRole().equals(Roles.ROLE_TEACHER.toString()));
        return userByEmail;
    }

    public UserData getUserByToken(String token) {
        String email = jwtTokenUtil.getUsernameFromToken(token);
        UserData userByEmail = getUserByEmail(email);
        userByEmail.setIsTeacher(userByEmail.getRole().equals(Roles.ROLE_TEACHER.toString()));
        return userByEmail;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getFullUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
