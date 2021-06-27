package com.blair.BlairSpringGlorious.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "Employee.findByFullName", query = "from Employee e where e.firstName=?1 and e.lastName=?2")
})
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

}
