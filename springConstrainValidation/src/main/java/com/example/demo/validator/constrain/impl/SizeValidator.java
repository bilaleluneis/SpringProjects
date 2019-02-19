package com.example.demo.validator.constrain.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.validator.constrain.Size;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class SizeValidator implements ConstraintValidator<Size, String>{
	
	private int length;

	@Override
	public void initialize(Size value) {
		this.length = value.length();
	}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.length() >0 && value.length() <= this.length;
	}

}
