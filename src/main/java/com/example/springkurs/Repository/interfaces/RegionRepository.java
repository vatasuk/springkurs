package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegionRepository extends CrudRepository<Region,Long> {

    Iterable<Region> findByCountry_Countryid(long id);
    Iterable<Region> findByNameregion(String nameregion);
    Iterable<Region> findByCountry_Fullname(String namecountry);
}
