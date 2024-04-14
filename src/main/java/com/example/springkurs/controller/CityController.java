package com.example.springkurs.controller;

import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.Repository.interfaces.CountryRepository;
import com.example.springkurs.Services.CityService;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cities")
public class CityController {
    private CityService cityService;
    @Autowired
    public CityController(
            CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/{id}/region")
    public String ShowCities(Model model, @PathVariable(name = "id") Long id) {
        Iterable<City> cities = cityService.findAllCitiesByRegionId(id);
        model.addAttribute("cities", cities);
        return "city";
    }
    @GetMapping
    public String ShowAllCities(Model model) {
        Iterable<City> cities = cityService.findAllCities();
        model.addAttribute("cities", cities);
        return "city";
    }


}
