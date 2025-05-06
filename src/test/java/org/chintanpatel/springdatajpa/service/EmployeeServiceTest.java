package org.chintanpatel.springdatajpa.service;

import org.chintanpatel.springdatajpa.model.Department;
import org.chintanpatel.springdatajpa.model.Employee;
import org.chintanpatel.springdatajpa.repository.EmployeeRepository;
import org.chintanpatel.springdatajpa.service.implement.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private Department department;

    @BeforeEach
    public void setup() {
        department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("IT");

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstName("John");
        employee.setMiddleName("A");
        employee.setLastName("Doe");
        employee.setGender("Male");
        employee.setProgrammingLanguage(new String[]{"Java", "Python"});
        employee.setEmail("john.doe@example.com");
        employee.setMobile("1234567890");
        employee.setBirthDate(LocalDate.of(1990, 1, 1));
        employee.setUserName("johndoe");
        employee.setPassword("password123");
        employee.setDepartment(department);
    }

    @Test
    public void testAddEmployee() {
        // Setup
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Execute
        employeeService.addEmployee(employee);

        // Verify
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetAllEmployeeList() {
        // Setup
        Employee employee2 = new Employee();
        employee2.setEmployeeId(2L);
        employee2.setFirstName("Jane");
        employee2.setMiddleName("B");
        employee2.setLastName("Smith");
        employee2.setGender("Female");
        employee2.setProgrammingLanguage(new String[]{"JavaScript", "C++"});
        employee2.setEmail("jane.smith@example.com");
        employee2.setMobile("0987654321");
        employee2.setBirthDate(LocalDate.of(1992, 2, 2));
        employee2.setUserName("janesmith");
        employee2.setPassword("password456");
        employee2.setDepartment(department);
        
        List<Employee> employees = Arrays.asList(employee, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);

        // Execute
        List<Employee> result = employeeService.getAllEmployeeList();

        // Verify
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeById() {
        // Setup
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Execute
        Employee result = employeeService.getEmployeeById(1L);

        // Verify
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        // Setup
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        // Execute
        Employee result = employeeService.getEmployeeById(99L);

        // Verify
        assertNull(result);
        verify(employeeRepository, times(1)).findById(99L);
    }

    @Test
    public void testDeleteEmployeeById() {
        // Setup
        doNothing().when(employeeRepository).deleteById(1L);

        // Execute
        employeeService.deleteEmployeeById(1L);

        // Verify
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}