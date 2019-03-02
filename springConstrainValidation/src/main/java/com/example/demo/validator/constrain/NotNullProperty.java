package com.example.demo.validator.constrain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.validator.constrain.impl.NotNullPropertyValidatorImpl;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

@Documented
@Constraint(validatedBy = NotNullPropertyValidatorImpl.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullProperty {
    String message() default "Field cannot be NULL !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
