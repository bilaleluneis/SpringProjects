package com.example.demo.validator.constrain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.validator.constrain.impl.NoSpecialCharsValidatorImpl;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

@Documented
@Constraint(validatedBy = NoSpecialCharsValidatorImpl.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpecialChars {
    String message() default "Field cannot contain special chars !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
