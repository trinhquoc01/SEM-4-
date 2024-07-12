package com.example.travelsuite.service;

import com.example.travelsuite.model.Comment;
import com.example.travelsuite.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public ResponseEntity<?> addComment(Comment comment) {
        commentRepository.save(comment);
        return ResponseEntity.ok("Comment added successfully!");
    }

    public List<Comment> getCommentsByPlaceId(Long placeId) {
        return commentRepository.findByPlaceId(placeId);
    }

    public ResponseEntity<?> deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        commentRepository.deleteById(id);
        return ResponseEntity.ok("Comment deleted successfully!");
    }
}