package com.codigofacilito.controllers;

import static org.mockito.Mockito.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  

import org.junit.jupiter.api.BeforeEach;  
import org.junit.jupiter.api.Test;  
import org.mockito.InjectMocks;  
import org.mockito.Mock;  
import org.mockito.MockitoAnnotations;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.MediaType;  
import org.springframework.test.web.servlet.MockMvc;  
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.codigofacilito.pet_shelter.controllers.AdoptionController;
import com.codigofacilito.pet_shelter.models.adoptions.AdoptionEntity;
import com.codigofacilito.pet_shelter.services.AdoptionService;

import java.util.Arrays;  

public class AdoptionControllerTest {  

    @Autowired  
    private MockMvc mockMvc;  

    @Mock  
    private AdoptionService adoptionService;  

    @InjectMocks  
    private AdoptionController adoptionController;  

    @BeforeEach  
    public void setUp() {  
        MockitoAnnotations.openMocks(this);  
        mockMvc = MockMvcBuilders.standaloneSetup(adoptionController).build();  
    }  

    @Test  
    public void testGetAllAdoptions() throws Exception {  
        when(adoptionService.getAllAdoptions()).thenReturn(Arrays.asList(new AdoptionEntity(), new AdoptionEntity()));  

        mockMvc.perform(get("/api/adoptions"))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$").isArray());  
    }  

    @Test  
    public void testGetAdoptionById() throws Exception {  
        Long id = 1L;  
        when(adoptionService.getAdoptionById(id)).thenReturn(new AdoptionEntity());  

        mockMvc.perform(get("/api/adoptions/{id}", id))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$").isNotEmpty());  
    }  

    @Test  
    public void testGetAdoptionByIdNotFound() throws Exception {  
        Long id = 1L;  
        when(adoptionService.getAdoptionById(id)).thenReturn(null);  

        mockMvc.perform(get("/api/adoptions/{id}", id))  
                .andExpect(status().isNotFound());  
    }  

    @Test  
    public void testNewAdoption() throws Exception {  
        String newAdoptionJson = "{\"userId\": 1, \"petId\": 2}";  

        mockMvc.perform(post("/api/adoptions")  
                .contentType(MediaType.APPLICATION_JSON)  
                .content(newAdoptionJson))  
                .andExpect(status().isOk())  
                .andExpect(content().string("Adoption created successfully"));  
    }  

    @Test  
    public void testNewAdoptionBadRequest() throws Exception {  
        String newAdoptionJson = "{\"userId\": 1, \"petId\": 2}";  

        doThrow(new RuntimeException("Error creating adoption")).when(adoptionService).newAdoption(1L, 2L);  

        mockMvc.perform(post("/api/adoptions")  
                .contentType(MediaType.APPLICATION_JSON)  
                .content(newAdoptionJson))  
                .andExpect(status().isBadRequest())  
                .andExpect(content().string("Error creating adoption"));  
    }  
}