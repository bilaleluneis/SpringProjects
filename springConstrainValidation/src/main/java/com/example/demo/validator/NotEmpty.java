package com.example.demo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NotEmptyValidatorImpl.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@NotNullProperty
public @interface NotEmpty {
    String message() default "Field cannot be Empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
