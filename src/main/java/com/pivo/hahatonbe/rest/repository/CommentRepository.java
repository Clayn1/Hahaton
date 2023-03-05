package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByUserId(Integer userId);
    List<Comment> findByLocationId(Integer locationId);
    List<Comment> findByAuditoryId(Integer auditoryId);
}
