package com.prawda.bloggApp.api;


import com.prawda.bloggApp.domain.Post;
import com.prawda.bloggApp.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private PostManager postManager;

    @Autowired
    public PostController(PostManager postManager) {
        this.postManager = postManager;
    }

    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        return postManager.getAllPosts();
    }

    @GetMapping("/api/posts/{id}")
    Post getPostById(@PathVariable String id) {
        return postManager.findById(id);
    }
}
