package com.example.demo.controller;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.BeanWithAnnotationValidation;
import com.example.demo.group.sequence.One;
import com.example.demo.group.sequence.Two;
import com.example.demo.validator.provider.impl.CustomValidatorProvider;
import com.example.demo.validator.provider.resolver.impl.CustomValidationProviderResolver;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

@Controller
public class MainController {
		
	@GetMapping("/input")
    public String loadForm(Model m) {
        m.addAttribute("bean", new BeanWithAnnotationValidation("validName","","",""));
        return "validateInput";
    }
	
	@PostMapping("/input")
    public String submitForm(	@Validated 
    							@ModelAttribute("bean")
    							BeanWithAnnotationValidation bean, 
    							BindingResult errors) {
		//this.validate(bean, errors);
		if(errors.hasErrors()) {

			return "validateInput";
		}
		return "validateInput"; //redirect to current page
	}
	
	private void validate(BeanWithAnnotationValidation bean,  BindingResult errors) {
		
		Set<ConstraintViolation<BeanWithAnnotationValidation>> violations = null;
		violations = getValidator().validate(bean, One.class, Two.class);
		for(ConstraintViolation<BeanWithAnnotationValidation> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
	        String message = violation.getMessage();
	        FieldError fieldError = new FieldError("bean", propertyPath, message);
	        errors.addError(fieldError);
		}
		
	}
	
	private Validator getValidator() {
		CustomValidationProviderResolver resolver = new CustomValidationProviderResolver();
		Configuration<?> config = Validation.byProvider(CustomValidatorProvider.class)
											.providerResolver(resolver)
											.configure();

		ParameterMessageInterpolator messages = new ParameterMessageInterpolator();
		ValidatorFactory validatorFactory = config	.messageInterpolator(messages)
													.buildValidatorFactory();
		return validatorFactory.getValidator();
	}
	
}
