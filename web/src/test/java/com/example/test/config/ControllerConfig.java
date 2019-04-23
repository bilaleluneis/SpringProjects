package com.example.test.config;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

public abstract class ControllerConfig {
	
	@Autowired 
	protected WebApplicationContext ctx;
	
	@Autowired 
	protected MockHttpServletRequest request;
	
	@Rule
	public TestName currentTestName;
	
	protected MockMvc mvc;
	protected static MockHttpSession session;
	protected Logger log;
	
	protected ControllerConfig() {
		log = Logger.getLogger(this.getClass().getSimpleName());
		currentTestName = new TestName();
	}
	
	@BeforeClass
	public static void setupOnce() {
		session = new MockHttpSession();
	}
	
	@Before
	public void beforeEachTest() {
		request.setSession(session);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		log.info("--------------------------------------------------------------------");
		log.info("Running @Test " + currentTestName.getMethodName());
		logSessionContent(session);
	}
	
	@After
	public void afterEachTest() {
		request.clearAttributes();
		log.info("@Test " + currentTestName.getMethodName() + " Run Completed!");
		logSessionContent(session);
		log.info("--------------------------------------------------------------------");
	}
	
	@AfterClass
	public static void destroyInstance() {
		session.clearAttributes();
	}
	
	private void logSessionContent(HttpSession session) {
		log.info("Attributes Currently in Session:");
		List<String> attributesList = Collections.list(session.getAttributeNames());
		if(CollectionUtils.isEmpty(attributesList))
			log.info("Session is Currently Empty");
		else
			attributesList	.stream()
							.forEach(attribute -> log.info(attribute + " Attribute Found in Session ..."));
	}

}
