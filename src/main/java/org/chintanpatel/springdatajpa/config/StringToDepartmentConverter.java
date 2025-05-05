package org.chintanpatel.springdatajpa.config;

import org.chintanpatel.springdatajpa.model.Department;
import org.chintanpatel.springdatajpa.service.DepartmentService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartmentConverter implements Converter<String, Department> {

    private final DepartmentService departmentService;

    public StringToDepartmentConverter(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public Department convert(String source) {
        try {
            Long departmentId = Long.parseLong(source);
            return departmentService.getDepartmentById(departmentId);
        } catch (NumberFormatException e) {
            return null;
        }

    }
}
