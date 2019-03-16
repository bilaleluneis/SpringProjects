package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Configuration
@ComponentScan(basePackages="com.example.first.controller")
public class FirstConfig {
	
	@Bean("firstViewResolver")
	public ViewResolver firstViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/first/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

}
