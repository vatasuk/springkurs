package com.example.springkurs.controller;

import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.Repository.interfaces.CountryRepository;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CityController {
    private final CityRepository cityRepository;
    @Autowired
    public CityController(
            CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = "/region/{id}/cities")
    public String ShowParks(Model model, @PathVariable(name = "id") int id) {
        List<City> cities = cityRepository.findByRegionId(id);
        model.addAttribute("cities", cities);
        return "city";

    }
}
