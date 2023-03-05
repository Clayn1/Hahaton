package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.entity.Auditory;
import com.pivo.hahatonbe.model.entity.Location;
import com.pivo.hahatonbe.rest.repository.AuditoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoryService {
    @Autowired
    private AuditoryRepository auditoryRepository;

    public List<Auditory> getAuditoriesByLocationId(Integer locationId) {
        return auditoryRepository.findByLocationId(locationId);
    }

    public Auditory createAuditory(Auditory auditory, Integer locationId) {
        auditory.setLocation(Location.builder().id(locationId).build());
        return auditoryRepository.save(auditory);
    }
}
