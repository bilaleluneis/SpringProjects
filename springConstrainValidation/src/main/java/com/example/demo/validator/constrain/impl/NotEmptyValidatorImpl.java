package com.example.demo.validator.constrain.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.example.demo.validator.constrain.NotEmpty;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class NotEmptyValidatorImpl implements ConstraintValidator<NotEmpty, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !StringUtils.isEmpty(value);
	}

	@Override
	public void initialize(NotEmpty constraintAnnotation) {}

}
