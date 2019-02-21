package com.example.demo.validator.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.util.TypeResolutionHelper;
import org.hibernate.validator.spi.time.TimeProvider;
import org.hibernate.validator.spi.valuehandling.ValidatedValueUnwrapper;

public class CustomValidator implements Validator {
	
	private CustomValidatorImpl validator;
	
	public CustomValidator(	ConstraintValidatorFactory constraintValidatorFactory,
							MessageInterpolator messageInterpolator,
							TraversableResolver traversableResolver,
							BeanMetaDataManager beanMetaDataManager,
							ParameterNameProvider parameterNameProvider,
							TimeProvider timeProvider,
							TypeResolutionHelper typeResolutionHelper,
							List<ValidatedValueUnwrapper<?>> validatedValueHandlers,
							ConstraintValidatorManager constraintValidatorManager,
							boolean failFast) {
		
		this.validator = new CustomValidatorImpl(  	constraintValidatorFactory, 
													messageInterpolator, 
													traversableResolver, 
													beanMetaDataManager, 
													parameterNameProvider, 
													timeProvider, 
													typeResolutionHelper, 
													validatedValueHandlers, 
													constraintValidatorManager, 
													failFast );
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
		Set<ConstraintViolation<T>> violations = null;
		try {
			violations = this.validator.customValidate(object, groups);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return violations;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
		return this.validator.validateProperty(object, propertyName, groups);
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value,
			Class<?>... groups) {
		return this.validator.validateValue(beanType, propertyName, value, groups);
	}

	@Override
	public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
		return this.validator.getConstraintsForClass(clazz);
	}

	@Override
	public <T> T unwrap(Class<T> type) {
		return this.validator.unwrap(type);
	}

	@Override
	public ExecutableValidator forExecutables() {
		return this.validator.forExecutables();
	}

}
