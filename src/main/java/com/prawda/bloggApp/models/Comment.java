package com.prawda.bloggApp.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    private int id;
    private int authorId;
    private int postId;
    private String contents;
}