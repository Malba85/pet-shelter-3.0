package com.codigofacilito.pet_shelter.models.pets;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class PetEntity {

    private @Id @GeneratedValue Long id; // Unique identifier

    @NotNull(message = "The name cannot be null")
    @Size(min = 2, message = "The name must have at least 2 character")
    private String name; // Name of the pet

    // Enum representing the type of pet (no relationship here)
    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    @NotNull(message = "Type of pet cannot be null")
    private PetType petType; // Reference to the PetType enum

    @Min(0)
    @Max(30)
    private Integer age; // Age of the pet in years

    @NotNull(message = "Availability status must be specified")
    private Boolean available; // Availability status for adoption

    public PetEntity() {
    }

    public PetEntity(String name, PetType petType, Integer age, Boolean available) {
        this.name = name;
        this.petType = petType;
        this.age = age;
        this.available = available;
    }

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Pet [id=" + id + ", name=" + name + ", petType=" + petType.getName() +
                ", age=" + age + ", available=" + available + "]";
    }

}
