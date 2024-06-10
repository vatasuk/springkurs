package com.example.springkurs.Services;

import com.example.springkurs.Repository.interfaces.AddressRepository;
import com.example.springkurs.entity.Address;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        return addressRepository.findByCity_Cityid(id);
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
    public Iterable<Address> findByCityRegionCountry(String namecity,String nameregion,String fullname)
    {
        return addressRepository.findByCity_NamecityOrCity_Region_NameregionOrCity_Region_Country_Fullname(namecity,nameregion,fullname);
    }
    public Model fill( String namecity, String nameregion,String namecountry,Model model)
    {
        if ( (namecountry == null & nameregion == null & namecity == null) || (namecountry.isEmpty() & nameregion.isEmpty() & namecity.isEmpty()) )
        {
          return   model.addAttribute("addresses", findAllAddresses());
        }
        else
        {
           return model.addAttribute("addresses", findByCityRegionCountry(namecity,nameregion,namecountry));
        }
    }
}
