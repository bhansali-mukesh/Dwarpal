package com.bhansali.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhansali.entity.Business;
import com.bhansali.service.GeneralService;

@RestController
@RequestMapping("/api")
public class RestBusinessController {

    @Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/business/{id}")
    public Business getBusiness(@PathVariable(name = "id") Integer id) {
        return generalService.getBusiness(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/businesses")
    public List<Business> getBusinesses() {
        return  generalService.getBusinesses();
    }
}