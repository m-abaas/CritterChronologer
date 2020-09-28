package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // This gets a list of pets by the ownerID
    List<Pet> findPetsByCustomerId(Long ownerId);


}
