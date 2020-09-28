package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    private String phoneNumber;
    private String notes;
    @OneToMany(targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Pet> pets;

    public void addPet(Pet pet)
    {
        if(this.getPets() == null) {
            pets = new ArrayList<>();
        }
        this.pets.add(pet);
    }

    // Getters and setters
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<Pet> getPets() { return pets; }
    public void setPets(List<Pet> pets) { this.pets = pets; }
}
