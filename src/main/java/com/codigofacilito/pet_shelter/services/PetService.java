package com.codigofacilito.pet_shelter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codigofacilito.pet_shelter.models.pets.NewPet;
import com.codigofacilito.pet_shelter.models.pets.PetEntity;
import com.codigofacilito.pet_shelter.models.pets.PetType;
import com.codigofacilito.pet_shelter.repositories.PetRepository;
import jakarta.annotation.PostConstruct;

@Service
public class PetService {

  private final PetRepository petRepository;

  public PetService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  public List<PetEntity> getPetEntities() {
    return petRepository.findAll();
  }

  public Optional<PetEntity> getPetById(Long id) {
    return petRepository.findById(id);
  }

  public PetEntity addPet(NewPet newPet) {
    return petRepository
        .saveAndFlush(new PetEntity(newPet.name(), newPet.petType(), newPet.age(), newPet.available()));
  }

  public void updatePet(Long id, PetEntity updatedPet) {
    Optional<PetEntity> pet = petRepository.findById(id);
    if (pet.isPresent()) {
      PetEntity existingPet = pet.get();
      existingPet.setName(updatedPet.getName());
      existingPet.setPetType(updatedPet.getPetType());
      existingPet.setAge(updatedPet.getAge());
      existingPet.setAvailable(updatedPet.isAvailable());
      petRepository.saveAndFlush(existingPet);
    }
  }

  public void deletePet(Long id) {
    petRepository.deleteById(id);
  }

  @PostConstruct
  void initDatabase() {
    petRepository.save(new PetEntity("Bobby", PetType.DOG, 4, true)); 
    petRepository.save(new PetEntity("Charly", PetType.DOG, 5, true));
    petRepository.save(new PetEntity("Mimi", PetType.CAT, 2, true));
    petRepository.save(new PetEntity("Luna", PetType.CAT, 1, true));
    petRepository.save(new PetEntity("Rocky", PetType.DOG, 6, false));
    petRepository.save(new PetEntity("Max", PetType.DOG, 3, true));
    petRepository.save(new PetEntity("Nala", PetType.CAT, 4, false));
    petRepository.save(new PetEntity("Simba", PetType.CAT, 5, true));
    petRepository.save(new PetEntity("Toby", PetType.DOG, 2, true));
    petRepository.save(new PetEntity("Bella", PetType.CAT, 3, true));
  }

}