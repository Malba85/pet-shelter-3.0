package com.codigofacilito.pet_shelter.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codigofacilito.pet_shelter.models.security.UserSecEntity;

import com.codigofacilito.pet_shelter.services.UserSecService;


@RestController
@RequestMapping("/api/security")
public class UserSecurityController {
    @Autowired
    private UserSecService userSecService;

    // Crear un nuevo usuario con roles
    @PostMapping
    public UserSecEntity createUser(@RequestBody UserSecEntity user) {
        return userSecService.createUser(user);
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<UserSecEntity> getAllUsers() {
        return userSecService.getAllUsers();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserSecEntity> getUserById(@PathVariable Long id) {
        Optional<UserSecEntity> user = userSecService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un usuario y sus roles
    @PutMapping("/{id}")
    public ResponseEntity<UserSecEntity> updateUser(@PathVariable Long id, @RequestBody UserSecEntity userDetails) {
        return ResponseEntity.ok(userSecService.updateUser(id, userDetails));
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userSecService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}


