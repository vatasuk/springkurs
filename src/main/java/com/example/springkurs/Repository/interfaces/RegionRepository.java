package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Region;

import java.util.List;

public interface RegionRepository {
    List findAll();
    Region findById(int id);
    List<Region> findByCountryId(int id);
    void save(Region region);
    void delete(int id);
    void update(Region region);
}
