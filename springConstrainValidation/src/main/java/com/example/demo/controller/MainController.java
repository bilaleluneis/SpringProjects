package com.example.demo.controller;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	
	private Set<ConstraintViolation<BeanWithAnnotationValidation>> violations;

	@GetMapping("/main")
    public String loadFormPage(Model m) {
        m.addAttribute("bean", new BeanWithAnnotationValidation("validName","","",""));
        return "inputPage";
    }
	
	@PostMapping("/main")
    public String submitForm(@ModelAttribute("bean")BeanWithAnnotationValidation bean, BindingResult errors) {
		this.violations = getValidator().validate(bean, One.class, Two.class);
		for(ConstraintViolation<BeanWithAnnotationValidation> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
	        String message = violation.getMessage();
	        FieldError fieldError = new FieldError("bean", propertyPath, message);
	        errors.addError(fieldError);
		}
		if(errors.hasErrors()) {
			return "errorPage";
		}
		return "resultPage";
	}
	
	private Validator getValidator() {
		Configuration<?> config = Validation.byProvider(CustomValidatorProvider.class)
											.providerResolver(new CustomValidationProviderResolver())
											.configure();

		ValidatorFactory validatorFactory = config	.messageInterpolator(new ParameterMessageInterpolator())
													.buildValidatorFactory();
		return validatorFactory.getValidator();
	}
}
