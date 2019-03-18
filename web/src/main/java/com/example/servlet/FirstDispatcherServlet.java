package com.example.servlet;



import javax.servlet.ServletRegistration.Dynamic;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.config.FirstConfig;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Configuration
public class FirstDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private static final Logger log = Logger.getLogger(FirstDispatcherServlet.class);

	@Override
	protected String getServletName() {
		return "firstSpringDispatcherServlet";
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		log.debug("INIT First servlet dispatcher");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {FirstConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/first/*" };
	}

}
