package com.example.springkurs.controller;

import com.example.springkurs.Services.AddressService;
import com.example.springkurs.Services.CityService;
import com.example.springkurs.Services.CountryService;
import org.junit.jupiter.api.Test;
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
@WebMvcTest(CountryController.class)
public class CountryTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountryService countryService;

    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testShowEditForm() throws  Exception{
        long id = 1;
        mockMvc.perform(get("/countries/edit/{id}",id)).andExpect(view().name("countryedit")).andExpect(model().attributeExists("country"));
    }
    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testDeleteAddress() throws  Exception{
        long id = 1;
        mockMvc.perform(get("/countries/delete/{id}",id));
        verify(countryService).deleteCountryById(id);
    }
}