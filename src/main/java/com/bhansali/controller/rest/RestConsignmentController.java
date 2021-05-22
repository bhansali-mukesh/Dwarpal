package com.bhansali.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhansali.entity.Consignment;
import com.bhansali.entity.Organization;
import com.bhansali.service.GeneralService;

@RestController
@RequestMapping("/api")
public class RestConsignmentController {

    @Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/consignments/received/{organization}")
    public List<Consignment> getReceivedConsignments(@PathVariable(name = "organization") Organization organization) {
        return generalService.getReceivedConsignments(organization);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consignments/sent/{organization}")
    public List<Consignment> getSentConsignments(@PathVariable(name = "organization") Organization organization) {
        return generalService.getSentConsignments(organization);
    }

}