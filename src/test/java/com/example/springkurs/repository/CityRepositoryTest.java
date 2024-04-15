package com.example.springkurs.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.Repository.interfaces.CountryRepository;

import com.example.springkurs.Repository.interfaces.RegionRepository;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CityRepositoryTest {
    private CityRepository cityRepository;
    @Autowired
    public CityRepositoryTest(
            CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    private City city;
    @BeforeEach
    public void setup(){
        city = City.builder().namecity("212121").build();
    }
    @Test
    public void saveCityTest()
    {
        City saved = cityRepository.save(city);
        assertThat(saved).isNotNull();
        assertThat(saved.getCityid()).isGreaterThan(0);
    }
    @Test
    public void getCityListTest()
    {
        City city1 = City.builder().namecity("12333121").build();

        cityRepository.save(city1);
        cityRepository.save(city);
        Iterable<City> list = cityRepository.findAll();

        assertThat(list).isNotNull();
    }
    @Test
    public void findByIdTest()
    {
        cityRepository.save(city);

        City country2 = cityRepository.findById(city.getCityid()).get();

        assertThat(country2).isNotNull();
    }
}