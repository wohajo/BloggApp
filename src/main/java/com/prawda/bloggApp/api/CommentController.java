package com.prawda.bloggApp.api;


import com.prawda.bloggApp.domain.Comment;
import com.prawda.bloggApp.service.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class CommentController {

    private CommentManager commentManager;

    @Autowired
    public CommentController(CommentManager commentManager) {
        this.commentManager = commentManager;
    }

    @GetMapping("/api/comments")
    public List<Comment> getAllComments() {
        return commentManager.getAllComments();
    }

    @GetMapping("/api/comments/{id}")
    public Comment getCommentById(@PathVariable String id) {
        return commentManager.findById(id);
    }

    @GetMapping("/api/post/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable String postId) {
        return commentManager.findByPostId(postId);
    }

    @GetMapping("/api/comments/user/{username}")
    public List<Comment> getCommentsByUsername(@PathVariable String username) {
        return commentManager.findByUsername(username);
    }

    @DeleteMapping("/api/comments/{id}")
    public void deleteCommentById(@PathVariable String id) {
        commentManager.remove(id);
    }

    @PutMapping("/api/comments")
    public void updateCommentById(@Valid @RequestBody Comment comment) {
        commentManager.updateComment(comment);
    }

    @PostMapping("/api/comments")
    @ResponseStatus(HttpStatus.CREATED)
    Comment addComment(@Valid @RequestBody Comment newComment) {
        return commentManager.addComment(newComment);
    }
}
