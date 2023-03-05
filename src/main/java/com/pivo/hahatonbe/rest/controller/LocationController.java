package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.Location;
import com.pivo.hahatonbe.rest.service.LocationService;
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
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/university/{id}")
    public List<Location> getLocationsByUniversityId(@PathVariable("id") Integer id) {
        return locationService.getLocationByUniversityId(id);
    }

    @PostMapping("/university/{id}")
    public Location createLocation(@PathVariable("id") Integer id, @RequestBody Location location) {
        return locationService.createLocation(location, id);
    }

    @PostMapping("/user")
    public Location createLocation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Location location) {
        return locationService.createLocationByToken(location, token);
    }

    @GetMapping("/user")
    public List<Location> getLocationsById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return locationService.getLocationByUserToken(token);
    }
}
