package com.blair.BlairSpringGlorious.hateoas.assemblers;

import com.blair.BlairSpringGlorious.controllers.JobController;
import com.blair.BlairSpringGlorious.hateoas.entitymodels.JobModel;
import com.blair.BlairSpringGlorious.model.Job;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class JobModelAssembler implements RepresentationModelAssembler<Job, JobModel> {

    @Override
    public JobModel toModel(@Nullable Job job) {
        if (Objects.isNull(job)) {
            return null;
        }

        return new JobModel(job.getDescription()).add(
                linkTo(methodOn(JobController.class).getJobByID(job.getId())).withSelfRel()
                        .andAffordance(afford(methodOn(JobController.class).updateJob(job.getId(), null)))
                        .andAffordance(afford(methodOn(JobController.class).deleteJob(job.getId()))),
                linkTo(methodOn(JobController.class).getAllJobs()).withRel("jobs"));
    }

}