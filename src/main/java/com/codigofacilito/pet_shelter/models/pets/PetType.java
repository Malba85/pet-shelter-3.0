package com.codigofacilito.pet_shelter.models.pets;

public enum PetType {  
    DOG("Dog"),  
    CAT("Cat"),  
    RABBIT("Rabbit"),  
    HAMSTER("Hamster"); 

    private final String name;  

    PetType(String name) {  
        this.name = name;  
    }  

    public String getName() {  
        return name;  
    }  
}