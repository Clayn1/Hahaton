package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> getGroupsByUniversityId(Integer id);
    @Query("SELECT g FROM groups g JOIN g.users u WHERE u.id = :id")
    Group findGroupByUserId(@Param("id") Integer id);

}
