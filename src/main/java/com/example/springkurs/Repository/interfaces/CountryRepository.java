package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {

    Iterable<Country> findByFullname(String fullname);
}
