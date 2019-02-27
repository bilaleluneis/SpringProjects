package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */
@SpringBootApplication
public class SpringConstrainValidationApplication extends SpringBootServletInitializer{

	// extended SpringBootServletInitializer and override this method to allow starting up as servlet
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringConstrainValidationApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringConstrainValidationApplication.class, args);
	}

}

