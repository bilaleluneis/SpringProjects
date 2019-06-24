package com.example.demo.validator.provider.impl;

import javax.validation.ValidatorFactory;
import javax.validation.spi.ConfigurationState;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.engine.CustomValidatorFactoryImpl;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class CustomValidatorProvider extends HibernateValidator {

	@Override
	public ValidatorFactory buildValidatorFactory(ConfigurationState configurationState) {
		return new CustomValidatorFactoryImpl(configurationState);
	}
	

}
