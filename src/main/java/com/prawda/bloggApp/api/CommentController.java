package com.prawda.bloggApp.api;


import com.prawda.bloggApp.domain.Comment;
import com.prawda.bloggApp.service.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/api/post/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable String postId) {
        return commentManager.findByPostId(postId);
    }

    @GetMapping("/api/comments/user/{username}")
    public List<Comment> getCommentsByUsername(@PathVariable String username) {
        return commentManager.findByUsername(username);
    }
}
