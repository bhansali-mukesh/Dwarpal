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

import com.bhansali.entity.Type;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class TypeController {

	@Autowired
	GeneralService generalService;

	public static final String ENTITY = "type";
	public static final String ENTITIES = "types";

	public static final String TITLE = CONSTANT.ADD + " " + ENTITY.toUpperCase();

	public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.CREATE + "/" + ENTITY;
	public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITY;
	public static final String VIEWS = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITIES;

	@GetMapping("/" + CONSTANT.CREATE + "/" + ENTITY)
	public String add(Model model, Type type) {

		if (type == null) {
			type = new Type();
		}

		model.addAttribute(CONSTANT.TITLE, TITLE);
		model.addAttribute(ENTITY, type);

		return CREATE;
	}

	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@PostMapping("/" + ENTITY)
	public String save(@ModelAttribute("type") @Validated Type type, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute(CONSTANT.TITLE, ENTITY + " " + CONSTANT.HAS_ERROR);
			return CREATE;
		}

		generalService.save(type);

		model.addAttribute(CONSTANT.TITLE, CONSTANT.ACKNOWLEDMENT_TITLE);
		model.addAttribute(ENTITY, type);
		return VIEW;
	}

	@GetMapping("/" + CONSTANT.VIEW + "/" + ENTITY)
	public String view(Integer id, Model model) {

		Type type = generalService.getType(id);
		if (type == null || model == null)
			return CONSTANT.LOGIN;

		model.addAttribute(ENTITY, type);
		return VIEW;
	}

	@GetMapping("/" + ENTITIES)
	public String getTypes(Model model) {

		model.addAttribute(CONSTANT.TITLE, ENTITIES.toUpperCase());
		model.addAttribute(ENTITIES, generalService.getTypes());

		return VIEWS;
	}

	@GetMapping(CONSTANT.delete + "/" + ENTITY)
	public String delete(Model model, Type type) {

		generalService.delete(type);
		return getTypes(model);
	}
}