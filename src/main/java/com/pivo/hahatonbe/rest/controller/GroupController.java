package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.University;
import com.pivo.hahatonbe.rest.service.GroupService;
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
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/university/{id}")
    public List<Group> getGroupsByUniversityId(@PathVariable("id") Integer id) {
        return groupService.getGroupsByUniversityId(id);
    }
    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable("id") Integer id) {
        return groupService.getGroupById(id);
    }
    @PostMapping("/university/{id}")
    public Group createGroup(@PathVariable("id") Integer id, @RequestBody Group group) {
        return groupService.createGroup(group, id);
    }
    @GetMapping("/user")
    public Group getGroupById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return groupService.getGroupByToken(token);
    }
}
