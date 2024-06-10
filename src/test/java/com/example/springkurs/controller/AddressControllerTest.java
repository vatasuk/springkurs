package com.example.springkurs.controller;

import com.example.springkurs.Services.AddressService;
import com.example.springkurs.Services.CityService;
import com.example.springkurs.Services.CountryService;
import com.example.springkurs.Services.RegionService;
import com.example.springkurs.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@AutoConfigureMockMvc
@WebMvcTest(AddressController.class)
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;
    @MockBean
    private CityService cityService;

    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testShowEditForm() throws  Exception{
        long id = 1;
        mockMvc.perform(get("/address/edit/{id}",id)).andExpect(view().name("addressedit")).andExpect(model().attributeExists("address"));
    }
    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testDeleteAddress() throws  Exception{
        long id = 1;
        mockMvc.perform(get("/address/delete/{id}",id));
        verify(addressService).deleteAddressById(id);
    }

}
