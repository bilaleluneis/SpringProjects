package com.example.demo.validator;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.demo.bean.BeanWithAnnotationValidation;
import com.example.demo.group.sequence.GroupSequence;
import com.example.demo.group.sequence.One;
import com.example.demo.group.sequence.Two;
import com.example.demo.validator.impl.CustomValidatorImpl;
import com.example.demo.validator.provider.impl.CustomValidatorProvider;
import com.example.demo.validator.provider.resolver.impl.CustomValidationProviderResolver;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class BeanWithAnnotationValidationTest {
	
//	private static final String MESSAGE_BUNDLE = "classpath:errorMessages";
	private static Validator validator;
	
	@BeforeClass
	public static void setupOnce() {
		
		Configuration<?> config = Validation./*byDefaultProvider()*/byProvider(CustomValidatorProvider.class)
											.providerResolver(new CustomValidationProviderResolver())
											.configure();
		
		ValidatorFactory validatorFactory = config.messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory();
		validator = validatorFactory.getValidator();
		
	}
	
	@Test
	public void testNotEmpty() {
		
		BeanWithAnnotationValidation bean = new BeanWithAnnotationValidation("","2999","","50");
		Set<ConstraintViolation<BeanWithAnnotationValidation>> violations;
		try {
			
			violations = ((CustomValidatorImpl)validator).customValidate(bean, new Class<?>[] {One.class, Two.class});
			for(ConstraintViolation<BeanWithAnnotationValidation> violation : violations) {
				System.out.println(violation.getMessage());
			}
			assertTrue(violations.isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
