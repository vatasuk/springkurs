package com.example.springkurs.Services;

import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Country;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public Iterable<City> findByRegionname(String nameregion)
    {
        return cityRepository.findByRegion_Nameregion(nameregion);
    }
    public Iterable<City> findByCountryname(String namecountry)
    {
        return cityRepository.findByRegion_Country_Fullname(namecountry);
    }
    public Iterable<City> findByCountryAndRegion(String namecountry,String nameregion)
    {
        return cityRepository.findByRegion_Country_FullnameOrRegion_Nameregion(namecountry,nameregion);
    }
    public Model fill(String namecountry,String nameregion,Model model)
    {
        if ( (namecountry == null & nameregion == null) || (namecountry.isEmpty() & nameregion.isEmpty()) )
        {
           return model.addAttribute("cities", findAllCities());
        }
        else
        {
          return   model.addAttribute("cities", findByCountryAndRegion(namecountry,nameregion));
        }
    }
}
