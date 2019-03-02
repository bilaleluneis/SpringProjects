package com.example.demo.controller.test;

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

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since Feb 2019
 */

public class MainControllerUnitTest {
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MainController controller = new MainController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testReturnedUrlResponse() throws Exception {
		this.mockMvc.perform(get("/input")).andExpect(view().name("validateInput"));
	}
	
	@Test
	public void testInvalidNameErrorResponse() throws Exception {
		
		final String beanName = "bean";
		final String propertyNameOnBean = "name";
		final String noSpecialCharValid = "NoSpecialChars";
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/unittest")
				.accept(MediaType.TEXT_HTML)
			    .flashAttr(beanName, new BeanWithAnnotationValidation("$duh","30","175","165")))
				.andExpect(model().attributeHasErrors(beanName))
			    .andExpect(model().errorCount(1))
			    .andExpect(model().attributeHasFieldErrorCode(beanName, propertyNameOnBean, noSpecialCharValid))
			    .andExpect(view().name("validateInput"))
			    .andExpect(status().isOk())
			    .andDo(print());
		
	}

}
