package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // This gets all the employees with the required available days
    List<Employee> getAllByDaysAvailableContains(DayOfWeek dayOfWeek);


}
