package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.entity.University;
import com.pivo.hahatonbe.rest.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UserService userService;

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }
    public University getUniversityById(Integer id) {
        return universityRepository.findById(id).orElse(null);
    }

    public University getUniversityByUserToken(String token) {
        return universityRepository.findUniversityByUserId(userService.getUserByToken(token).getId());
    }
}
