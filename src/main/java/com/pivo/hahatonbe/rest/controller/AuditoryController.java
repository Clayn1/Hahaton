package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.entity.Auditory;
import com.pivo.hahatonbe.rest.service.AuditoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditory")
public class AuditoryController {
    @Autowired
    private AuditoryService auditoryService;

    @GetMapping("/location/{id}")
    public List<Auditory> getAuditoriesByLocationId(@PathVariable("id") Integer locationId) {
        return auditoryService.getAuditoriesByLocationId(locationId);
    }

    @PostMapping("/location/{id}")
    public Auditory createAuditory(@PathVariable("id") Integer id, @RequestBody Auditory auditory) {
        return auditoryService.createAuditory(auditory, id);
    }
}
