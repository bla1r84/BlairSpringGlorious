package com.blair.BlairSpringGlorious.services.impl;

import com.blair.BlairSpringGlorious.model.Employee;
import com.blair.BlairSpringGlorious.model.Job;
import com.blair.BlairSpringGlorious.repositories.EmployeeRepository;
import com.blair.BlairSpringGlorious.repositories.JobRepository;
import com.blair.BlairSpringGlorious.services.EmployeeService;
import com.blair.BlairSpringGlorious.validators.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final ObjectFactory<EmployeeValidator> employeeValidator;

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findByCompleteName(String firstName, String lastName) {
        return employeeRepository.findByFullName(firstName, lastName).orElse(null);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Page<Employee> findAllPaged() {
        return employeeRepository.findAll(PageRequest.of(0, 2));
    }

    @Override
    public Employee create(Employee employee) {
        employeeValidator.getObject()
                .validate(StringUtils.isNotBlank(employee.getFirstName()))
                .validate(StringUtils.isNotBlank(employee.getLastName()))
                .validate(!(employee.getSalary() <= 0 || employee.getSalary() > 10_000))
                .throwIfFailure(() -> new RuntimeException("Invalid Employee"));
        return employeeRepository.save(employee);
    }

    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = UnexpectedRollbackException.class)
    public Job insertAutomatistis() {
        Job newJob = new Job();
        newJob.setDescription("Automatistis");
        jobRepository.save(newJob);
        throw new UnexpectedRollbackException("Something");
        // return newJob;
    }

    @Override
    public List<Employee> findBySalaryBetween(Long min, Long max) {
        return employeeRepository.findBySalaryBetween(min, max);
    }

    @Override
    public List<Employee> findBySalaryLessThanEqualAndLastNameStartingWith(Long max, String startingLetter) {
        return employeeRepository.findBySalaryLessThanEqualAndLastNameStartingWith(max, startingLetter);
    }

    @Override
    public List<Employee> findByLastNameLike(String like) {
        return employeeRepository.findByLastNameLike(like);
    }

    @Override
    public List<Employee> findFirst5BySalaryGreaterThan(Long min) {
        return employeeRepository.findFirst5BySalaryGreaterThan(min);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void transactionalMethod() {
        log.info("Running EmployeeServiceImpl.transactionalMethod");
    }

}
