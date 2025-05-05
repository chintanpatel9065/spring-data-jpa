package org.chintanpatel.springdatajpa.validator;

import jakarta.validation.ConstraintValidator;
import org.chintanpatel.springdatajpa.model.Department;

public class DepartmentValidator implements ConstraintValidator<ValidDepartment, Department> {

    @Override
    public boolean isValid(Department department, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        return department != null && department.getDepartmentId() > 0;
    }
}
