package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;

import java.util.List;

public interface AddressRepository {
    List findAll();
    Address findById(int id);
    List<Address> findByCityId(int id);
    void save(Address address);
    void delete(int id);
    void update(Address address);
}
