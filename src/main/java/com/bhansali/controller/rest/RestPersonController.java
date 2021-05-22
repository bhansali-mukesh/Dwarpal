package com.bhansali.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhansali.entity.Person;
import com.bhansali.service.GeneralService;

@RestController
@RequestMapping("/api")
public class RestPersonController {

    @Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/person/{id}")
    public Person getPerson(@PathVariable(name = "id") Integer id) {
    	return generalService.getPerson(id);
    }
    
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> getPersons() {
    	return generalService.getPersons();
    }
}