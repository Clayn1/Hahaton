package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoryRepository extends JpaRepository<Auditory, Integer> {
    List<Auditory> findByLocationId(Integer id);
}
