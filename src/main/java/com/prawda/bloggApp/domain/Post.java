package com.prawda.bloggApp.domain;


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
    private List<String> tags;
    private String contents;
}
