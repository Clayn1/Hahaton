package com.pivo.hahatonbe.rest.controller;

import com.pivo.hahatonbe.model.entity.Comment;
import com.pivo.hahatonbe.rest.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/user/{id}")
    public List<Comment> getCommentsByUserId(@PathVariable("id") Integer id) {
        return commentService.getCommentsByUserId(id);
    }

    @GetMapping("/user")
    public List<Comment> getCommentsByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return commentService.getCommentsByToken(token);
    }

    @GetMapping("/location/{id}")
    public List<Comment> getCommentsByLocationId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                 @PathVariable("id") Integer id) {
        return commentService.getCommentsByLocationId(id, token);
    }

    @GetMapping("/auditory/{id}")
    public List<Comment> getCommentsByAuditoryId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                 @PathVariable("id") Integer id) {
        return commentService.getCommentsByAuditoryId(id, token);
    }

    @PostMapping("/location/{id}")
    public Comment writeCommentUnderLocation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                             @PathVariable("id") Integer id,
                                             @RequestBody Comment comment) {
        return commentService.writeCommentUnderLocation(comment, token, id);
    }

    @PostMapping("/auditory/{id}")
    public Comment writeCommentUnderAuditory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                             @PathVariable("id") Integer id,
                                             @RequestBody Comment comment) {
        return commentService.writeCommentUnderAuditory(comment, token, id);
    }
}
