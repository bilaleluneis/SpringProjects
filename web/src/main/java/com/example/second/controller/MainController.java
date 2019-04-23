package com.example.second.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.model.Page;
import com.example.model.Person;
import com.example.servlet.SecondDispatcherServlet;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

@Controller
@RequestMapping({"/welcome", "/controllerPage*"})
public final class MainController extends BaseController {
	
	private MainController() {
		super();
	}
	
	@PostMapping(params= {"next"})
	View nextPost(	@Validated 
					@ModelAttribute(Person.MODEL) 
					Person person, 
					BindingResult errors,
					RedirectAttributes redirect) {
		log.info("In Next button Post Handler Method ...");
		Page page = getCurrentPage();
		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setExposeModelAttributes(false);
		if(errors.hasErrors()) {
			log.error("Validation Failed on Next Post call before Processing ...");
			redirect.addFlashAttribute(ERRORS_MODEL_KEY, errors);
			rv.setUrl(SecondDispatcherServlet.ROOT_CONTEXT + "/" + page.getUrl());
		}else {
			Integer currentPageId = page.getPageId();
			log.info("Current page ID = " + currentPageId);
			page = pageMap.get(currentPageId + 1);
			log.info("Next button Post Handler Method setting page Id to " + page.getPageId());
			session.setAttribute(Page.MODEL, page);
			log.info("Validation Success.. proceeding with controller processing ...");
			rv.setUrl(SecondDispatcherServlet.ROOT_CONTEXT + "/" + page.getUrl());
		}
		redirect.addFlashAttribute(Person.MODEL, person);
		session.setAttribute(Person.MODEL, person);
		log.info("Next button Post Handler Method redirecting to /" + page.getUrl());
		log.info("Existing Next button Post Handler Method ...");
		return rv;
	}
	
	@PostMapping(params= {"back"})
	View backPost(	@ModelAttribute(Person.MODEL) 
					Person person, 
					BindingResult errors,
					RedirectAttributes redirect) {
		log.info("In Back button Post Handler Method ...");
		Page page = getCurrentPage();
		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setExposeModelAttributes(false);
		Integer currentPageId = page.getPageId();
		log.info("Current page ID = " + currentPageId);
		page = pageMap.get(currentPageId - 1);
		log.info("Back button Post Handler Method setting page Id to " + page.getPageId());
		session.setAttribute(Page.MODEL, page);
		rv.setUrl(SecondDispatcherServlet.ROOT_CONTEXT + "/" + page.getUrl());
		redirect.addFlashAttribute(Person.MODEL, person);
		session.setAttribute(Person.MODEL, person);
		log.info("Back button Post Handler Method redirecting to /" + page.getUrl());
		log.info("Existing Back button Post Handler Method ...");
		return rv;
	}
	
	@PostMapping(params= {"start"})
	View startPost(	@ModelAttribute(Person.MODEL) 
					Person person, 
					BindingResult errors,
					RedirectAttributes redirect, 
					HttpServletRequest request ) {
		log.info("In Start button Post Handler Method ...");
		String urlCurrntlyDisplayed = request.getRequestURL().toString();
		Page page = getCurrentPage();
		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setExposeModelAttributes(false);
		Integer currentPageId = page.getPageId();
		log.info("Current page ID = " + currentPageId);
		if(urlCurrntlyDisplayed.contains("/welcome"))
			page = pageMap.get(1);
		else
			page = pageMap.get(0);
		log.info("Start button Post Handler Method setting page Id to " + page.getPageId());
		session.setAttribute(Page.MODEL, page);
		rv.setUrl(SecondDispatcherServlet.ROOT_CONTEXT + "/" + page.getUrl());
		redirect.addFlashAttribute(Person.MODEL, person);
		session.setAttribute(Person.MODEL, person);
		log.info("Start button Post Handler Method redirecting to /" + page.getUrl());
		log.info("Existing Start button Post Handler Method ...");
		return rv;
	}

}
