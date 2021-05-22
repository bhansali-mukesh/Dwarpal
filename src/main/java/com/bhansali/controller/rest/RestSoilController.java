package com.bhansali.controller.rest;

import com.bhansali.entity.Soil;
import com.bhansali.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestSoilController {

    @Autowired
    GeneralService generalService;

    @RequestMapping(method = RequestMethod.GET, value = "/soil/{id}")
    public Soil getSoil(@PathVariable(name = "id") Integer id) {
        return generalService.getSoil(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/soils")
    public List<Soil> getSoils() {
        return  generalService.getSoils();
    }
}