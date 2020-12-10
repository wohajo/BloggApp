package com.prawda.bloggApp.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AuthorsValidator implements ConstraintValidator<Authors, List<String>> {

    @Override
    public void initialize(Authors authors) {
    }

    @Override
    public boolean isValid(List<String> authors, ConstraintValidatorContext context) {
        return authors.stream().allMatch(author ->
                author.matches("[A-Z][a-z]+ [A-Z][a-z]+"));
    }

}
