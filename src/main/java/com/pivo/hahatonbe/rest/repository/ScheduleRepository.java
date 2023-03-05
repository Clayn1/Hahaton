package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Optional<Schedule> getScheduleByGroupId(Integer groupId);
    @Query("select s from schedule s left join s.group.users u where (s.user.id = :id) or (u.id = :id)")
    Optional<Schedule> getScheduleByUserId(@Param("id") Integer id);
}
