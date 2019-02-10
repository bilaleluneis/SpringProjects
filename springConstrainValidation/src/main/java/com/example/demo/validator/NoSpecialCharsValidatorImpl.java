package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSpecialCharsValidatorImpl implements ConstraintValidator<NoSpecialChars, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (!value.contains("$"));
	}

}
