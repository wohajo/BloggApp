package com.prawda.bloggApp.service;

import com.prawda.bloggApp.domain.Post;

import java.util.List;

public interface PostManager {
    Post addPost(Post post);

    Post findById(String id);

    void updatePost(Post newPost);

    List<Post> getAllPosts();

    void remove(String id);

    List<Post> getAllPostsPaginated(int number);

    List<Post> findByAuthor(String givenAuthorName);

    List<Post> findByTag(String givenTag);

    List<Post> findByWord(String givenWord);

    List<Post> findPosts(String givenAuthorName, String givenTag, String givenWord);

    List<Post> findPostsPaginated(int number, String givenAuthorName, String givenTag, String givenWord);
}
