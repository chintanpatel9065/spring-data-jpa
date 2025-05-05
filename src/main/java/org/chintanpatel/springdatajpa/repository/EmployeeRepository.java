package org.chintanpatel.springdatajpa.repository;

import org.chintanpatel.springdatajpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository( "employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}