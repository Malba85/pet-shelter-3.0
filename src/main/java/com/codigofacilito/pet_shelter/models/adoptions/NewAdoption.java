package com.codigofacilito.pet_shelter.models.adoptions;

import java.time.LocalDate;

import com.codigofacilito.pet_shelter.models.pets.PetEntity;
import com.codigofacilito.pet_shelter.models.users.UserEntity;

public record NewAdoption(UserEntity user, PetEntity pet, LocalDate adoptionDate) {

}
