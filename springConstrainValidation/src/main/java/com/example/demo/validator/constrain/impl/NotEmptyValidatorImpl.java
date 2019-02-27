package com.example.demo.validator.constrain.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.validator.constrain.NotEmpty;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class NotEmptyValidatorImpl implements ConstraintValidator<NotEmpty, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (!value.isEmpty() &&value.length() > 0);
	}

	@Override
	public void initialize(NotEmpty constraintAnnotation) {}

}
