package com.example.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ValidationProviderResolver;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.example.model.Page;
import com.example.validator.CustomValidationProviderResolver;
import com.example.validator.CustomValidatorProvider;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.example.second.controller")
public class SecondConfig {
	
	private static final Logger log = Logger.getLogger(SecondConfig.class);

	@Bean("secondValidator")
	@DependsOn({"secondMessageBundle"})
	public Validator validator() {
		log.info("Creating Validator bean instance for Second Config ...");
		ValidationProviderResolver resolver = new CustomValidationProviderResolver();
		LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
		validatorFactory.setProviderClass(CustomValidatorProvider.class);
		validatorFactory.setValidationProviderResolver(resolver);
		validatorFactory.setValidationMessageSource(messageSource());
		return validatorFactory;
	}
	
	@Bean("secondMessageBundle")
	public MessageSource messageSource() {
		log.info("Creating Message Source Bundle Bean for Second Config ...");
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	@Bean("secondTilesViewResolver")
	public ViewResolver tilesViewResolver() {
		log.info("Creating Tiles View Resolver for Second Config ...");
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
	@Bean("secondTilesConfigurer")
	public TilesConfigurer tilesConfigurer() {
		log.info("Creating Tiles Configurer bean for Second Config ...");
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("classpath:tiles-def.xml");
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	@Bean("pageMap")
	public Map<Integer, Page> pages(){
		Map<Integer, Page> pageMap = new HashMap<>(0);
		pageMap.put(Integer.valueOf(0), new Page("Weclome to Start Page",Integer.valueOf(0), "welcomePage", "welcome"));
		pageMap.put(Integer.valueOf(1), new Page("Page One in Form", Integer.valueOf(1), "pageOne", "controllerPageOne"));
		pageMap.put(Integer.valueOf(2), new Page("Page Two in Form", Integer.valueOf(2), "pageTwo", "controllerPageTwo"));
		return pageMap;
	}

}
