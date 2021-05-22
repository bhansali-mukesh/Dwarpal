package com.bhansali.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.bhansali.util.CONSTANT;

@Controller
public class HomeController {

	public static final String ENTITY = "home";

	@GetMapping("/")
	public RedirectView defaultPage() {
		return new RedirectView("/" + ENTITY + "/" + CONSTANT.CREATE);
	}

	@GetMapping("/" + ENTITY + "/" + CONSTANT.CREATE)
	public String home() {
		return ENTITY + "/" + CONSTANT.CREATE;
	}
}