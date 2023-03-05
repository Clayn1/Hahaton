package com.pivo.hahatonbe.rest.service;

import com.pivo.hahatonbe.model.dto.UserData;
import com.pivo.hahatonbe.model.entity.Auditory;
import com.pivo.hahatonbe.model.entity.Comment;
import com.pivo.hahatonbe.model.entity.Location;
import com.pivo.hahatonbe.model.entity.User;
import com.pivo.hahatonbe.rest.repository.CommentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    public List<Comment> getCommentsByUserId(Integer id) {
        return commentRepository.findByUserId(id).stream().peek(comment -> comment.setIsSameUser(false)).collect(Collectors.toList());
    }

    public List<Comment> getCommentsByToken(String token) {
        return getCommentsByUserId(userService.getUserByToken(token).getId()).stream()
                .peek(comment -> comment.setIsSameUser(true))
                .collect(Collectors.toList());
    }

    public List<Comment> getCommentsByLocationId(Integer id, String token) {
        UserData userByToken = userService.getUserByToken(token);
        List<Comment> byLocationId = commentRepository.findByLocationId(id);
        return byLocationId.stream()
                .peek(comment -> comment.setIsSameUser(
                        Objects.equals(comment.getUser().getId(), userByToken.getId()))
                ).collect(Collectors.toList());
    }

    public List<Comment> getCommentsByAuditoryId(Integer id, String token) {
        UserData userByToken = userService.getUserByToken(token);
        List<Comment> byAuditoryId = commentRepository.findByAuditoryId(id);
        return byAuditoryId.stream()
                .peek(comment -> comment.setIsSameUser(
                        Objects.equals(comment.getUser().getId(), userByToken.getId()))
                ).collect(Collectors.toList());
    }

    public Comment writeCommentUnderLocation(Comment comment, String token, Integer locationId) {
        comment.setUser(User.builder()
                .id(userService
                        .getUserByToken(token)
                        .getId())
                .build());
        comment.setLocation(Location.builder()
                .id(locationId)
                .build());
        return commentRepository.save(comment);
    }

    public Comment writeCommentUnderAuditory(Comment comment, String token, Integer auditoryId) {
        comment.setUser(User.builder()
                .id(userService
                        .getUserByToken(token)
                        .getId())
                .build());
        comment.setAuditory(Auditory.builder()
                .id(auditoryId)
                .build());
        return commentRepository.save(comment);
    }
}
