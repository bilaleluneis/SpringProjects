package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidatorImpl implements ConstraintValidator<NotEmpty, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (!value.isEmpty() &&value.length() > 0);
	}

}
