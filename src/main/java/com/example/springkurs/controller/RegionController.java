package com.example.springkurs.controller;

import com.example.springkurs.Services.CountryService;
import com.example.springkurs.Services.RegionService;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String ShowRegions(Model model) {

        model.addAttribute("regions", regionService.findAllRegions());
        model.addAttribute("countries",countryService.showAllCountries());
        Region region = new Region();
        model.addAttribute("region", region);
        return "region";
    }
    @GetMapping(value = "/{id}/country")
    public String showRegionsFromCountry(Model model, @PathVariable(name = "id") Long id){
        Iterable<Region> regions = regionService.findAllRegionsByCountryId(id);
        Optional<Country> country = countryService.showCountryById(id);
        model.addAttribute("regions", regions);
        Region region = new Region();
        model.addAttribute("region",region);
        model.addAttribute("countryid", id);
        model.addAttribute("fullname",country.get().getFullname());
        return "regionfromcountry";
    }
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRegion( @PathVariable(name = "id") long id){
        regionService.deleteRegionByid(id);
        return "redirect:/regions";
    }
    @GetMapping(value = "/{countryid}/country/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRegionFromCity( @PathVariable(name = "id") long id){
        regionService.deleteRegionByid(id);
        return "redirect:/regions/{countryid}/country";
    }

    @PostMapping( "/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newRegion(Model model, @ModelAttribute(value = "region") @Valid Region region, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("regions",regionService.findAllRegions());
            model.addAttribute("countries",countryService.showAllCountries());
            return "region";
        }
        else {
            regionService.newRegion(region);
        }
        return "redirect:/regions";
    }
    @PostMapping( "regionfromcountry/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newRegionFromCity(Model model, @ModelAttribute(value = "region") @Valid Region region, Errors errors){
        long countryid = region.getCountry().getCountryid();
        if (errors.hasErrors()) {
            Optional<Country> country = countryService.showCountryById(region.getCountry().getCountryid());
            model.addAttribute("countryid",countryid);
            model.addAttribute("fullname",country.get().getFullname());
            model.addAttribute("regions", regionService.findAllRegionsByCountryId(countryid));
            return "regionfromcountry";
        }
        else {
            regionService.newRegion(region);
        }
        return "redirect:/regions/" + countryid + "/country";
    }

    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showRegionEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Region> region = regionService.showRegionById(id);
        Iterable<Country> countries = countryService.showAllCountries();
        model.addAttribute("countries",countries);
        model.addAttribute("region",region);
        return "regionedit";
    }
    @GetMapping(value = "/{countryid}/country/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showRegionEditFormFromCity(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Region> region = regionService.showRegionById(id);
        model.addAttribute("countryid",region.get().getCountry().getCountryid());
        model.addAttribute("fullname",region.get().getCountry().getFullname());
        model.addAttribute("region",region);
        return "regionfromcountryedit";
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
    @PostMapping(value = "/updateregionfromcity")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRegionFromCity(Model model, @Valid @ModelAttribute("Region") Region region,Errors errors) {
        Long countryid = region.getCountry().getCountryid();
        if (errors.hasErrors())
        {

            model.addAttribute("countryid",countryid);
            model.addAttribute("fullname",countryService.showCountryById(countryid).get().getFullname());
            return "addresseditfromcity";
        }
        else
        {

            regionService.newRegion(region);
        }

        return "redirect:/regions/"+ countryid +"/country";
    }

}
