package com.example.springkurs.Repository.interfaces;

import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface AddressRepository extends CrudRepository<Address,Long> {
    @Query(nativeQuery = true, value = "select * from address where cityidfk = ?1")
    Iterable<Address> findAddressByCityid(long id);

}
