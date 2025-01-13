package com.codigofacilito.pet_shelter.models.pets;

public record NewPet(String name, PetType petType, Integer age, Boolean available) {
    
}
