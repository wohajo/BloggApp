package com.prawda.bloggApp.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorsValidator.class)
public @interface Authors {

    String message() default "Authors have to have capitalized first letters and be separated by comma";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}