package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.City;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CityRepository extends CrudRepository<City,Long> {

    Iterable<City> findByRegion_Regionid(Long id);
    Iterable<City> findByRegion_Nameregion(String nameregion);
    Iterable<City> findByRegion_Country_Fullname(String fullname);
    Iterable<City> findByRegion_Country_FullnameOrRegion_Nameregion(String fullname, String nameregion);
}
