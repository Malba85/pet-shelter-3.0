package com.codigofacilito.pet_shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PetShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShelterApplication.class, args);
	}

}
