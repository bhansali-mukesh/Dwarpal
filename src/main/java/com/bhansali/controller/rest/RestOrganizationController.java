package com.bhansali.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhansali.entity.Organization;
import com.bhansali.service.GeneralService;

@RestController
@RequestMapping("/api")
public class RestOrganizationController {
    
 	@Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/organization/{id}")
    public Organization getOrganization(@PathVariable(name = "id") Integer id) {
        return generalService.getOrganization(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/organizations")
    public List<Organization> getOrganizations() {
        return  generalService.getOrganizations();
    }
}