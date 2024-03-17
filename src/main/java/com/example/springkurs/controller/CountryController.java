package com.example.springkurs.controller;


import com.example.springkurs.Repository.interfaces.CountryRepository;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CountryController {
    private final CountryRepository countryRepository;
    @Autowired
    public CountryController(
            CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

    }

    @RequestMapping("/countries")
    public String showAddresses(Model model)
    {
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries",countries);
        return "country";
    }
}
