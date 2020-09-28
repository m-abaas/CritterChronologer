package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Returns all the schedules for a specific Pet
    List<Schedule> getAllByPetsContains(Pet pet);
    // Returns all the schedules for a specific Employee
    List<Schedule> getAllByEmployeesContains(Employee employee);
    // Slightly different due to the the input nature
    List<Schedule> getAllByPetsIn(List<Pet> pets);
}

