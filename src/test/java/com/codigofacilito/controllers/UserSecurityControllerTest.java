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

import com.codigofacilito.pet_shelter.controllers.UserSecurityController;
import com.codigofacilito.pet_shelter.models.security.UserSecEntity;
import com.codigofacilito.pet_shelter.services.UserSecService;

import java.util.Arrays;
import java.util.Optional;

public class UserSecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserSecService userSecService;

    @InjectMocks
    private UserSecurityController userSecurityController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userSecurityController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        String newUserJson = "{\"username\": \"user1\", \"password\": \"pass\"}";

        when(userSecService.createUser(any(UserSecEntity.class))).thenReturn(new UserSecEntity());

        mockMvc.perform(post("/api/security")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userSecService.getAllUsers()).thenReturn(Arrays.asList(new UserSecEntity(), new UserSecEntity()));

        mockMvc.perform(get("/api/security"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetUserById() throws Exception {
        Long id = 1L;
        when(userSecService.getUserById(id)).thenReturn(Optional.of(new UserSecEntity()));

        mockMvc.perform(get("/api/security/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        Long id = 1L;
        when(userSecService.getUserById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/security/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUser() throws Exception {
        Long id = 1L;
        String updatedUserJson = "{\"username\": \"updatedUser\", \"password\": \"newPass\"}";

        when(userSecService.updateUser(eq(id), any(UserSecEntity.class))).thenReturn(new UserSecEntity());

        mockMvc.perform(put("/api/security/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedUserJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/security/{id}", id))
                .andExpect(status().isNoContent());

        verify(userSecService, times(1)).deleteUser(id);
    }
}
