package com.example.springkurs.controller;


import com.example.springkurs.Services.CountryService;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(
            CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public String showCountries(Model model)
    {
        model.addAttribute("countries",countryService.showAllCountries());
        model.addAttribute("country",new Country());
        return "country";
    }
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCountry( @PathVariable(name = "id") long id){
        countryService.deleteCountryById(id);
        return "redirect:/countries";
    }
    @PostMapping( "/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newCountry(Model model, @Valid @ModelAttribute(value = "country")  Country country, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("countries",countryService.showAllCountries());
            return "country";
        }
        else {
            countryService.newCountry(country);
        }
        return "redirect:/countries";
    }


    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCountryEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Country> country = countryService.showCountryById(id);
        model.addAttribute("country",country);
        return "countryedit";
    }
    @PostMapping(value = "/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCountry(Model model, @Valid @ModelAttribute("country") Country country,Errors errors) {
        if (errors.hasErrors())
        {

            return "countryedit";
        }
        else
        {
            countryService.newCountry(country);
        }

        return "redirect:/countries";
    }
}
