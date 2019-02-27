package com.example.demo.validator.constrain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.validator.constrain.impl.SkipValidationCheckImpl;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

@Documented
@Constraint(validatedBy = SkipValidationCheckImpl.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipValidationCheck {
    String message() default "";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}