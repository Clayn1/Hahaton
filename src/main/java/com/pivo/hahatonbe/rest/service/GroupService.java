package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.dto.UserData;
import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.Schedule;
import com.pivo.hahatonbe.model.entity.University;
import com.pivo.hahatonbe.rest.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
    public Group createGroup(Group group, Integer universityId) {
        University university = new University();
        university.setId(universityId);
        group.setUniversity(university);
        Group savedGroup = groupRepository.save(group);
        Schedule scheduleByGroupId = scheduleService.createScheduleByGroupId(savedGroup.getId());
        savedGroup.setSchedule(scheduleByGroupId);
        return savedGroup;
    }
    public Group getGroupById(Integer id) {
        return groupRepository.findById(id).orElse(null);
    }
    public List<Group> getGroupsByUniversityId(Integer id) {
        return groupRepository.getGroupsByUniversityId(id);
    }

    public Group getGroupByToken(String token) {
        return groupRepository.findGroupByUserId(userService.getUserByToken(token).getId());
    }
}
