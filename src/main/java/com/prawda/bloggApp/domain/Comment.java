package com.prawda.bloggApp.domain;


import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @CsvBindByName(column = "id", required = true)
    private String id;
    @CsvBindByName(column = "username", required = true)
    private String username;
    @CsvBindByName(column = "id_post", required = true)
    private int postId;
    @CsvBindByName(column = "comment_content", required = true)
    private String contents;
}