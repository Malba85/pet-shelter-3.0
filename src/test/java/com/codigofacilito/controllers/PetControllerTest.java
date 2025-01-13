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

import com.codigofacilito.pet_shelter.controllers.PetController;
import com.codigofacilito.pet_shelter.models.pets.NewPet;
import com.codigofacilito.pet_shelter.models.pets.PetEntity;
import com.codigofacilito.pet_shelter.services.PetService;

import java.util.Arrays;  
import java.util.Optional;  

public class PetControllerTest {  

    @Autowired  
    private MockMvc mockMvc;  

    @Mock  
    private PetService petService;  

    @InjectMocks  
    private PetController petController;  

    @BeforeEach  
    public void setUp() {  
        MockitoAnnotations.openMocks(this);  
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();  
    }  

    @Test  
    public void testGetAllPets() throws Exception {  
        when(petService.getPetEntities()).thenReturn(Arrays.asList(new PetEntity(), new PetEntity()));  

        mockMvc.perform(get("/api/pets"))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$").isArray());  
    }  

    @Test  
    public void testGetPetById() throws Exception {  
        Long id = 1L;  
        when(petService.getPetById(id)).thenReturn(Optional.of(new PetEntity()));  

        mockMvc.perform(get("/api/pets/{id}", id))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$").isNotEmpty());  
    }  

    @Test  
    public void testGetPetByIdNotFound() throws Exception {  
        Long id = 1L;  
        when(petService.getPetById(id)).thenReturn(Optional.empty());  

        mockMvc.perform(get("/api/pets/{id}", id))  
                .andExpect(status().isNotFound());  
    }  

    @Test  
    public void testNewPet() throws Exception {  
        String newPetJson = "{\"name\": \"Dog\", \"type\": \"Dog\"}"; // Ajusta según tu clase NewPet  

        when(petService.addPet(any(NewPet.class))).thenReturn(new PetEntity());  

        mockMvc.perform(post("/api/pets")  
                .contentType(MediaType.APPLICATION_JSON)  
                .content(newPetJson))  
                .andExpect(status().isOk());  
    }  

    @Test  
    public void testUpdatePet() throws Exception {  
        Long id = 1L;  
        String updatedPetJson = "{\"name\": \"UpdatedDog\", \"type\": \"Dog\"}";  

        mockMvc.perform(put("/api/pets/{id}", id)  
                .contentType(MediaType.APPLICATION_JSON)  
                .content(updatedPetJson))  
                .andExpect(status().isOk());  

        verify(petService, times(1)).updatePet(eq(id), any(PetEntity.class));  
    }  

    @Test  
    public void testDeletePet() throws Exception {  
        Long id = 1L;  

        mockMvc.perform(delete("/api/pets/{id}", id))  
                .andExpect(status().isOk());  

        verify(petService, times(1)).deletePet(id);  
    }  
}