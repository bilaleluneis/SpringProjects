package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.bean.BeanWithAnnotationValidation;
import com.example.demo.controller.MainController;

public class MainControllerUnitTest {
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
	}
	
	@Test
	public void testReturnedUrlResponse() throws Exception {
		this.mockMvc.perform(get("/main")).andExpect(view().name("inputPage"));
	}
	
	@Test
	public void testInvalidNameErrorResponse() throws Exception {
		
		final String beanName = "bean";
		final String propertyNameOnBean = "name";
		final String noSpecialCharValid = "NoSpecialChars";
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/main")
				.accept(MediaType.TEXT_HTML)
			    .flashAttr(beanName, new BeanWithAnnotationValidation("$duh")))
			    .andExpect(model().attributeHasFieldErrorCode(beanName, propertyNameOnBean, noSpecialCharValid))
			    .andExpect(model().errorCount(1))
			    .andExpect(view().name("errorPage"))
			    .andExpect(status().isOk())
			    .andDo(print());
		
	}

}
