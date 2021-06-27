package com.blair.BlairSpringGlorious.services;

import com.blair.BlairSpringGlorious.model.Job;

import java.util.List;

public interface JobService {

    Job findById(Long id);

    List<Job> findAll();

    Job create(Job job);

    void deleteById(Long id);

}
