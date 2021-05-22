package com.bhansali.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhansali.entity.Available;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class InventoryController {

	@Autowired
	GeneralService generalService;

	@Autowired
	LoginController loginController;

	public static final String ENTITY = "inventory";
	public static final String AVAILABLE = "available";
	public static final String AVAILABLES = "availables";
	public static final String AVAILABLE_INVENTORY = "Available Inventory";

	@PostMapping("/" + ENTITY + "/" + CONSTANT.HOME)
	public String home(Model model) {
		return ENTITY + "/" + CONSTANT.HOME;
	}

	@PostMapping("/" + ENTITY + "/" + AVAILABLE)
	public String available(Model model, HttpSession session) {

		if (!(loginController.isManager(session, model)))
			return CONSTANT.HOME_PAGE;

		List<Available> availables = generalService.getAvailables();

		model.addAttribute(CONSTANT.TITLE, AVAILABLE_INVENTORY);
		model.addAttribute(AVAILABLES, availables);
		return ENTITY + "/" + AVAILABLE;
	}
}