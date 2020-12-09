package com.prawda.bloggApp.api;

import com.prawda.bloggApp.service.CommentManager;
import com.prawda.bloggApp.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class StatisticsController {

    private CommentManager commentManager;
    private PostManager postManager;

    @Autowired
    public StatisticsController(CommentManager commentManager, PostManager postManager) {
        this.commentManager = commentManager;
        this.postManager = postManager;
    }

    @GetMapping("/api/statistics/best-users")
    public HashMap<String, Integer> getBestUsersStats() {
        HashMap<String, Integer> commentsByUserCount = new HashMap<String, Integer>();

        this.commentManager.getAllComments().forEach(
                (comment -> {
                    if (commentsByUserCount.containsKey(comment.getUsername())) {
                        commentsByUserCount.put(comment.getUsername(), commentsByUserCount.get(comment.getUsername()) + 1);
                    } else {
                        commentsByUserCount.put(comment.getUsername(), 1);
                    }
                })
        );

        return commentsByUserCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    @GetMapping("/api/statistics/best-posts")
    public HashMap<String, Integer> getBestPostsStats() {
        HashMap<String, Integer> commentsInPostCount = new HashMap<String, Integer>();

        this.commentManager.getAllComments().forEach(
                (comment -> {
                    if (commentsInPostCount.containsKey(comment.getPostId())) {
                        commentsInPostCount.put(comment.getPostId(), commentsInPostCount.get(comment.getPostId()) + 1);
                    } else {
                        commentsInPostCount.put(comment.getPostId(), 1);
                    }
                })
        );

        return commentsInPostCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    @GetMapping("/api/statistics/longest-posts")
    public HashMap<String, Integer> getLongestPost() {
        HashMap<String, Integer> postLengthCount = new HashMap<String, Integer>();

        this.postManager.getAllPosts().forEach(
                (post -> postLengthCount.put(post.getId(), post.getContents().length()))
        );

        return postLengthCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
