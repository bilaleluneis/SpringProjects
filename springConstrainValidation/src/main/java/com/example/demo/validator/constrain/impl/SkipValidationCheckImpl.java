package com.example.demo.validator.constrain.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.bean.BaseBean;
import com.example.demo.validator.constrain.SkipValidationCheck;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class SkipValidationCheckImpl implements ConstraintValidator<SkipValidationCheck, BaseBean>{
	

	@Override
	public void initialize(SkipValidationCheck annotation) {}

	@Override
	public boolean isValid(BaseBean bean, ConstraintValidatorContext context) {
		return bean.skipValidationCheck();
	}

}

