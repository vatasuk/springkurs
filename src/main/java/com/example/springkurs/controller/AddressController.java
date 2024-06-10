package com.example.springkurs.controller;

import com.example.springkurs.Services.AddressService;
import com.example.springkurs.Services.CityService;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public String showAddresses(Model model,@Param("namecountry") String namecountry, @Param("nameregion") String nameregion, @Param("namecity") String namecity)
    {
        addressService.fill(namecity,nameregion,namecountry,model);

        model.addAttribute("cities",cityService.findAllCities());
        model.addAttribute("address",new Address());
        return "address";
    }
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAddress( @PathVariable(name = "id") long id){
        addressService.deleteAddressById(id);

        return "redirect:/address";
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


    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddressEditForm(Model model,@PathVariable(name = "id") int id)
    {

        model.addAttribute("cities",cityService.findAllCities());
        model.addAttribute("address",addressService.showAddressById(id));
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

}
