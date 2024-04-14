package com.example.springkurs.Services;

import com.example.springkurs.Repository.interfaces.CountryRepository;
import com.example.springkurs.entity.Country;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    @Autowired
    public CountryService(
            CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Iterable<Country> showAllCountries(){
        return countryRepository.findAll();
    }

    public void deleteCountryById(long id)
    {
        countryRepository.deleteById(id);
    }

    public void newCountry(@Valid Country country)
    {
        countryRepository.save(country);
    }

    public Optional<Country> showCountryById(long id){
        return countryRepository.findById(id);
    }
}
