package com.prawda.bloggApp.api;


import com.prawda.bloggApp.domain.Post;
import com.prawda.bloggApp.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/api/posts")
    public Post addPost(@RequestBody Post post) {
        return postManager.addPost(post);
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePostById(@PathVariable String id) {
        postManager.remove(id);
    }

    @GetMapping("/api/posts/{id}")
    public Post getPostById(@PathVariable String id) {
        return postManager.findById(id);
    }

    @PutMapping("/api/posts")
    public void updatePostById(@RequestBody Post post) {
        postManager.updatePost(post);
    }
}
