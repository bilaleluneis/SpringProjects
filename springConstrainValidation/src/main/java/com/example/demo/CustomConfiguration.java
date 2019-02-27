package com.example.demo;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.example.demo.validator.provider.impl.CustomValidatorProvider;
import com.example.demo.validator.provider.resolver.impl.CustomValidationProviderResolver;


/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

@EnableWebMvc
@Configuration
public class CustomConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public Validator getValidator() {
		CustomValidationProviderResolver resolver = new CustomValidationProviderResolver();
		ParameterMessageInterpolator messages = new ParameterMessageInterpolator();
		LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
		validatorFactory.setProviderClass(CustomValidatorProvider.class);
		validatorFactory.setValidationProviderResolver(resolver);
		validatorFactory.setMessageInterpolator(messages);
		return validatorFactory;
	}

	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }

}
