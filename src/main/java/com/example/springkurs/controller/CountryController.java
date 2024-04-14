package com.example.springkurs.controller;


import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.Repository.interfaces.CountryRepository;
import com.example.springkurs.Services.CountryService;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(
            CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public String showCountries(Model model)
    {
        Iterable<Country> countries = countryService.showAllCountries();
        model.addAttribute("countries",countries);
        return "country";
    }
}
