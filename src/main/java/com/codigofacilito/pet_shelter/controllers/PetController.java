package com.codigofacilito.pet_shelter.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codigofacilito.pet_shelter.models.pets.NewPet;
import com.codigofacilito.pet_shelter.models.pets.PetEntity;
import com.codigofacilito.pet_shelter.services.PetService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<PetEntity> all() {
        return petService.getPetEntities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetEntity> getPetEntity(@PathVariable("id") Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PetEntity newPet(@RequestBody NewPet newpet) {
        return petService.addPet(newpet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable("id") Long id, @Valid @RequestBody PetEntity pet,
            BindingResult result) {

        petService.updatePet(id, pet);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public List<PetEntity> deletePet(@PathVariable("id") Long id) {
        petService.deletePet(id);
        return petService.getPetEntities();
    }
}