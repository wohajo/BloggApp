package com.prawda.bloggApp.api;

import com.prawda.bloggApp.domain.StatsObject;
import com.prawda.bloggApp.service.CommentManager;
import com.prawda.bloggApp.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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
    public List<StatsObject> getBestUsersStats() {
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

        List<StatsObject> sorted = getSortedStats(commentsByUserCount);

        return sorted.subList(Math.max(sorted.size() - 3, 0), sorted.size());
    }

    @GetMapping("/api/statistics/best-posts")
    public List<StatsObject> getBestPostsStats() {
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

        List<StatsObject> sorted = getSortedStats(commentsInPostCount);

        return sorted.subList(Math.max(sorted.size() - 3, 0), sorted.size());
    }

    @GetMapping("/api/statistics/longest-posts")
    public List<StatsObject> getLongestPost() {
        HashMap<String, Integer> postLengthCount = new HashMap<String, Integer>();

        this.postManager.getAllPosts().forEach(
                (post -> postLengthCount.put(post.getId(), post.getContents().length()))
        );

        List<StatsObject> sorted = getSortedStats(postLengthCount);

        return sorted.subList(Math.max(sorted.size() - 3, 0), sorted.size());
    }

    private List<StatsObject> getSortedStats(HashMap<String, Integer> commentsInPostCount) {
        HashMap<String, Integer> commentsInPostCountSorted = commentsInPostCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<StatsObject> statsList = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : commentsInPostCountSorted.entrySet()) {
            statsList.add(new StatsObject(entry.getKey(), entry.getValue()));
        }

        return statsList;
    }
}
