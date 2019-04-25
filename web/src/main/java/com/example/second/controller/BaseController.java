package com.example.second.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ViewResolver;

import com.example.model.Page;
import com.example.model.Person;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

public abstract class BaseController {
	
	static final String ERRORS_MODEL_KEY = BindingResult.MODEL_KEY_PREFIX + Person.MODEL;
	static final String CONTROLLER_KEY = "controller";
	Logger log;
	
	BaseController(){
		log = Logger.getLogger(this.getClass());
		log.info("Instance of " + this.getClass().getSimpleName() + " Created!");
	}
	
	@Autowired
	@Qualifier("secondValidator")
	Validator validator;
	
	@Autowired
	@Qualifier("secondTilesViewResolver")
	ViewResolver viewResolver;
	
	@Autowired
	@Qualifier("pageMap")
	Map<Integer, Page> pageMap;
	
	@Autowired
	HttpSession session;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@ModelAttribute(Person.MODEL)
	private Person getPerson(Model model) {
		log.info("inside getPerson()");
		Person person = (Person)model.asMap().get(Person.MODEL);
		
		if(person == null) {
			log.info("person Obtained from Model is empty.. attempt to get from session");
			person = (Person)session.getAttribute(Person.MODEL);
		}else
			log.info("person Sucessfully Obtained from Model with name property = " + person.getName());
		
		if(person ==  null) {
			log.info("person Obtained from session is empty.. creating new Person()");
			person = new Person();
		}else
			log.info("person Sucessfully Obtained from session with name property = " + person.getName());
		
		model.addAttribute(Person.MODEL, person);
		session.setAttribute(Person.MODEL, person);
		log.info("exiting getPerson()");
		return person;
	}
	
	Page getCurrentPage() {
		Page currentPage = (Page)session.getAttribute(Page.MODEL);
		if(currentPage == null)
			currentPage = pageMap.get(0);
		return currentPage;
	}
	
}
