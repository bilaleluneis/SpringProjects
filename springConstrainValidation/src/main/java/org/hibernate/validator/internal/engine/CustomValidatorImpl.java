package org.hibernate.validator.internal.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.ValidatorFactoryImpl.ValidatorFactoryScopedContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.hibernate.validator.internal.engine.groups.ValidationOrderGenerator;
import org.hibernate.validator.internal.engine.valueextraction.ValueExtractorManager;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;

import com.example.demo.validator.impl.ConstraintsOrder;


/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class CustomValidatorImpl extends ValidatorImpl {
	
	public CustomValidatorImpl(	ConstraintValidatorFactory constraintValidatorFactory,
								BeanMetaDataManager beanMetaDataManager,
								ValueExtractorManager valueExtractorManager,
								ConstraintValidatorManager constraintValidatorManager,
								ValidationOrderGenerator validationOrderGenerator,
								ValidatorFactoryScopedContext validatorFactoryScopedContext ) {
		
		super(	constraintValidatorFactory, 
				beanMetaDataManager, 
				valueExtractorManager, 
				constraintValidatorManager, 
				validationOrderGenerator, 
				validatorFactoryScopedContext	);
	}
	
	public <T> Set<ConstraintViolation<T>> customValidate(T object, Class<?>[] groups) throws 	IllegalAccessException, IllegalArgumentException, 
																	 							InvocationTargetException, NoSuchMethodException, 
																	 							SecurityException, NoSuchFieldException {
		
		Set<ConstraintViolation<T>> violations = Collections.emptySet();
		Map<String, ConstraintViolation<T>> map = new HashMap<String, ConstraintViolation<T>>();
		
		for (Class<?> group : groups) {
			violations = super.validate(object, group);
			if(group == ConstraintsOrder.Zero.class) { 
				if(violations.isEmpty())
					return violations;
				else 
					violations.clear();
			}
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

