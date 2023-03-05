package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.dto.UserData;
import com.pivo.hahatonbe.model.entity.Location;
import com.pivo.hahatonbe.model.entity.University;
import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.rest.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UniversityService universityService;

    public Location createLocation(Location location, Integer universityId) {
        location.setUniversity(University.builder().id(universityId).build());
        return locationRepository.save(location);
    }

    public List<Location> getLocationByUniversityId(Integer universityId) {
        return locationRepository.getLocationsByUniversityId(universityId);
    }

    public List<Location> getLocationByUserToken(String token) {
        return locationRepository.findLocationsByUserId(userService.getUserByToken(token).getId());
    }

    public Location createLocationByToken(Location location, String token) {
        location.setUniversity(universityService.getUniversityByUserToken(token));
        return locationRepository.save(location);
    }


//    public Location createLocationByToken(Location location, String token) {
//        UserData userByToken = userService.getUserByToken(token);
//        location.setUniversity(University.builder()
//                .adminUser(User.builder()
//                        .id(userByToken.getId())
//                        .build())
//                .build());
//        return locationRepository.save(location);
//    }
}
