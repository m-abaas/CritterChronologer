package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet save(Pet pet, Long ownerID)
    {
        Pet returnedPet = new Pet();
        if(customerRepository.existsById(ownerID)) {
            pet.setCustomer(customerRepository.getOne(ownerID));
            returnedPet = petRepository.save(pet);
            Customer customer = customerRepository.getOne(ownerID);
            customer.addPet(returnedPet);
        }
        else {
            // Even if the customer doesn't exists, just add the Pet
            returnedPet = petRepository.save(pet);
        }
        return returnedPet;
    }

    public Pet getPet(Long petId)
    {
        return petRepository.getOne(petId);
    }

    public List<Pet> getPets()
    {

        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(long ownerId)
    {
        return petRepository.findPetsByCustomerId(ownerId);
    }
}
