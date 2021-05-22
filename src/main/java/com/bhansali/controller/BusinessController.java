package com.bhansali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bhansali.entity.Business;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class BusinessController {

	@Autowired
	GeneralService generalService;

	public static final String ENTITY = "business";
	public static final String ENTITIES = "businesses";

	public static final String TITLE = CONSTANT.ADD + " " + ENTITY.toUpperCase();

	public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.CREATE + "/" + ENTITY;
	public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITY;
	public static final String VIEWS = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITIES;

	@GetMapping("/" + CONSTANT.CREATE + "/" + ENTITY)
	public String add(Model model, Business business) {

		if (business == null) {
			business = new Business();
		}

		model.addAttribute(CONSTANT.TITLE, TITLE);
		model.addAttribute(ENTITY, business);

		return CREATE;
	}

	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@PostMapping("/" + ENTITY)
	public String save(@ModelAttribute("business") @Validated Business business, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute(CONSTANT.TITLE, ENTITY + " " + CONSTANT.HAS_ERROR);
			return CREATE;
		}
		generalService.save(business);

		model.addAttribute(CONSTANT.TITLE, CONSTANT.ACKNOWLEDMENT_TITLE);
		model.addAttribute(ENTITY, business);
		return VIEW;
	}

	@GetMapping("/" + CONSTANT.VIEW + "/" + ENTITY)
	public String view(Integer id, Model model) {

		Business business = generalService.getBusiness(id);
		if (business == null || model == null)
			return CONSTANT.LOGIN;

		model.addAttribute(ENTITY, business);
		return VIEW;
	}

	@GetMapping("/" + ENTITIES)
	public String getBusinesses(Model model) {

		model.addAttribute(CONSTANT.TITLE, ENTITIES.toUpperCase());
		model.addAttribute(ENTITIES, generalService.getBusinesses());

		return VIEWS;
	}

	@GetMapping(CONSTANT.delete + "/" + ENTITY)
	public String delete(Model model, Business business) {

		generalService.delete(business);
		return getBusinesses(model);
	}
}