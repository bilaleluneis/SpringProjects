package org.hibernate.validator.internal.engine;

import java.util.Set;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

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

public class CustomValidator implements Validator {
	
	private CustomValidatorImpl validator;
	
	public CustomValidator(	ConstraintValidatorFactory constraintValidatorFactory,
							BeanMetaDataManager beanMetaDataManager,
							ValueExtractorManager valueExtractorManager,
							ConstraintValidatorManager constraintValidatorManager,
							ValidationOrderGenerator validationOrderGenerator,
							ValidatorFactoryScopedContext validatorFactoryScopedContext ) {

		this.validator = new CustomValidatorImpl(	constraintValidatorFactory, 
													beanMetaDataManager, 
													valueExtractorManager, 
													constraintValidatorManager,
													validationOrderGenerator, 
													validatorFactoryScopedContext	);
		
	}
	
	@Override
	public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
		Set<ConstraintViolation<T>> violations = null;
		try {
			boolean useDefaultGroup = (groups == null || groups.length == 0);
			Class<?>[] _groups = useDefaultGroup ? ConstraintsOrder.getDefaultConstraints() : groups;
			violations = this.validator.customValidate(object, _groups);
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
