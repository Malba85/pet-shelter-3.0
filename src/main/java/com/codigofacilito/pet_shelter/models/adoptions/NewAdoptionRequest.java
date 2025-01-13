package com.codigofacilito.pet_shelter.models.adoptions;

public class NewAdoptionRequest {
    private Long userId;
    private Long petId;

    // Getters y Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
}

