package com.prawda.bloggApp.domain;


import com.prawda.bloggApp.validators.Authors;
import com.prawda.bloggApp.validators.PostTags;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id;
    @Authors
    @NotNull(message = "The authors should not be null.")
    private List<String> authors;
    @PostTags
    @NotNull(message = "The tags should not be null.")
    private List<String> tags;
    @NotNull(message = "The contents should not be null.")
    @NotEmpty(message = "The contents should not be empty.")
    @Length(min = 3, message = "The field must be at least 3 characters.")
    private String contents;
}
