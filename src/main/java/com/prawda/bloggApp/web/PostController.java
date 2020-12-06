package com.prawda.bloggApp.web;


import com.prawda.bloggApp.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("postControllerWeb")
public class PostController {

    private PostManager postManager;

    @Autowired
    public PostController(PostManager postManager) {
        this.postManager = postManager;
    }

    @GetMapping("/posts")
    public String postHome(Model model) {
        model.addAttribute("posts", postManager.getAllPosts());
        return "posts-all";
    }

}
