package com.codigofacilito.pet_shelter.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codigofacilito.pet_shelter.models.security.UserSecEntity;

@Repository
public interface UserSecRepository extends JpaRepository<UserSecEntity, Long>{
    UserSecEntity findByEmail(String email);
}
