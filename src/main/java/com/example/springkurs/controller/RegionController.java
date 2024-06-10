package com.example.springkurs.controller;

import com.example.springkurs.Services.CountryService;
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
@RequestMapping(value = "/regions")
public class RegionController {
    private final RegionService regionService;
    private final CountryService countryService;
    @Autowired
    public RegionController(
            RegionService regionService,CountryService countryService) {
        this.regionService = regionService;
        this.countryService = countryService;


    }

    @GetMapping()
    public String ShowRegions(Model model, @Param("namecountry") String namecountry) {
        regionService.fill(namecountry,model);

        model.addAttribute("countries",countryService.showAllCountries());
        model.addAttribute("region", new Region());
        return "region";
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRegion( @PathVariable(name = "id") long id){
        regionService.deleteRegionByid(id);
        return "redirect:/regions";
    }


    @PostMapping( "/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newRegion(Model model, @ModelAttribute(value = "region") @Valid Region region, Errors errors){

        if (errors.hasErrors()) {
            regionService.fill(null,model);
            model.addAttribute("countries",countryService.showAllCountries());
            return "region";
        }
        else {
            regionService.newRegion(region);
        }
        return "redirect:/regions";
    }


    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showRegionEditForm(Model model,@PathVariable(name = "id") int id)
    {

        model.addAttribute("region",regionService.showRegionById(id));
        model.addAttribute("countries",countryService.showAllCountries());
        return "regionedit";
    }

    @PostMapping(value = "/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRegion(Model model, @Valid @ModelAttribute("region") Region region, Errors errors) {
        if (errors.hasErrors())
        {
            model.addAttribute("countries",countryService.showAllCountries());
            return "regionedit";
        }
        else {
            regionService.newRegion(region);
        }

        return "redirect:/regions";
    }

}
