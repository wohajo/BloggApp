package com.prawda.bloggApp.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Post {
    private String id;
    private List<Author> authors;
    private List<String> tags;
    private String contents;
}
