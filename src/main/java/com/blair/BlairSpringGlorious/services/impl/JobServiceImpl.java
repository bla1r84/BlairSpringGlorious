package com.blair.BlairSpringGlorious.services.impl;

import com.blair.BlairSpringGlorious.exceptions.NotFoundException;
import com.blair.BlairSpringGlorious.model.Job;
import com.blair.BlairSpringGlorious.repositories.JobRepository;
import com.blair.BlairSpringGlorious.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final EmployeeServiceImpl employeeService;

    public Job findById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new NotFoundException(Job.class, id));
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job create(Job job) {
        try {
            return jobRepository.save(job);
        } catch (DataAccessException dae) {
            log.info("DataAccessException caught: {}", dae.getMessage());
            throw dae;
        }
    }

    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    @Transactional
    public void transactionalMethod() {
        employeeService.transactionalMethod();
    }

}
