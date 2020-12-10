package com.prawda.bloggApp.api;


import com.prawda.bloggApp.domain.Post;
import com.prawda.bloggApp.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    public List<Post> getAllPostsPaginated(@PathVariable @Min(1) int number) {
        return postManager.getAllPostsPaginated(number);
    }

    @GetMapping("/api/posts/pages")
    public int getAllPostsPaginatedCount() {
        return postManager.getAllPosts().size() / 10;
    }

    @GetMapping("/api/posts/find/all")
    public List<Post> getFoundPosts(
            @RequestParam String author,
            @RequestParam String tag,
            @RequestParam String contents) {
        return postManager.findPosts(author, tag, contents);
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
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@Valid @RequestBody Post post) {
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
    public void updatePostById(@Valid @RequestBody Post post) {
        postManager.updatePost(post);
    }
}
