package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.City;

import java.util.List;

public interface CityRepository {
    List findAll();
    City findById(int id);
    List<City> findByRegionId(int id);
    void save(City city);
    void delete(int id);
    void update(City city);
}
