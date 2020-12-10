package com.prawda.bloggApp.service;

import com.prawda.bloggApp.domain.Comment;

import java.util.List;

public interface CommentManager {
    Comment addComment(Comment newComment);

    Comment findById(String id);

    void updateComment(Comment newComment);

    void remove(String id);

    List<Comment> getAllComments();

    List<Comment> findByPostId(String postId);

    List<Comment> findByUsername(String username);
}