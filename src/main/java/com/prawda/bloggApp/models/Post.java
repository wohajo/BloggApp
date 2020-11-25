package com.prawda.bloggApp.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class Post {
    private int id;
    private List<Author> authors;
    private List<String> tags;
    private String contents;
}
