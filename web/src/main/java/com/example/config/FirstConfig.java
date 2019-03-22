package com.example.config;

import javax.validation.ValidationProviderResolver;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.example.validator.CustomValidationProviderResolver;
import com.example.validator.CustomValidatorProvider;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Configuration
@ComponentScan(basePackages="com.example.first.controller")
public class FirstConfig {
	
	@Bean
	public Validator firstValidator() {
		ValidationProviderResolver resolver = new CustomValidationProviderResolver();
		LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
		validatorFactory.setProviderClass(CustomValidatorProvider.class);
		validatorFactory.setValidationProviderResolver(resolver);
		validatorFactory.setValidationMessageSource(messageSource());
		return validatorFactory;
	}

	@Bean("firstViewResolver")
	public ViewResolver firstViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/first/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}

}
