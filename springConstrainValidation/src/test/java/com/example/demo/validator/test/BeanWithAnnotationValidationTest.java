package com.example.demo.validator.test;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.demo.bean.BeanWithAnnotationValidation;
import com.example.demo.validator.impl.ConstraintsOrder;
import com.example.demo.validator.provider.impl.CustomValidatorProvider;
import com.example.demo.validator.provider.resolver.impl.CustomValidationProviderResolver;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class BeanWithAnnotationValidationTest {
	
	private static final Logger log = Logger.getLogger(BeanWithAnnotationValidationTest.class);
	private static Validator validator;
	
	@BeforeClass
	public static void setupOnce() {
		
		Configuration<?> config = Validation.byProvider(CustomValidatorProvider.class)
											.providerResolver(new CustomValidationProviderResolver())
											.configure();
		
		ValidatorFactory validatorFactory = config	.messageInterpolator(new ParameterMessageInterpolator())
													.buildValidatorFactory();
		validator = validatorFactory.getValidator();
		
	}
	
	@Test
	public void testAllNotEmpty() {
		
		BeanWithAnnotationValidation bean = new BeanWithAnnotationValidation("name","29","175","165");
		Set<ConstraintViolation<BeanWithAnnotationValidation>> violations;
		try {
			
			violations = validator.validate(bean, ConstraintsOrder.getDefaultConstraints());
			assertTrue(violations.isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testNameisEmpty() {
		BeanWithAnnotationValidation bean = new BeanWithAnnotationValidation("","29","175","165");
		Set<ConstraintViolation<BeanWithAnnotationValidation>> violations;
		try {
			
			violations = validator.validate(bean);
			for(ConstraintViolation<BeanWithAnnotationValidation> violation : violations) {
				log.error(violation.getMessage());
			}
			assertTrue(!violations.isEmpty() && violations.size() == 1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInvalidName() {
		BeanWithAnnotationValidation bean = new BeanWithAnnotationValidation("$duh","30","175","165");
		Set<ConstraintViolation<BeanWithAnnotationValidation>> violations;
		try {
			
			violations = validator.validate(bean);
			for(ConstraintViolation<BeanWithAnnotationValidation> violation : violations) {
				log.error(violation.getMessage());
			}
			assertTrue(!violations.isEmpty() && 
						violations.size() == 1 &&
						violations.stream()	.filter(violation -> violation.getPropertyPath()
											.toString()
											.equals("name"))
											.findAny()
											.orElse(null) != null &&
						violations.stream()	.filter(violation -> violation.getConstraintDescriptor()
											.toString()
											.contains("NoSpecialChars"))
											.findAny()
											.orElse(null) != null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
