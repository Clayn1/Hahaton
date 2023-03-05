package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.entity.University;
import com.pivo.hahatonbe.rest.service.UniversityService;
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
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @GetMapping("")
    public List<University> getUniversitiesByUniversityId() {
        return universityService.getAllUniversities();
    }
    @GetMapping("/{id}")
    public University getUniversityById(@PathVariable("id") Integer id) {
        return universityService.getUniversityById(id);
    }
    @PostMapping("")
    public University createUniversity(@RequestBody University university) {
        return universityService.createUniversity(university);
    }

    @GetMapping("/user")
    public University getUniversityByUserToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return universityService.getUniversityByUserToken(token);
    }
}
