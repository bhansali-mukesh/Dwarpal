package com.bhansali.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhansali.entity.Category;
import com.bhansali.service.GeneralService;

@RestController
@RequestMapping("/api")
public class RestCategoryController {

	 	@Autowired
	    GeneralService generalService;

	    @RequestMapping(method = RequestMethod.GET, value = "/category/{id}")
	    public Category getCategory(@PathVariable(name = "id") Integer id) {
	        return generalService.getCategory(id);
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/categories")
	    public List<Category> getCategories() {
	        return  generalService.getCategories();
	    }
}