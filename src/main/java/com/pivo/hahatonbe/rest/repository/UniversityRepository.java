package com.pivo.hahatonbe.rest.repository;


import com.pivo.hahatonbe.model.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    @Query("select uni from university uni join uni.groups g join g.users u where u.id = :id")
    University findUniversityByUserId(@Param("id") Integer id);
}
