package com.example.springkurs.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springkurs.Repository.interfaces.AddressRepository;
import com.example.springkurs.Repository.interfaces.CityRepository;
import com.example.springkurs.Repository.interfaces.CountryRepository;

import com.example.springkurs.Repository.interfaces.RegionRepository;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AddressRepositoryTest {
    private AddressRepository addressRepository;
    @Autowired
    public AddressRepositoryTest(
            AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    private Address address;
    @BeforeEach
    public void setup(){
        address = Address.builder().person("212121").street("12122323").building("121212").office("sfds23").build();
    }
    @Test
    public void saveAddressTest()
    {
        Address saved = addressRepository.save(address);
        assertThat(saved).isNotNull();
        assertThat(saved.getAddressid()).isGreaterThan(0);
    }
    @Test
    public void getCityListTest()
    {
        Address address1 = Address.builder().person("212sdsd21").street("12dfx2").building("121212").office("sfds23").build();

        addressRepository.save(address1);
        addressRepository.save(address);
        Iterable<Address> list = addressRepository.findAll();

        assertThat(list).isNotNull();
    }
    @Test
    public void findByIdTest()
    {
        addressRepository.save(address);

        Address address1 = addressRepository.findById(address.getAddressid()).get();

        assertThat(address1).isNotNull();
    }
}