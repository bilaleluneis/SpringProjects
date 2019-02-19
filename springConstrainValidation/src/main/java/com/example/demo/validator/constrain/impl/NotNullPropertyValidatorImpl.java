package com.example.demo.validator.constrain.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.validator.constrain.NotNullProperty;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class NotNullPropertyValidatorImpl implements ConstraintValidator<NotNullProperty, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.length() > 0;
	}

	@Override
	public void initialize(NotNullProperty constraintAnnotation) {		
	}

}
