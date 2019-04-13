package com.example.first.controller;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.config.FirstConfig;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

@Configuration
@ImportResource("classpath:rootContext-test.xml")
@Import(FirstConfig.class)
class TestConfig{}

//bellow annotations will only work with public classes!
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public abstract class BaseControllerTestConfig {
	
	@Autowired 
	WebApplicationContext ctx;
	
	@Autowired 
	MockHttpSession session;
	
	@Autowired 
	MockHttpServletRequest request;
	
	MockMvc mvc;
	final static Logger log = Logger.getLogger(BaseControllerTestConfig.class);
	
	@BeforeClass
	public static void setupOnce() {}
	
	@Before
	public void setupBeforeEachTest() {
		request.setSession(session);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@After
	public void cleanupAfterEachTest() {
		session.clearAttributes();
	}

}
