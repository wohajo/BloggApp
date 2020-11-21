package com.prawda.bloggApp.classes;


import com.prawda.bloggApp.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private UserType userType;
}
