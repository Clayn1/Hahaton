package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> getEventsByAuditoryId(Integer auditoryId);

    List<Event> getEventsByLocationId(Integer locationId);
}
