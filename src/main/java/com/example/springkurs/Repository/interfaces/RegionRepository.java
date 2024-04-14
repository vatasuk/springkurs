package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.City;
import com.example.springkurs.entity.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegionRepository extends CrudRepository<Region,Long> {
    @Query(value = "select * from region where countryidfk = ?", nativeQuery = true)
    Iterable<Region> findRegionByCountryid(long id);
}
