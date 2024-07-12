package com.example.travelsuite.controller;

import com.example.travelsuite.model.Comment;
import com.example.travelsuite.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/{placeId}")
    public List<Comment> getCommentsByPlaceId(@PathVariable Long placeId) {
        return commentService.getCommentsByPlaceId(placeId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}