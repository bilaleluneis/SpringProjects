package com.example.demo.validator.constrain.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.validator.constrain.NoSpecialChars;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class NoSpecialCharsValidatorImpl implements ConstraintValidator<NoSpecialChars, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (!value.contains("$"));
	}

	@Override
	public void initialize(NoSpecialChars constraintAnnotation) {}

}
