package com.example.demo;



import java.util.Arrays;
import java.util.stream.Stream;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.one.config.ConfigOne;
import com.one.controller.ControllerOne;
import com.two.config.ConfigTwo;
import com.two.controller.ControllerTwo;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@SpringBootApplication
//@ImportResource("classpath:app-config.xml")
public class ConfigurationApplication extends SpringBootServletInitializer {
	
	private static final Logger log = Logger.getLogger(ConfigurationApplication.class);

	// extended SpringBootServletInitializer and override this method to allow starting up as servlet
	//FIXME: dont think i need this if using @SpringBootApplication
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(new Class<?>[] {ConfigurationApplication.class});
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder()	
										.sources(ConfigurationApplication.class)
										.child(ConfigOne.class)
										.sibling(ConfigTwo.class)
										.run(args);
		//ApplicationContext context = SpringApplication.run(ConfigurationApplication.class, args);
		//Stream<String> beans = Arrays.stream(context.getBeanDefinitionNames());
//		beans.filter(bean -> !bean.startsWith("org.springframework"))
//			 .forEach(bean -> log.info(bean + "bean definition obtained from context!"));
	}
	
	/*@Bean
	public ServletRegistrationBean<DispatcherServlet> dispatcherOne() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		AnnotationConfigWebApplicationContext contextOne = new AnnotationConfigWebApplicationContext();
		contextOne.register(ConfigOne.class);
		dispatcherServlet.setApplicationContext(contextOne);
		ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet, "/one/*");
		servletRegistrationBean.setName("one");
		contextOne.refresh();
		Stream<String> beans = Arrays.stream(contextOne.getBeanDefinitionNames());
		beans.forEach(bean -> log.info(bean + "bean definition obtained from context One!"));
		return servletRegistrationBean;
	}*/
	
	/*@Bean
	public ServletRegistrationBean<DispatcherServlet> dispatcherTwo() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		AnnotationConfigWebApplicationContext contextTwo = new AnnotationConfigWebApplicationContext();
		contextTwo.register(ConfigTwo.class);
		dispatcherServlet.setApplicationContext(contextTwo);
		ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet, "/two/*");
		servletRegistrationBean.setName("two");
		contextTwo.refresh();
		Stream<String> beans = Arrays.stream(contextTwo.getBeanDefinitionNames());
		beans.forEach(bean -> log.info(bean + "bean definition obtained from context Two!"));
		return servletRegistrationBean;
	}*/

}

