package com.prawda.bloggApp.models;


import com.prawda.bloggApp.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Author {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private UserType userType;
}
