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

import com.bhansali.entity.Person;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class PersonController {

	@Autowired
	GeneralService generalService;

	public static final String ENTITY = "person";
	public static final String ENTITIES = "persons";

	public static final String GENDERS = "genders";
	public static final String TITLE = CONSTANT.ADD + " " + ENTITY.toUpperCase();

	public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.CREATE + "/" + ENTITY;
	public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITY;
	public static final String VIEWS = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITIES;

	@GetMapping("/" + CONSTANT.CREATE + "/" + ENTITY)
	public String add(Model model, Person person) {

		if (person == null) {
			person = new Person();
		}

		model.addAttribute(CONSTANT.TITLE, TITLE);
		model.addAttribute(GENDERS, generalService.getGenders());
		model.addAttribute(ENTITY, person);

		return CREATE;
	}

	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@PostMapping("/" + ENTITY)
	public String save(@ModelAttribute("person") @Validated Person person, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute(CONSTANT.TITLE, ENTITY.toUpperCase() + " " + CONSTANT.HAS_ERROR);
			model.addAttribute(GENDERS, generalService.getGenders());
			return CREATE;
		}

		generalService.save(person);
		return view(person, model);
	}

	@PostMapping("/" + CONSTANT.VIEW + "/" + ENTITY)
	public String view(Person person, Model model) {

		if (person == null || model == null)
			return "/";

		model.addAttribute(ENTITY, person);
		return VIEW;
	}

	@GetMapping("/" + ENTITIES)
	public String getPersons(Model model) {

		model.addAttribute(CONSTANT.TITLE, ENTITIES.toUpperCase());
		model.addAttribute(ENTITIES, generalService.getPersons());

		return VIEWS;
	}

	@GetMapping(CONSTANT.delete + "/" + ENTITY)
	public String delete(Model model, Person person) {

		generalService.delete(person);
		return getPersons(model);
	}
}