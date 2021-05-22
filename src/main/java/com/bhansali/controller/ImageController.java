package com.bhansali.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bhansali.util.CONSTANT;

@Controller
public class ImageController {

	public static final String ENTITY = "image";

	@RequestMapping(method = RequestMethod.GET, value = "/" + ENTITY + "/{name}")
	public String home(Model model, @PathVariable(name = "name") String name) {

		model.addAttribute(ENTITY, "/img/" + name + ".jpg");
		return CONSTANT.VIEW + "/imager";
	}
}