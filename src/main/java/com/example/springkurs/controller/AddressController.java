package com.example.springkurs.controller;

import com.example.springkurs.Repository.interfaces.AddressRepository;
import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.Services.AddressService;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(
            AddressService addressService) {
        this.addressService = addressService;

    }

    @GetMapping
    public String showAllAddresses(Model model)
    {
        Iterable<Address> addresses = addressService.findAllAddresses();
        System.out.println(addresses);
        model.addAttribute("addresses",addresses);

        return "address";
    }
    @GetMapping(value = "/city/{id}/addresses")
    public String ShowAddresses(Model model, @PathVariable(name = "id") Long id) {
       Iterable<Address> addresses = addressService.findAllAddressesByCityId(id);
       model.addAttribute("addresses", addresses);
        return "address";
    }




}
