package com.codigofacilito.pet_shelter.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import com.codigofacilito.pet_shelter.models.adoptions.AdoptionEntity;
import com.codigofacilito.pet_shelter.models.adoptions.NewAdoptionRequest;
import com.codigofacilito.pet_shelter.services.AdoptionService;
import com.codigofacilito.pet_shelter.services.PetService;
import com.codigofacilito.pet_shelter.services.UserService;

@RestController
@RequestMapping("/api/adoptions")
public class AdoptionController {

    // private final PetService petService;
    // private final UserService userService;
    private final AdoptionService adoptionService;

    public AdoptionController(PetService petService, UserService userService, AdoptionService adoptionService) {
        // this.petService = petService;
        // this.userService = userService;
        this.adoptionService = adoptionService;
    }

    @GetMapping
    public List<AdoptionEntity> all() {
        return adoptionService.getAllAdoptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionEntity> getAdoption(@PathVariable("id") Long id) {
        Optional<AdoptionEntity> adoptionEntity = Optional.ofNullable(adoptionService.getAdoptionById(id));
        return adoptionEntity
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Los datos userId=1&petId=2 se envían como parámetros en el cuerpo.
    // @RequestParam
    // @PostMapping
    // public ResponseEntity<?> newAdoption(
    // @RequestParam("userId") Long userId,
    // @RequestParam("petId") Long petId) {
    // try {
    // adoptionService.newAdoption(userId, petId);
    // return ResponseEntity.ok().body("Adoption created successfully");
    // } catch (RuntimeException e) {
    // return ResponseEntity.badRequest().body(e.getMessage());
    // }
    // }

    // Los datos userId=1&petId=2 se envían como un objeto JSON en el cuerpo.
    // @RequestBody
    @PostMapping
    public ResponseEntity<?> newAdoption(@RequestBody NewAdoptionRequest request) {
        try {
            adoptionService.newAdoption(request.getUserId(), request.getPetId());
            return ResponseEntity.ok().body("Adoption created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
