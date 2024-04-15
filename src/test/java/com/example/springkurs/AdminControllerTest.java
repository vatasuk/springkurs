package com.example.springkurs;

import com.example.springkurs.Services.AddressService;
import com.example.springkurs.Services.CityService;
import com.example.springkurs.Services.CountryService;
import com.example.springkurs.Services.RegionService;
import com.example.springkurs.controller.AdminController;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminController.class)
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountryService countryService;
    @MockBean
    private RegionService regionService;
    @MockBean
    private CityService cityService;
    @MockBean
    private AddressService addressService;
    @InjectMocks
    private AdminController adminController;

    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testAdminPage() throws Exception
    {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk()).andExpect(view().name("admin"));
    }
    @Test
    public void testAccessPage() throws Exception
    {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testAdminModel() throws Exception
    {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk()).andExpect(view().name("admin"))
                .andExpect(model().attributeExists("cities","countries","addresses","regions"));
    }
    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testDeleteCountry() throws  Exception{
        long id = 1;
        mockMvc.perform(get("/admin/delete/{id}/country",id));
        verify(countryService).deleteCountryById(id);
    }
    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testDeleteAddress() throws  Exception{
        long id = 1;
        mockMvc.perform(get("/admin/delete/{id}/address",id));
        verify(addressService).deleteAddressById(id);
    }
    @Test
    @WithMockUser(username = "vadim", roles = "admin")
    public void testShowEditFormCity() throws  Exception{
        long id = 1;
        mockMvc.perform(get("/admin/edit/{id}/city",id)).andExpect(view().name("cityedit")).andExpect(model().attributeExists("city"));
    }


}
