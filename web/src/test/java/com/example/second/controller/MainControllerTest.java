package com.example.second.controller;

import javax.servlet.ServletContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static com.example.second.controller.BaseController.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.Person;
import com.example.servlet.SecondDispatcherServlet;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public final class MainControllerTest extends BaseControllerTest{
	
	public MainControllerTest(){
		super();
	}
	
	@Test
	public void test_0_verifyContext(){
		ServletContext servletCtx = ctx.getServletContext();
		assertNotNull(servletCtx);
		assertNotNull(ctx.getBean("personBean")); // get from rootContext-test.xml
	    assertNotNull(ctx.getBean("secondTilesViewResolver")); //get from SecondConfig.java
	}
	
	@Test
	public void test_1_get_welcome_url() throws Exception {
		mvc	.perform(MockMvcRequestBuilders.get("/welcome")
			.session(session))
			.andExpect(handler().handlerType(MainController.class))
			.andExpect(handler().methodName("loadPage"))
			.andExpect(model().size(2))
			.andExpect(model().attributeExists(CONTROLLER_KEY, Person.MODEL))
			.andExpect(model().hasNoErrors())
			.andExpect(model().errorCount(0))
			.andExpect(view().name("welcomePage"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void test_2_click_begin_wizard() throws Exception {
		mvc .perform(MockMvcRequestBuilders.post("/welcome")
			.session(session)
			.param("start", "start"))
			.andExpect(status().is3xxRedirection())
			.andExpect(status().isFound())
			.andExpect(redirectedUrl(SecondDispatcherServlet.ROOT_CONTEXT+"/controllerPageOne"))
			.andDo(print());
	}
	
	@Test
	public void test_3_input_valid_name_and_click_next() throws Exception {
		mvc .perform(MockMvcRequestBuilders.post("/controllerPageOne")
			.session(session)
			.param("next", "next"))
			.andExpect(status().is3xxRedirection())
			.andDo(print());
	}

}
