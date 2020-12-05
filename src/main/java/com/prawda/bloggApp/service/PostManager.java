package com.prawda.bloggApp.service;

import com.prawda.bloggApp.domain.Post;

import java.util.List;

public interface PostManager {
    void addPost(Post post);

    Post findById(String id);

    List<Post> getAllPosts();

    void remove(String id);

    List<Post> findByAuthor(String authorName);

    List<Post> findByTag(String tag);
}
