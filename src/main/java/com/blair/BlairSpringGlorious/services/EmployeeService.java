package com.blair.BlairSpringGlorious.services;

import com.blair.BlairSpringGlorious.model.Employee;
import com.blair.BlairSpringGlorious.model.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Employee findById(Long id);

    void deleteById(Long id);

    Employee findByCompleteName(String firstName, String lastName);

    List<Employee> findAll();

    Page<Employee> findAllPaged();

    Employee create(Employee employee);

    Job insertAutomatistis();

    List<Employee> findBySalaryBetween(Long min, Long max);

    List<Employee> findBySalaryLessThanEqualAndLastNameStartingWith(Long max, String startingLetter);

    List<Employee> findByLastNameLike(String like);

    List<Employee> findFirst5BySalaryGreaterThan(Long min);

}
