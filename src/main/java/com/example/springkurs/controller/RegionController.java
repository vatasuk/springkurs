package com.example.springkurs.controller;

import com.example.springkurs.Repository.interfaces.RegionRepository;
import com.example.springkurs.Services.RegionService;
import com.example.springkurs.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/regions")
public class RegionController {
    private final RegionService regionService;
    @Autowired
    public RegionController(
            RegionService regionService) {
        this.regionService = regionService;

    }

    @GetMapping(value = "/{id}/country")
    public String ShowRegions(Model model, @PathVariable(name = "id") Long id) {
        Iterable<Region> regions = regionService.findAllRegionsByCountryId(id);
        model.addAttribute("regions", regions);
        return "region";
    }
    @GetMapping
    public String showAllRegions(Model model){
        Iterable<Region> regions = regionService.findAllRegions();
        model.addAttribute("regions", regions);
        return "region";
    }

}
