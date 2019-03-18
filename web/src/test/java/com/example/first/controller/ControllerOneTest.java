package com.example.first.controller;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.config.FirstConfig;
import com.example.servlet.FirstDispatcherServlet;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { FirstDispatcherServlet.class, FirstConfig.class})
public class ControllerOneTest {
	
	@Autowired 
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		//new ControllerOne();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testContext(){
		ServletContext servletCtx = this.ctx.getServletContext();
		Assert.assertNotNull(servletCtx);
	    Assert.assertNotNull(ctx.getBean("firstViewResolver"));
	}
	

}
