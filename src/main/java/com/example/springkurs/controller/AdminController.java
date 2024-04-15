package com.example.springkurs.controller;

import com.example.springkurs.Services.AddressService;
import com.example.springkurs.Services.CityService;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CityService cityService;
    private AddressService addressService;
    private CountryService countryService;
    private RegionService regionService;
    @Autowired
    public AdminController(
            CityService cityService, AddressService addressService, RegionService regionService, CountryService countryService) {
        this.cityService = cityService;
        this.addressService = addressService;
        this.countryService = countryService;
        this.regionService = regionService;
    }

    @GetMapping
    public String fillLists(Model model){
        showData(model);
        showForms(model);
        return "admin";
    }
    public void showData(Model model){
        Iterable<Address> addresses = addressService.findAllAddresses();
        System.out.println(addresses);
        model.addAttribute("addresses",addresses);
        Iterable<City> cities = cityService.findAllCities();
        model.addAttribute("cities", cities);
        Iterable<Region> regions = regionService.findAllRegions();
        model.addAttribute("regions", regions);
        Iterable<Country> countries = countryService.showAllCountries();
        model.addAttribute("countries",countries);


    }
    public void showForms(Model model)
    {
        Country country = new Country();
        model.addAttribute("country", country);
        Region region = new Region();
        model.addAttribute("region", region);
        City city = new City();
        model.addAttribute("city", city);
        Address address = new Address();
        model.addAttribute("address", address);
    }

    @GetMapping(value = "/delete/{id}/address")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAddress( @PathVariable(name = "id") long id){
        addressService.deleteAddressById(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/delete/{id}/city")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCity( @PathVariable(name = "id") long id){
        cityService.deleteCityById(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "delete/{id}/region")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRegion( @PathVariable(name = "id") long id){
        regionService.deleteRegionByid(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/delete/{id}/country")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCountry( @PathVariable(name = "id") long id){
        countryService.deleteCountryById(id);
        return "redirect:/admin";
    }

    @PostMapping( "/newcountry")
    @PreAuthorize("hasRole('ADMIN')")
    public String newCountry(Model model,@Valid @ModelAttribute(value = "country")  Country country, Errors errors,
                             @ModelAttribute(value = "address")  Address address,
                             @ModelAttribute(value = "city")  City city,
                             @ModelAttribute(value = "region")  Region region){

        if (errors.hasErrors()) {
            showData(model);
            return "admin";
        }
        else {
            countryService.newCountry(country);
        }
        return "redirect:/admin";
    }

    @PostMapping( "/newregion")
    @PreAuthorize("hasRole('ADMIN')")
    public String newRegion(Model model, @ModelAttribute(value = "region") @Valid Region region, Errors errors){

        if (errors.hasErrors()) {
            showData(model);
            Country country = new Country();
            model.addAttribute("country", country);
            City city = new City();
            model.addAttribute("city", city);
            Address address = new Address();
            model.addAttribute("address", address);
            return "admin";
        }
        else {
            regionService.newRegion(region);
        }
        return "redirect:/admin";
    }

    @PostMapping( "/newcity")
    @PreAuthorize("hasRole('ADMIN')")
    public String newCity(Model model, @ModelAttribute(value = "city") @Valid City city, Errors errors){

        if (errors.hasErrors()) {
            showData(model);
            Country country = new Country();
            model.addAttribute("country", country);
            Region region = new Region();
            model.addAttribute("region", region);
            Address address = new Address();
            model.addAttribute("address", address);

            return "admin";
        }
        else {
            cityService.newCity(city);
        }
        return "redirect:/admin";
    }
    @PostMapping( "/newaddress")
    @PreAuthorize("hasRole('ADMIN')")
    public String newAddress(Model model, @ModelAttribute(value = "address") @Valid Address address, Errors errors){

        if (errors.hasErrors()) {
            showData(model);
            Country country = new Country();
            model.addAttribute("country", country);
            Region region = new Region();
            model.addAttribute("region", region);
            City city = new City();
            model.addAttribute("city", city);
            return "admin";
        }
        else {
            addressService.newAddress(address);
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}/country")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCountryEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Country> country = countryService.showCountryById(id);
        model.addAttribute("country",country);
        return "countryedit";
    }
    @PostMapping(value = "/updatecountry")
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

        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}/region")
    @PreAuthorize("hasRole('ADMIN')")
    public String showRegionEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Region> region = regionService.showRegionById(id);
        Iterable<Country> countries = countryService.showAllCountries();
        model.addAttribute("countries",countries);
        model.addAttribute("region",region);
        return "regionedit";
    }
    @PostMapping(value = "/updateregion")
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

        return "redirect:/admin";
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

        return "redirect:/admin";
    }


    @GetMapping(value = "/edit/{id}/address")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddressEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Address> address = addressService.showAddressById(id);
        Iterable<City> cities = cityService.findAllCities();
        model.addAttribute("cities",cities);
        model.addAttribute("address",address);
        return "addressedit";
    }
    @PostMapping(value = "/updateaddress")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateAddress(Model model, @Valid @ModelAttribute("address") Address address,Errors errors) {
        if (errors.hasErrors())
        {
            model.addAttribute("cities",cityService.findAllCities());
            return "addressedit";
        }
        else
        {
            addressService.newAddress(address);
        }

        return "redirect:/admin";
    }

}
