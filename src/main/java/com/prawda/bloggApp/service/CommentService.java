package com.prawda.bloggApp.service;

import com.prawda.bloggApp.domain.Comment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ImportResource("Comments.xml")
@Service
public class CommentService implements CommentManager {

    List<Comment> commentList;

    public CommentService(ApplicationContext applicationContext) {
        this.commentList = new ArrayList<>(applicationContext.getBeansOfType(Comment.class).values());
    }

    @Override
    public Comment addComment(Comment newComment) {
        String newId = UUID.randomUUID().toString();
        newComment.setId(newId);

        this.commentList.add(newComment);

        return findById(newId);
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
    public void updateComment(Comment newComment) {
        Comment oldComment = findById(newComment.getId());
        int index = this.commentList.indexOf(oldComment);
        this.commentList.set(index, newComment);
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
