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
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PetRepository petRepository;

    public Customer save(Customer customer, List<Long> petIds) {
        if (petIds != null && !petIds.isEmpty()) {
           // List<Pet> pets = new ArrayList<>();
            for (Long petId : petIds) {
                if (petRepository.existsById(petId) && petRepository.getOne(petId).getCustomer() == null) {
                    Pet retrievedPetObject = petRepository.getOne(petId);
                    customer.addPet(retrievedPetObject);
                }
            }
            customer.getPets().forEach(pet -> pet.setCustomer(customer));
            }
        return customerRepository.save(customer);
        }
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(Long petId) {
        return petRepository.getOne(petId).getCustomer();
    }
}

