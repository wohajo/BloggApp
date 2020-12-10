package com.prawda.bloggApp.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PostTagsValidator implements ConstraintValidator<PostTags, List<String>> {

    @Override
    public void initialize(PostTags postTags) {
    }

    @Override
    public boolean isValid(List<String> stringList, ConstraintValidatorContext context) {
        return stringList.stream().allMatch(tag ->
                !tag.isEmpty() &&
                        !tag.isBlank() &&
                        tag.length() > 1 &&
                        !tag.matches("[\\W]+"));
    }
}
