package com.codigofacilito.pet_shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codigofacilito.pet_shelter.models.pets.PetEntity;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

}