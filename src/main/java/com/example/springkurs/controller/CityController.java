package com.example.springkurs.controller;

import com.example.springkurs.Services.CityService;
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

    @GetMapping(value = "/{id}/region")
    public String ShowCitiesFromRegion(Model model, @PathVariable(name = "id") Long id) {
        Iterable<City> cities = cityService.findAllCitiesByRegionId(id);
        Optional<Region> region =regionService.showRegionById(id);

        model.addAttribute("cities", cities);
        City city = new City();
        model.addAttribute("city",city);
        model.addAttribute("regionid",id);
        model.addAttribute("nameregion",region.get().getNameregion());
        return "cityfromregion";
    }
    @GetMapping
    public String ShowCities(Model model) {
        Iterable<City> cities = cityService.findAllCities();
        Iterable<Region> regions = regionService.findAllRegions();
        model.addAttribute("regions",regions);
        model.addAttribute("cities", cities);
        City city = new City();
        model.addAttribute("city",city);
        return "city";
    }
    @GetMapping(value = "delete/{id}/city")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCity( @PathVariable(name = "id") long id){
        cityService.deleteCityById(id);
        return "redirect:/cities";
    }
    @GetMapping(value = "/{regionid}/region/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCityFromRegion( @PathVariable(name = "id") long id){
        cityService.deleteCityById(id);
        return "redirect:/cities/{regionid}/region";
    }

    @PostMapping( "/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newCity(Model model, @ModelAttribute(value = "city") @Valid City city, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("regions",regionService.findAllRegions());
            model.addAttribute("cities",cityService.findAllCities());

            return "city";
        }
        else {
            cityService.newCity(city);
        }
        return "redirect:/cities";
    }
    @PostMapping( "cityfromregion/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newCityFromAddress(Model model, @ModelAttribute(value = "city") @Valid City city, Errors errors){
        long regionid = city.getRegion().getRegionid();
        if (errors.hasErrors()) {
            Optional<Region> region = regionService.showRegionById(city.getRegion().getRegionid());
            model.addAttribute("regionid",regionid);
            model.addAttribute("nameregion",region.get().getNameregion());
            model.addAttribute("cities",cityService.findAllCitiesByRegionId(regionid));

            return "cityfromregion";
        }
        else {
            cityService.newCity(city);
        }
        return "redirect:/cities/" + regionid + "/region";
    }

    @GetMapping(value = "/edit/{id}/city")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCityEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<City> city = cityService.showCityById(id);
        Iterable<Region> regions = regionService.findAllRegions();
        model.addAttribute("regions",regions);
        model.addAttribute("city",city);
        return "cityedit";
    }

    @GetMapping(value = "/{regionid}/region/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCityEditFormFromRegion(Model model,@PathVariable(name = "id") int id)
    {
        Optional<City> city = cityService.showCityById(id);

        model.addAttribute("regionid",city.get().getRegion().getRegionid());
        model.addAttribute("nameregion",city.get().getRegion().getNameregion());
        model.addAttribute("city",city);
        return "cityeditfromregion";
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
    @PostMapping(value = "/updatecityfromregion")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCityFromRegion(Model model,@Valid @ModelAttribute("city") City city,Errors errors) {
        Long regionid = city.getRegion().getRegionid();
        if (errors.hasErrors())
        {
            model.addAttribute("regionid",regionid);
            model.addAttribute("nameregion",regionService.showRegionById(regionid).get().getNameregion());
            return "cityeditfromregion";
        }
        else
        {
            cityService.newCity(city);
        }

        return "redirect:/cities/" + regionid + "/region";
    }


}
