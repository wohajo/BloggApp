package com.prawda.bloggApp.service;


import com.prawda.bloggApp.domain.Comment;
import com.prawda.bloggApp.domain.Post;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
 import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ImportResource("ManyPostsManyAuthors.xml")
@Service
public class PostService implements PostManager {

    List<Post> postList;

    public PostService(ApplicationContext context) {
        this.postList = new ArrayList<>(context.getBeansOfType(Post.class).values());
    }

    @Override
    public Post addPost(Post newPost) {
        String newId = UUID.randomUUID().toString();
        newPost.setId(newId);

        this.postList.add(newPost);

        return findById(newId);
    }

    @Override
    public Post findById(String id) {
        return  this.postList
                .stream()
                .filter(post -> id.equals(post.getId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Post> getAllPosts() {
        return postList;
    }

    @Override
    public void updatePost(Post newPost) {
        Post oldPost = findById(newPost.getId());
        int index = this.postList.indexOf(oldPost);
        this.postList.set(index, newPost);
    }

    @Override
    public void remove(String id) {
        this.postList = this.postList
                .stream()
                .filter(post -> !id.equals(post.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByAuthor(String authorName) {
        return null;
    }

    @Override
    public List<Post> findByTag(String tag) {
        return null;
    }
}
