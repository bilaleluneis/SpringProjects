package com.example.demo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NotNullPropertyValidatorImpl.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullProperty {
    String message() default "Field cannot be NULL !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
