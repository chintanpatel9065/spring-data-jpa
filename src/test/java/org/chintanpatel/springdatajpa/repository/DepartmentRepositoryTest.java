package org.chintanpatel.springdatajpa.repository;

import org.chintanpatel.springdatajpa.config.TestConfig;
import org.chintanpatel.springdatajpa.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setup() {
        departmentRepository.deleteAll();
    }

    @Test
    public void testSaveDepartment() {
        // Create a new department
        Department department = new Department();
        department.setDepartmentName("IT");

        // Save the department
        departmentRepository.save(department);

        // Verify the department was saved with an ID
        assertNotNull(department.getDepartmentId());
    }

    @Test
    public void testFindAllDepartments() {
        // Create and save two departments
        Department department1 = new Department();
        department1.setDepartmentName("IT");
        departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setDepartmentName("HR");
        departmentRepository.save(department2);

        // Get all departments
        List<Department> departments = departmentRepository.findAll();

        // Verify both departments are returned
        assertEquals(2, departments.size());
    }

    @Test
    public void testFindDepartmentById() {
        // Create and save a department
        Department department = new Department();
        department.setDepartmentName("Finance");
        departmentRepository.save(department);

        // Find the department by ID
        Optional<Department> foundDepartment = departmentRepository.findById(department.getDepartmentId());

        // Verify the department is found
        assertTrue(foundDepartment.isPresent());
        assertEquals("Finance", foundDepartment.get().getDepartmentName());
    }

    @Test
    public void testDeleteDepartment() {
        // Create and save a department
        Department department = new Department();
        department.setDepartmentName("Marketing");
        departmentRepository.save(department);

        // Get the ID
        Long id = department.getDepartmentId();

        // Delete the department
        departmentRepository.deleteById(id);

        // Verify the department is deleted
        Optional<Department> foundDepartment = departmentRepository.findById(id);
        assertFalse(foundDepartment.isPresent());
    }
}
