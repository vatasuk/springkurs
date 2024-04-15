package com.example.springkurs.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.springkurs.Repository.interfaces.CountryRepository;

import com.example.springkurs.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CountryRepositoryTest {
    private CountryRepository countryRepository;
    @Autowired
    public CountryRepositoryTest(
            CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    private Country country;
    @BeforeEach
    public void setup(){
        country = Country.builder()
                .fullname("211221")
                .shortname("21212")
                .build();
    }
    @Test
    public void saveCountryTest()
    {
        Country savedCountry = countryRepository.save(country);
        assertThat(savedCountry).isNotNull();
        assertThat(savedCountry.getCountryid()).isGreaterThan(0);
    }
    @Test
    public void getCountryListTest()
    {
        Country country1 = Country.builder()
                .fullname("zxzxzx")
                .shortname("kaka").build();

        countryRepository.save(country1);
        countryRepository.save(country);
        Iterable<Country> list = countryRepository.findAll();

        assertThat(list).isNotNull();
    }
    @Test
    public void findByIdTest()
    {
        countryRepository.save(country);

        Country country2 = countryRepository.findById(country.getCountryid()).get();

        assertThat(country2).isNotNull();
    }
}
