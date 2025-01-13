package com.codigofacilito.pet_shelter.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codigofacilito.pet_shelter.models.adoptions.AdoptionEntity;
import com.codigofacilito.pet_shelter.models.users.UserEntity;
import com.codigofacilito.pet_shelter.models.pets.PetEntity;
import com.codigofacilito.pet_shelter.repositories.AdoptionRepository;
import com.codigofacilito.pet_shelter.repositories.PetRepository;
import com.codigofacilito.pet_shelter.repositories.UserRepository;

@Service
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, PetRepository petRepository,
            UserRepository userRepository) {
        this.adoptionRepository = adoptionRepository;
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public List<AdoptionEntity> getAllAdoptions() {
        return adoptionRepository.findAll();
    }

    public AdoptionEntity getAdoptionById(Long id) {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption not found"));
    }

    @Transactional
    public void newAdoption(Long userId, Long petId) {
        // Lógica para crear una nueva adopción
        // Asegúrate de manejar las excepciones adecuadamente
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PetEntity pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (!pet.isAvailable()) {
            throw new RuntimeException("The pet is not available for adoption");
        }

        // Crear la adopción
        AdoptionEntity adoption = new AdoptionEntity(user, pet, LocalDate.now());
        adoptionRepository.save(adoption);

        // Marcar mascota como NO disponible
        pet.setAvailable(false);
        petRepository.save(pet);
    }

    // public void save(AdoptionEntity adoption) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'save'");
    // }

}