package com.example.springkurs.Services;

import com.example.springkurs.Repository.interfaces.RegionRepository;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {
    private final RegionRepository regionRepository;
    @Autowired
    public RegionService(
            RegionRepository regionRepository) {
        this.regionRepository = regionRepository;

    }

    public Iterable<Region> findAllRegionsByCountryId(long id){
        return regionRepository.findByCountry_Countryid(id);
    }

    public Iterable<Region> findAllRegions()
    {
        return regionRepository.findAll();
    }

    public void deleteRegionByid(long id)
    {
        regionRepository.deleteById(id);
    }

    public void newRegion(@Valid Region region)
    {
        regionRepository.save(region);
    }

    public Optional<Region> showRegionById(long id){
        return regionRepository.findById(id);
    }
}
