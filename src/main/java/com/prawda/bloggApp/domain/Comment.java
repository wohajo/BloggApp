package com.prawda.bloggApp.domain;


import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @CsvBindByName(column = "id", required = true)
    private String id;
    @CsvBindByName(column = "username", required = true)
    @Pattern(regexp = "[\\w]+", message = "Username can contain only word characters.")
    @NotNull(message = "The username should not be null.")
    @Pattern(regexp = "[\\w]+", message = "Username can contain only word characters.")
    @Length(min = 3, message = "Username should be longer than 3 characters.")
    private String username;
    @CsvBindByName(column = "id_post", required = true)
    private String postId;
    @CsvBindByName(column = "comment_content", required = true)
    @NotNull(message = "The contents should not be null.")
    @NotEmpty(message = "The contents should not be empty.")
    @Length(min = 3, message = "The field must be at least 3 characters.")
    private String contents;
}