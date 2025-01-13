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

import com.codigofacilito.pet_shelter.controllers.UserController;
import com.codigofacilito.pet_shelter.models.users.NewUser;
import com.codigofacilito.pet_shelter.models.users.UserEntity;
import com.codigofacilito.pet_shelter.services.UserService;

import java.util.Arrays;  
import java.util.Optional;  

public class UserControllerTest {  

    @Autowired  
    private MockMvc mockMvc;  

    @Mock  
    private UserService userService;  

    @InjectMocks  
    private UserController userController;  

    @BeforeEach  
    public void setUp() {  
        MockitoAnnotations.openMocks(this);  
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();  
    }  

    @Test  
    public void testCreateUser() throws Exception {  
        String newUserJson = "{\"username\": \"user1\", \"password\": \"pass\"}"; // Ajusta según tu clase NewUser  
        when(userService.addUser(any(NewUser.class))).thenReturn(new UserEntity());  

        mockMvc.perform(post("/api/users")  
                .contentType(MediaType.APPLICATION_JSON)  
                .content(newUserJson))  
                .andExpect(status().isOk());  
    }  

    @Test  
    public void testGetAllUsers() throws Exception {  
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new UserEntity(), new UserEntity()));  

        mockMvc.perform(get("/api/users"))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$").isArray());  
    }  

    @Test  
    public void testGetUserById() throws Exception {  
        Long id = 1L;  
        when(userService.getUserById(id)).thenReturn(Optional.of(new UserEntity()));  

        mockMvc.perform(get("/api/users/{id}", id))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$").isNotEmpty());  
    }  

    @Test  
    public void testGetUserByIdNotFound() throws Exception {  
        Long id = 1L;  
        when(userService.getUserById(id)).thenReturn(Optional.empty());  

        mockMvc.perform(get("/api/users/{id}", id))  
                .andExpect(status().isNotFound());  
    }  

    @Test  
    public void testUpdateUser() throws Exception {  
        Long id = 1L;  
        String updatedUserJson = "{\"username\": \"updatedUser\", \"password\": \"newPass\"}";  

        mockMvc.perform(put("/api/users/{id}", id)  
                .contentType(MediaType.APPLICATION_JSON)  
                .content(updatedUserJson))  
                .andExpect(status().isOk());  

        verify(userService, times(1)).updateUser(eq(id), any(UserEntity.class));  
    }  

    @Test  
    public void testDeleteUser() throws Exception {  
        Long id = 1L;  

        mockMvc.perform(delete("/api/users/{id}", id))  
                .andExpect(status().isNoContent());  

        verify(userService, times(1)).deleteUser(id);  
    }  
}