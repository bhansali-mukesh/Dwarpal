package com.bhansali.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhansali.entity.WIPInventory;
import com.bhansali.service.GeneralService;
import com.bhansali.util.CONSTANT;

@Controller
public class WIPController {

	@Autowired
	GeneralService generalService;

	@Autowired
	LoginController loginController;

	public static final String ENTITY = "process";
	public static final String WIP = "wip";
	public static final String WIPs = "WIPs";
	public static final String IN_PROCESS = "Inventory in Process";

	public static final String HOME = CONSTANT.FORMS + "/" + ENTITY + "/" + CONSTANT.HOME;

	// public static final String CREATE = CONSTANT.ENTITY + "/" + ENTITY + "/" +
	// CONSTANT.CREATE + "/" + ENTITY;
	// public static final String VIEW = CONSTANT.ENTITY + "/" + ENTITY + "/" +
	// CONSTANT.VIEW + "/" + ENTITY;

	@PostMapping("/" + ENTITY + "/" + CONSTANT.HOME)
	public String home(Model model, HttpSession session) {

		if (!(loginController.isManager(session, model)))
			return CONSTANT.HOME_PAGE;

		return HOME;
	}

	@PostMapping("/" + ENTITY + "/" + WIP)
	public String available(Model model, HttpSession session) {

		if (!(loginController.isManager(session, model)))
			return CONSTANT.HOME_PAGE;

		List<WIPInventory> wips = generalService.getWIPs();

		model.addAttribute(CONSTANT.TITLE, IN_PROCESS);
		model.addAttribute(WIPs, wips);
		return CONSTANT.INVENTORY + "/" + WIP;
	}
}