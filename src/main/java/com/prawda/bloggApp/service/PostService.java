package com.prawda.bloggApp.service;


import com.prawda.bloggApp.domain.Post;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
 import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@ImportResource("ManyPostsManyAuthors.xml")
@Service
public class PostService implements PostManager {

    List<Post> postList;

    public PostService(ApplicationContext context) {
        this.postList = new ArrayList<>(context.getBeansOfType(Post.class).values());
    }

    @Override
    public void addPost(Post post) {

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
    public void remove(String id) {

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
