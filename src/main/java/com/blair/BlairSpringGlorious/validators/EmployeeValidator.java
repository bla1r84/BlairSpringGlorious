package com.blair.BlairSpringGlorious.validators;

import com.blair.BlairSpringGlorious.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmployeeValidator {

    private boolean validationState = true;

    public EmployeeValidator validateFirstName(Employee employee) {
        validationState = StringUtils.isNotBlank(employee.getFirstName());
        return this;
    }

    public EmployeeValidator validateLastName(Employee employee) {
        if (validationState) {
            validationState = StringUtils.isNotBlank(employee.getLastName());
        }
        return this;
    }

    public EmployeeValidator validateSalary(Employee employee) {
        if (validationState) {
            Long employeeSalary = employee.getSalary();
            validationState = !(employeeSalary <= 0 || employeeSalary > 10_000);
        }
        return this;
    }

    public EmployeeValidator validate(boolean validation) {
        if (validationState) {
            validationState = validation;
        }
        return this;
    }

    public <X extends RuntimeException> void throwIfFailure(Supplier<? extends X> exceptionSupplier) {
        if (!validationState) {
            throw exceptionSupplier.get();
        }
    }

}
