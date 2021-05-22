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

import com.bhansali.entity.Role;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class RoleController {

	@Autowired
	GeneralService generalService;

	public static final String ENTITY = "role";
	public static final String TITLE = CONSTANT.ADD + " " + ENTITY.toUpperCase();

	public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.CREATE + "/" + ENTITY;
	public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" + CONSTANT.VIEW + "/" + ENTITY;

	@GetMapping("/" + CONSTANT.CREATE + "/" + ENTITY)
	public String add(Model model, Role role) {

		if (role == null) {
			role = new Role();
		}

		model.addAttribute(CONSTANT.TITLE, TITLE);
		model.addAttribute(ENTITY, role);
		return CREATE;
	}

	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@PostMapping("/" + ENTITY)
	public String save(@ModelAttribute("role") @Validated Role role, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute(CONSTANT.TITLE, ENTITY + " " + CONSTANT.HAS_ERROR);
			return CREATE;
		}

		generalService.save(role);
		model.addAttribute(CONSTANT.TITLE, CONSTANT.ACKNOWLEDMENT_TITLE);
		model.addAttribute(ENTITY, role);
		return VIEW;
	}

	@GetMapping("/" + CONSTANT.VIEW + "/" + ENTITY)
	public String view(Integer id, Model model) {

		Role role = generalService.getRole(id);
		if (role == null || model == null)
			return CONSTANT.LOGIN;

		model.addAttribute(ENTITY, role);
		return VIEW;
	}
}