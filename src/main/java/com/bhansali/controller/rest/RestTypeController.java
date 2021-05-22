package com.bhansali.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhansali.entity.Type;
import com.bhansali.service.GeneralService;

@RestController
@RequestMapping("/api")
public class RestTypeController {

    @Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/type/{id}")
    public Type getType(@PathVariable(name = "id") Integer id) {
        return generalService.getType(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public List<Type> getTypes() {
        return  generalService.getTypes();
    }
}