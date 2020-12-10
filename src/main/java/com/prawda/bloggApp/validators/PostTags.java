package com.prawda.bloggApp.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostTagsValidator.class)
public @interface PostTags {

    String message() default "Tag must be longer than 1 character. Only words characters allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
