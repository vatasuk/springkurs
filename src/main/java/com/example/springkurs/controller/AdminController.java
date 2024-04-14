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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        Iterable<Address> addresses = addressService.findAllAddresses();
        System.out.println(addresses);
        model.addAttribute("addresses",addresses);
        Iterable<City> cities = cityService.findAllCities();
        model.addAttribute("cities", cities);
        Iterable<Country> countries = countryService.showAllCountries();
        model.addAttribute("countries",countries);
        Iterable<Region> regions = regionService.findAllRegions();
        model.addAttribute("regions", regions);
        Country country = new Country();
        model.addAttribute("country", country);
        Region region = new Region();
        model.addAttribute("region", region);
        City city = new City();
        model.addAttribute("city", city);
        Address address = new Address();
        model.addAttribute("address", address);
        return "admin";
    }

    @GetMapping(value = "/delete/{id}/address")
    public String deleteAddress( @PathVariable(name = "id") long id){
        addressService.deleteAddressById(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/delete/{id}/city")
    public String deleteCity( @PathVariable(name = "id") long id){
        cityService.deleteCityById(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/delete/{id}/region")
    public String deleteRegion( @PathVariable(name = "id") long id){
        regionService.deleteRegionByid(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/delete/{id}/country")
    public String deleteCountry( @PathVariable(name = "id") long id){
        countryService.deleteCountryById(id);
        return "redirect:/admin";
    }

    @PostMapping( "/newcountry")
    public String newCountry(@ModelAttribute(value = "country") @Valid Country country, Errors errors){

        if (errors.hasErrors()) {
            return "redirect:/admin";
        }
        else {
            countryService.newCountry(country);
        }
        return "redirect:/admin";
    }

    @PostMapping( "/newregion")
    public String newRegion(@ModelAttribute(value = "region") @Valid Region region, Errors errors){

        if (errors.hasErrors()) {
            return "redirect:/admin";
        }
        else {
            regionService.newRegion(region);
        }
        return "redirect:/admin";
    }

    @PostMapping( "/newcity")
    public String newCity(@ModelAttribute(value = "city") @Valid City city, Errors errors){

        if (errors.hasErrors()) {
            return "redirect:/admin";
        }
        else {
            cityService.newCity(city);
        }
        return "redirect:/admin";
    }
    @PostMapping( "/newaddress")
    public String newAddress(@ModelAttribute(value = "address") @Valid Address address, Errors errors){

        if (errors.hasErrors()) {
            return "redirect:/admin";
        }
        else {
            addressService.newAddress(address);
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}/country")
    public String showCountryEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Country> country = countryService.showCountryById(id);
        model.addAttribute("country",country);
        return "countryedit";
    }
    @PostMapping(value = "/updatecountry")
    public String updateCountry(Model model, @ModelAttribute("country") Country country) {
        countryService.newCountry(country);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}/region")
    public String showRegionEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Region> region = regionService.showRegionById(id);
        Iterable<Country> countries = countryService.showAllCountries();
        model.addAttribute("countries",countries);
        model.addAttribute("region",region);
        return "regionedit";
    }
    @PostMapping(value = "/updateregion")
    public String updateRegion(Model model, @ModelAttribute("region") Region region) {
        regionService.newRegion(region);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}/city")
    public String showCityEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<City> city = cityService.showCityById(id);
        Iterable<Region> regions = regionService.findAllRegions();
        model.addAttribute("regions",regions);
        model.addAttribute("city",city);
        return "cityedit";
    }
    @PostMapping(value = "/updatecity")
    public String updateCity(Model model, @ModelAttribute("city") City city) {
        cityService.newCity(city);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}/address")
    public String showAddressEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Address> address = addressService.showAddressById(id);
        Iterable<City> cities = cityService.findAllCities();
        model.addAttribute("cities",cities);
        model.addAttribute("address",address);
        return "addressedit";
    }
    @PostMapping(value = "/updateaddress")
    public String updateAddress(Model model, @ModelAttribute("address") Address address) {
        addressService.newAddress(address);
        return "redirect:/admin";
    }

}
