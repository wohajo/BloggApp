package com.prawda.bloggApp.models;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id;
    private List<String> authors;
    private String tags;
    private String contents;
}
