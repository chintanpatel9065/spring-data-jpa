package org.chintanpatel.springdatajpa.repository;

import org.chintanpatel.springdatajpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository( "departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}