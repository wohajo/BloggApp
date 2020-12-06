package com.prawda.bloggApp.service;

import com.prawda.bloggApp.domain.Comment;
import java.util.List;

public interface CommentManager {
    void addComment(Comment comment);

    Comment findById(String id);

    void updateComment(Comment comment);

    void remove(String id);

    List<Comment> getAllComments();

    List<Comment> findByPostId(String postId);

    List<Comment> findByUsername(String username);
}