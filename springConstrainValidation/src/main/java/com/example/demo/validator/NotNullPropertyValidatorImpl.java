package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullPropertyValidatorImpl implements ConstraintValidator<NotNullProperty, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null;
	}

}
