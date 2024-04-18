package com.example.springkurs.controller;

import com.example.springkurs.Services.AddressService;
import com.example.springkurs.Services.CityService;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    private CityService cityService;

    @Autowired
    public AddressController(
            AddressService addressService,CityService cityService) {
        this.addressService = addressService;
        this.cityService = cityService;

    }

    @GetMapping
    public String showAddresses(Model model)
    {
        Iterable<Address> addresses = addressService.findAllAddresses();
        Iterable<City> cities = cityService.findAllCities();
        model.addAttribute("addresses",addresses);
        model.addAttribute("cities",cities);
        Address address = new Address();
        model.addAttribute("address",address);
        return "address";
    }
    @GetMapping(value = "/{id}/city")
    public String ShowAddressesFromCity(Model model, @PathVariable(name = "id") Long id) {
        Iterable<Address> addresses = addressService.findAllAddressesByCityId(id);
        Optional<City> city = cityService.showCityById(id);
        model.addAttribute("addresses",addresses);
        Address address = new Address();
        model.addAttribute("address",address);
        model.addAttribute("cityid",id);
        model.addAttribute("namecity",city.get().getNamecity());
        return "addressfromcity";
    }
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAddress( @PathVariable(name = "id") long id){
        addressService.deleteAddressById(id);

        return "redirect:/address";
    }
    @RequestMapping(value = "/{cityid}/city/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAddressFromCity( @PathVariable(name = "id") long id,
                                     @PathVariable(name = "cityid") long cityid){
        addressService.deleteAddressById(id);

        return "redirect:/address/{cityid}/city";
    }

    @PostMapping( "/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newAddress(Model model, @ModelAttribute(value = "address") @Valid Address address, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("addresses",addressService.findAllAddresses());
            model.addAttribute("cities",cityService.findAllCities());
            return "address";
        }
        else {
            addressService.newAddress(address);
        }
        return "redirect:/address";
    }
    @PostMapping( "addressfromcity/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newAddressFromCity(Model model, @ModelAttribute(value = "address") @Valid Address address, Errors errors){
        long cityid = address.getCity().getCityid();
        if (errors.hasErrors()) {
            Optional<City> city = cityService.showCityById(address.getCity().getCityid());
           // model.addAttribute("cities",city);
            model.addAttribute("cityid",cityid);
            model.addAttribute("namecity",city.get().getNamecity());
            model.addAttribute("addresses",addressService.findAllAddressesByCityId(cityid));
            //model.addAttribute("cities",cityService.findAllCities());
            return "addressfromcity";
        }
        else {
            addressService.newAddress(address);
        }
        return "redirect:/address/" + cityid + "/city";
    }
    @GetMapping(value = "/{cityid}/city/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddressEditFormFromCity(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Address> address = addressService.showAddressById(id);
        model.addAttribute("cityid",address.get().getCity().getCityid());
        model.addAttribute("namecity",address.get().getCity().getNamecity());
        model.addAttribute("address",address);
        return "addresseditfromcity";
    }
    @GetMapping(value = "edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddressEditForm(Model model,@PathVariable(name = "id") int id)
    {
        Optional<Address> address = addressService.showAddressById(id);
        Iterable<City> cities = cityService.findAllCities();
        model.addAttribute("cities",cities);
        model.addAttribute("address",address);
        return "addressedit";
    }
    @PostMapping(value = "/update")
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

        return "redirect:/address";
    }
    @PostMapping(value = "/updateaddressfromcity")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateAddressFromCity(Model model, @Valid @ModelAttribute("address") Address address,Errors errors) {
       Long cityid = address.getCity().getCityid();
        if (errors.hasErrors())
        {

            model.addAttribute("cityid",cityid);
            model.addAttribute("namecity",cityService.showCityById(cityid).get().getNamecity());
            return "addresseditfromcity";
        }
        else
        {

            addressService.newAddress(address);
        }

        return "redirect:/address/"+ cityid +"/city";
    }



}
