package com.example.springkurs.Services;

import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Country;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    private CityRepository cityRepository;
    @Autowired
    public CityService(
            CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Iterable<City> findAllCitiesByRegionId(long id){
        return cityRepository.findByRegion_Regionid(id);
    }

    public Iterable<City> findAllCities(){
        return cityRepository.findAll();
    }

    public void deleteCityById(long id){
        cityRepository.deleteById(id);
    }

    public void newCity(@Valid City city){
        cityRepository.save(city);
    }

    public Optional<City> showCityById(long id){
        return cityRepository.findById(id);
    }
}
