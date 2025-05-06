package org.chintanpatel.springdatajpa.service;

import org.chintanpatel.springdatajpa.model.Department;
import org.chintanpatel.springdatajpa.repository.DepartmentRepository;
import org.chintanpatel.springdatajpa.service.implement.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    public void setup() {
        department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("IT");
    }

    @Test
    public void testAddDepartment() {
        // Setup
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        // Execute
        departmentService.addDepartment(department);

        // Verify
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void testGetAllDepartmentList() {
        // Setup
        Department department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setDepartmentName("HR");
        
        List<Department> departments = Arrays.asList(department, department2);
        when(departmentRepository.findAll()).thenReturn(departments);

        // Execute
        List<Department> result = departmentService.getAllDepartmentList();

        // Verify
        assertEquals(2, result.size());
        assertEquals("IT", result.get(0).getDepartmentName());
        assertEquals("HR", result.get(1).getDepartmentName());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void testGetDepartmentById() {
        // Setup
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        // Execute
        Department result = departmentService.getDepartmentById(1L);

        // Verify
        assertNotNull(result);
        assertEquals("IT", result.getDepartmentName());
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetDepartmentByIdNotFound() {
        // Setup
        when(departmentRepository.findById(99L)).thenReturn(Optional.empty());

        // Execute
        Department result = departmentService.getDepartmentById(99L);

        // Verify
        assertNull(result);
        verify(departmentRepository, times(1)).findById(99L);
    }

    @Test
    public void testDeleteDepartmentById() {
        // Setup
        doNothing().when(departmentRepository).deleteById(1L);

        // Execute
        departmentService.deleteDepartmentById(1L);

        // Verify
        verify(departmentRepository, times(1)).deleteById(1L);
    }
}