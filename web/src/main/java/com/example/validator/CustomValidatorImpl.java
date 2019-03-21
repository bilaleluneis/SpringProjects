package com.example.validator;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;

import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.util.TypeResolutionHelper;
import org.hibernate.validator.spi.time.TimeProvider;
import org.hibernate.validator.spi.valuehandling.ValidatedValueUnwrapper;


/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class CustomValidatorImpl extends ValidatorImpl {
	
	public CustomValidatorImpl(ConstraintValidatorFactory constraintValidatorFactory,
			MessageInterpolator messageInterpolator,
			TraversableResolver traversableResolver,
			BeanMetaDataManager beanMetaDataManager,
			ParameterNameProvider parameterNameProvider,
			TimeProvider timeProvider,
			TypeResolutionHelper typeResolutionHelper,
			List<ValidatedValueUnwrapper<?>> validatedValueHandlers,
			ConstraintValidatorManager constraintValidatorManager,
			boolean failFast) {
		
		super(  constraintValidatorFactory, 
				messageInterpolator, 
				traversableResolver, 
				beanMetaDataManager, 
				parameterNameProvider, 
				timeProvider, 
				typeResolutionHelper, 
				validatedValueHandlers, 
				constraintValidatorManager, 
				failFast);
	}
	
	public <T> Set<ConstraintViolation<T>> customValidate(T object, Class<?>[] groups) throws 	IllegalAccessException, IllegalArgumentException, 
																	 							InvocationTargetException, NoSuchMethodException, 
																	 							SecurityException, NoSuchFieldException {
		
		Set<ConstraintViolation<T>> violations = Collections.emptySet();
		Map<String, ConstraintViolation<T>> map = new HashMap<String, ConstraintViolation<T>>();
		
		for (Class<?> group : groups) {
			violations = super.validate(object, group);
			for(ConstraintViolation<T> violation : violations) {
				String key = violation.getPropertyPath().toString();
				if(!map.containsKey(key))
					map.put(violation.getPropertyPath().toString(), violation);
			}
		}
		
		violations = new HashSet<>(map.values());
		return violations;
	}
	
	
}

