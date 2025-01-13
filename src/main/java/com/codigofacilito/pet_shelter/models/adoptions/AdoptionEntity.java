package com.codigofacilito.pet_shelter.models.adoptions;

import java.time.LocalDate;
import java.util.Optional;

import com.codigofacilito.pet_shelter.models.pets.PetEntity;
import com.codigofacilito.pet_shelter.models.users.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AdoptionEntity {
    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // ● ID Usuario (ID Usuario): Referencia al usuario adoptante (relación
                             // many-to-one)

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet; // ● Mascota (ID Mascota): Referencia a la mascota adoptada (relación
                           // many-to-one)

    @Column(name = "adoption_date", nullable = false)
    private LocalDate adoptionDate; // ● Fecha Adopción (Date): Fecha en la que se realiza la adopción

    public AdoptionEntity() {}

    public AdoptionEntity(UserEntity user, PetEntity pet, LocalDate adoptionDate) {
        this.user = user;
        this.pet = pet;
        this.adoptionDate = adoptionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public Optional<PetEntity> map(Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }

}
