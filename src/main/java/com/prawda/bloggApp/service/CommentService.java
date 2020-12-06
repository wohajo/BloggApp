package com.prawda.bloggApp.service;

import com.prawda.bloggApp.domain.Comment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ImportResource("Comments.xml")
@Service
public class CommentService implements CommentManager {

    List<Comment> commentList;

    public CommentService(ApplicationContext applicationContext) {
        this.commentList = new ArrayList<>(applicationContext.getBeansOfType(Comment.class).values());
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public Comment findById(String id) {
        return  this.commentList
                .stream()
                .filter(comment -> id.equals(comment.getId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public void remove(String id) {
        this.commentList = this.commentList
                .stream()
                .filter(comment -> !id.equals(comment.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> getAllComments() {
        return this.commentList;
    }

    @Override
    public List<Comment> findByPostId(String postId) {
        return  this.commentList
                .stream()
                .filter(comment -> postId.equals(comment.getPostId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByUsername(String username) {
        return this.commentList
                .stream()
                .filter(comment -> username.equals(comment.getUsername()))
                .collect(Collectors.toList());
    }
}
