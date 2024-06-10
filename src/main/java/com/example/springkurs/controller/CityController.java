package com.example.springkurs.controller;

import com.example.springkurs.Services.CityService;
import com.example.springkurs.Services.RegionService;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/cities")
public class CityController {
    private CityService cityService;
    private RegionService regionService;
    @Autowired
    public CityController(
            CityService cityService,RegionService regionService) {
        this.cityService = cityService;
        this.regionService = regionService;
    }

    @GetMapping
    public String ShowCities(Model model, @Param("namecountry") String namecountry,@Param("nameregion") String nameregion) {

        cityService.fill(namecountry,nameregion,model);
        model.addAttribute("regions",regionService.findAllRegions());
        model.addAttribute("city",new City());
        return "city";
    }
    @GetMapping(value = "delete/{id}/city")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCity( @PathVariable(name = "id") long id){
        cityService.deleteCityById(id);
        return "redirect:/cities";
    }

    @PostMapping( "/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newCity(Model model, @ModelAttribute(value = "city") @Valid City city, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("regions",regionService.findAllRegions());
            cityService.fill(null,null,model);

            return "city";
        }
        else {
            cityService.newCity(city);
        }
        return "redirect:/cities";
    }


    @GetMapping(value = "/edit/{id}/city")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCityEditForm(Model model,@PathVariable(name = "id") int id)
    {
        model.addAttribute("regions",regionService.findAllRegions());
        model.addAttribute("city",cityService.showCityById(id));
        return "cityedit";
    }

    @PostMapping(value = "/updatecity")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCity(Model model,@Valid @ModelAttribute("city") City city,Errors errors) {
        if (errors.hasErrors())
        {
            model.addAttribute("regions",regionService.findAllRegions());
            return "cityedit";
        }
        else
        {
            cityService.newCity(city);
        }

        return "redirect:/cities";
    }



}
