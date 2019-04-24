package com.example.second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
public final class NextController extends BaseController {
	
	private NextController() {
		super();
	}
	
	@PostMapping(value= {"/welcome", "/controllerPage*"}, params= {"next"})
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
		log.info("Exiting Next button Post Handler Method ...");
		return rv;
	}

}
