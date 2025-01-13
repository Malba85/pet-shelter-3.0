package com.codigofacilito.pet_shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codigofacilito.pet_shelter.models.adoptions.AdoptionEntity;

@Repository
public interface AdoptionRepository extends JpaRepository<AdoptionEntity, Long>{
    
}
