package com.example.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.example.config.SecondConfig;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

public class SecondDispatcherServlet implements WebApplicationInitializer {

	
	private static final Logger log = Logger.getLogger(SecondDispatcherServlet.class);
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.debug("INIT Second servlet dispatcher");
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SecondConfig.class);
		context.refresh();
		for(String bean : context.getBeanDefinitionNames()) {
			log.debug(bean + " Obtained from Second dispatcher servlet context !");
		}
		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("secondSpringDispatcherServlet", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/second/*");
		
	}

}
