package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.Location;
import com.pivo.hahatonbe.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> getLocationsByUniversityId(Integer id);

    @Query("select l from locations l join l.university.groups g join g.users u where u.id = :id")
    List<Location> findLocationsByUserId(@Param("id") Integer id);
}
