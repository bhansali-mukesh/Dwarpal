package com.bhansali.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GeneralController {

	@GetMapping("/error")
	public String error() {
		return "view/error";
	}

	@PostMapping("/error")
	public String Error() {
		return "view/error";
	}
}