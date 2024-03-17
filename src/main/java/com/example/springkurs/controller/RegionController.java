package com.example.springkurs.controller;

import com.example.springkurs.Repository.interfaces.RegionRepository;
import com.example.springkurs.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RegionController {
    private final RegionRepository regionRepository;
    @Autowired
    public RegionController(
            RegionRepository regionRepository) {
        this.regionRepository = regionRepository;

    }

    @GetMapping(value = "/country/{id}/regions")
    public String ShowParks(Model model, @PathVariable(name = "id") int id) {
        List<Region> regions = regionRepository.findByCountryId(id);
        model.addAttribute("regions", regions);
        return "region";

    }

}
