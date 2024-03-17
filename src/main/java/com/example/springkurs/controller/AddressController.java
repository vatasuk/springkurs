package com.example.springkurs.controller;

import com.example.springkurs.Repository.interfaces.AddressRepository;
import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddressController {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    @Autowired
    public AddressController(
            AddressRepository addressRepository,CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    @RequestMapping("/address")
    public String showAddresses(Model model)
    {
        List<Address> addresses = addressRepository.findAll();
        model.addAttribute("addresses",addresses);

        return "address";
    }
    @GetMapping(value = "/city/{id}/addresses")
    public String ShowParks(Model model, @PathVariable(name = "id") int id) {
        List<Address> addresses = addressRepository.findByCityId(id);
        model.addAttribute("addresses", addresses);
        return "address";

    }

}
