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

import com.bhansali.entity.Soil;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class SoilController {

	@Autowired
	GeneralService generalService;

	public static final String ENTITY = "soil";
	public static final String ENTITIES = "soils";

	public static final String TITLE = CONSTANT.ADD + " " + ENTITY.toUpperCase();

	public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.CREATE + "/" + ENTITY;
	public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITY;
	public static final String VIEWS = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITIES;

	@GetMapping("/" + CONSTANT.CREATE + "/" + ENTITY)
	public String add(Model model, Soil soil) {

		if (soil == null) {
			soil = new Soil();
		}

		model.addAttribute(CONSTANT.TITLE, TITLE);
		model.addAttribute(ENTITY, soil);
		return CREATE;
	}

	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@PostMapping("/" + ENTITY)
	public String save(@ModelAttribute("soil") @Validated Soil soil, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute(CONSTANT.TITLE, ENTITY + " " + CONSTANT.HAS_ERROR);
			return CREATE;
		}

		generalService.save(soil);

		model.addAttribute(CONSTANT.TITLE, CONSTANT.ACKNOWLEDMENT_TITLE);
		model.addAttribute(ENTITY, soil);
		return VIEW;
	}

	@GetMapping("/" + CONSTANT.VIEW + "/" + ENTITY)
	public String view(Integer id, Model model) {

		Soil soil = generalService.getSoil(id);
		if (soil == null || model == null)
			return CONSTANT.LOGIN;

		model.addAttribute(ENTITY, soil);
		return VIEW;
	}

	@GetMapping("/" + ENTITIES)
	public String getSoils(Model model) {

		model.addAttribute(CONSTANT.TITLE, ENTITIES.toUpperCase());
		model.addAttribute(ENTITIES, generalService.getSoils());

		return VIEWS;
	}

	@GetMapping(CONSTANT.delete + "/" + ENTITY)
	public String delete(Model model, Soil soil) {

		generalService.delete(soil);
		return getSoils(model);
	}
}