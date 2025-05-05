package org.chintanpatel.springdatajpa.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DepartmentValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDepartment {

    String message() default "Please Select Department";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
