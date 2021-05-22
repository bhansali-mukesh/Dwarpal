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

import com.bhansali.entity.Organization;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class OragnizationController {

	@Autowired
	GeneralService generalService;

	public static final String ENTITY = "organization";
	public static final String ENTITIES = "organizations";

	public static final String BUSINESSES = "businesses";
	public static final String CATEGORIES = "categories";
	public static final String PERSONS = "persons";
	public static final String SOILS = "soils";

	public static final String TITLE = CONSTANT.ADD + " " + ENTITY.toUpperCase();

	public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.CREATE + "/" + ENTITY;
	public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITY;
	public static final String VIEWS = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITIES;

	@GetMapping("/" + CONSTANT.CREATE + "/" + ENTITY)
	public String add(Model model, Organization organization) {

		model = setModel(model, organization);
		return CREATE;
	}

	@PostMapping("/" + ENTITY)
	public String save(@ModelAttribute("organization") @Validated Organization organization, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model = setModel(model, organization);
			model.addAttribute(CONSTANT.TITLE, ENTITY + " " + CONSTANT.HAS_ERROR);
			return CREATE;
		}

		generalService.save(organization);

		model.addAttribute(CONSTANT.TITLE, CONSTANT.ACKNOWLEDMENT_TITLE);
		model.addAttribute(ENTITY, organization);

		return VIEW;
	}

	@GetMapping("/" + CONSTANT.VIEW + "/" + ENTITY)
	public String view(Integer id, Model model) {

		Organization organization = generalService.getOrganization(id);
		if (organization == null || model == null)
			return CONSTANT.LOGIN;

		model.addAttribute(ENTITY, organization);
		return CONSTANT.VIEW + "/" + ENTITY;
	}

	@GetMapping("/" + ENTITIES)
	public String getOrganizations(Model model) {

		model.addAttribute(CONSTANT.TITLE, ENTITIES.toUpperCase());
		model.addAttribute(ENTITIES, generalService.getOrganizations());

		return VIEWS;
	}

	@GetMapping(CONSTANT.delete + "/" + ENTITY)
	public String delete(Model model, Organization organization) {

		generalService.delete(organization);
		return getOrganizations(model);
	}

	private Model setModel(Model model, Organization organization) {

		if (organization == null) {
			organization = com.bhansali.util.Dummy.ORGANIZATION;
		}

		model.addAttribute(CONSTANT.TITLE, TITLE);
		model.addAttribute(ENTITY, organization);

		model.addAttribute(BUSINESSES, generalService.getBusinesses());
		model.addAttribute(CATEGORIES, generalService.getCategories());
		model.addAttribute(PERSONS, generalService.getPersons());
		model.addAttribute(SOILS, generalService.getSoils());
		model.addAttribute(ENTITIES, generalService.getOrganizations());

		return model;
	}
}