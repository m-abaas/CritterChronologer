package com.udacity.jdnd.course3.critter.pet;


import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    public static Pet convertPetDTOToEntity(PetDTO petDTO)
    {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public static PetDTO convertPetToDTO(Pet pet)
    {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        // Handling exception case of the pet not having a customer
        try { petDTO.setOwnerId(pet.getCustomer().getId()); }
        catch (Exception ignored) { ; }
        return petDTO;
    }
    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        return convertPetToDTO(petService.save(convertPetDTOToEntity(petDTO), petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        PetDTO petDTO = new PetDTO();
        return convertPetToDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getPets();
        List<PetDTO> petDTOS = new ArrayList<>();

        // Looping over the pets list and converting item by item to DTO, then adding to the customerDTOs
        for (Pet pet : pets) {
            petDTOS.add(convertPetToDTO(pet));
        }
        return petDTOS;
    }


    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();

        // Looping over the pets list and converting item by item to DTO, then adding to the customerDTOs
        for (Pet pet : pets) {
            petDTOS.add(convertPetToDTO(pet));
        }
        return petDTOS;
    }
}
