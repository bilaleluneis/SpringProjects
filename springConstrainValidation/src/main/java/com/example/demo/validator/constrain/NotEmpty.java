package com.example.demo.validator.constrain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.example.demo.validator.constrain.impl.NotEmptyValidatorImpl;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

@Documented
@Constraint(validatedBy = NotEmptyValidatorImpl.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@NotNullProperty // check if value is not null via NotNullPropertyValidatorImpl
@ReportAsSingleViolation // report any errors found as on single violation "Field cannot be Empty"
public @interface NotEmpty {
    String message() default "Field cannot be Empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
