package com.bhansali.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhansali.entity.Available;
import com.bhansali.service.GeneralService;

@RestController
@RequestMapping("/api")
public class RestAvailableController {

    @Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/available")
    public List<Available> getAvailable() {
        return  generalService.getAvailables();
    }
}