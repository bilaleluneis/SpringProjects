package com.example.demo.validator.impl;

import static org.hibernate.validator.internal.util.logging.Messages.MESSAGES;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import org.hibernate.validator.internal.engine.ValidationContext;
import org.hibernate.validator.internal.engine.ValidationContext.ValidationContextBuilder;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.hibernate.validator.internal.engine.ValueContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.hibernate.validator.internal.engine.groups.DefaultValidationOrder;
import org.hibernate.validator.internal.engine.groups.Group;
import org.hibernate.validator.internal.engine.groups.ValidationOrder;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.util.Contracts;
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
	
	
	//@SuppressWarnings("unchecked") //added to deal with last return statement from reflection
	public <T> Set<ConstraintViolation<T>> customValidate(T object, Class<?>[] groups) throws 	IllegalAccessException, IllegalArgumentException, 
																	 							InvocationTargetException, NoSuchMethodException, 
																	 							SecurityException, NoSuchFieldException {
		
		Set<ConstraintViolation<T>> violations = Collections.emptySet();
		Map<String, ConstraintViolation<T>> map = new HashMap<String, ConstraintViolation<T>>();
		
		for (Class<?> clazz : groups) {
			violations = super.validate(object, clazz);
			for(ConstraintViolation<T> violation : violations) {
				String key = violation.getPropertyPath().toString();
				if(!map.containsKey(key))
					map.put(violation.getPropertyPath().toString(), violation);
			}
		}
		
		violations = new HashSet<>(map.values());
		return violations;
		/*Contracts.assertNotNull(object, MESSAGES.validatedObjectMustNotBeNull());
		Field beanMetaDataManagerField = ValidatorImpl.class.getDeclaredField("beanMetaDataManager");
		beanMetaDataManagerField.setAccessible(true);
		BeanMetaDataManager beanMetaDataManager = (BeanMetaDataManager)beanMetaDataManagerField.get(this);

		if (!beanMetaDataManager.isConstrained(object.getClass())) {
			return Collections.emptySet();
		}

		ValidationOrder validationOrder = new DefaultValidationOrder();
		((DefaultValidationOrder) validationOrder).insertGroup( Group.DEFAULT_GROUP );
		
		Method getValidatorContext = getClass().getSuperclass()
											   .getDeclaredMethod("getValidationContext", new Class<?>[]{});
        getValidatorContext.setAccessible(true);
		
		ValidationContext<T> validationContext = ((ValidationContextBuilder)getValidatorContext.invoke(this, (Object[])null))
																							   .forValidate(object);

		ValueContext<?, Object> valueContext = ValueContext.getLocalExecutionContext(object,
				beanMetaDataManager.getBeanMetaData(object.getClass()), PathImpl.createRootPath());
		
		Method validateInContext = getClass().getSuperclass()
				   							 .getDeclaredMethod("validateInContext", new Class<?>[]{ValueContext.class, 
				   								 													ValidationContext.class, 
				   								 													ValidationOrder.class});
		validateInContext.setAccessible(true);
		Set<ConstraintViolation<T>> violations = (Set<ConstraintViolation<T>>) validateInContext.invoke(this, new Object[] {valueContext, validationContext, validationOrder}); 
		
		return violations; */
	}
	
	
}

