package com.bhansali.controller.rest;

import com.bhansali.entity.Gender;
import com.bhansali.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestGenderController {

    @Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/gender/{id}")
    public Gender getGender(@PathVariable(name = "id") Integer id) {
        return generalService.getGender(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/genders")
    public List<Gender> getGenders() {
        return  generalService.getGenders();
    }
}