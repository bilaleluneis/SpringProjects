package com.example.first.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.Person;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since March 2019
 */

public class ControllerOneTest extends BaseControllerTestConfig {
	
	private static final String BEAN = "person";
	
	@Test
	public void testContext(){
		ServletContext servletCtx = ctx.getServletContext();
		Assert.assertNotNull(servletCtx);
		Assert.assertNotNull(ctx.getBean("personBean")); // get from rootContext-test.xml
	    Assert.assertNotNull(ctx.getBean("firstViewResolver")); //get from FirstConfig.java
	}
	
	@Test
	public void testPostForm() throws Exception {
		mvc	.perform(MockMvcRequestBuilders.post("/welcome")
			.accept(MediaType.TEXT_HTML)
			.flashAttr(BEAN, new Person("",10)))
			.andExpect(model().attributeHasErrors(BEAN))
			.andExpect(model().errorCount(1))
			.andExpect(model().attributeHasFieldErrorCode(BEAN, "name", "NotBlank"))
			.andExpect(view().name("error"))
			.andExpect(status().isOk())
			.andDo(print());
	}
	

}
