package com.example.springkurs.Services;

import com.example.springkurs.Repository.interfaces.AddressRepository;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(
            AddressRepository addressRepository) {
        this.addressRepository = addressRepository;

    }
    public Iterable<Address> findAllAddresses(){
        return addressRepository.findAll();
    }

    public Iterable<Address> findAllAddressesByCityId(long id){
        return addressRepository.findAddressByCityid(id);
    }

    public void deleteAddressById(long id){
        addressRepository.deleteById(id);
    }

    public void newAddress(@Valid Address address){
        addressRepository.save(address);

    }
    public Optional<Address> showAddressById(long id){
        return addressRepository.findById(id);
    }
}
