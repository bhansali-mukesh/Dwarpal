package com.bhansali.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

	public static final String ENTITY = "application";
	public static final String SHUTDOWN = "shutdown";

	@GetMapping("/" + ENTITY + "/control")
	public static String admin(Model model) {
		return ENTITY + "/" + SHUTDOWN;
	}

}