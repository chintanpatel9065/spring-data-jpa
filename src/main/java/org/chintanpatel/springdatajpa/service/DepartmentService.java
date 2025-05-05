package org.chintanpatel.springdatajpa.service;

import org.chintanpatel.springdatajpa.model.Department;

import java.util.List;

public interface DepartmentService {

    void addDepartment(Department department);

    List<Department>getAllDepartmentList();

    Department getDepartmentById(Long departmentId);

    void deleteDepartmentById(Long departmentId);
}
