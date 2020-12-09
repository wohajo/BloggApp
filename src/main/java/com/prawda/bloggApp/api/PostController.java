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

    @GetMapping("/api/posts/page/{number}")
    public List<Post> getAllPostsPaginated(@PathVariable int number) {
        return postManager.getAllPostsPaginated(number);
    }

    @GetMapping("/api/posts/find/page/{number}")
    public List<Post> getFoundPostsPaginated(
            @PathVariable int number,
            @RequestParam String author,
            @RequestParam String tag,
            @RequestParam String contents) {
        return postManager.findPostsPaginated(number, author, tag, contents);
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

    @GetMapping("/api/posts/author/{authorName}")
    public List<Post> getPostsByAuthorName(@PathVariable String authorName) {
        return postManager.findByAuthor(authorName);
    }

    @GetMapping("/api/posts/tag/{tagName}")
    public List<Post> getPostsByTag(@PathVariable String tagName) {
        return postManager.findByTag(tagName);
    }

    @GetMapping("/api/posts/contents/{word}")
    public List<Post> getPostsByWord(@PathVariable String word) {
        return postManager.findByWord(word);
    }

    @PutMapping("/api/posts")
    public void updatePostById(@RequestBody Post post) {
        postManager.updatePost(post);
    }
}
