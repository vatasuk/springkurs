package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.Country;

import java.util.List;

public interface CountryRepository {
    List findAll();
    Country findById(int id);
    void save(Country country);
    void delete(int id);
    void update(Country country);
}
