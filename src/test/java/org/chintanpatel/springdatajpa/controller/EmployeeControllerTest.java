package org.chintanpatel.springdatajpa.controller;

import org.chintanpatel.springdatajpa.model.Department;
import org.chintanpatel.springdatajpa.model.Employee;
import org.chintanpatel.springdatajpa.service.DepartmentService;
import org.chintanpatel.springdatajpa.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    private Employee employee;
    private Department department;
    private List<Employee> employees;
    private List<Department> departments;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("IT");

        Department department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setDepartmentName("HR");

        departments = Arrays.asList(department, department2);

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
        employee2.setDepartment(department2);

        employees = Arrays.asList(employee, employee2);
    }

    @Test
    public void testListEmployee() throws Exception {
        // Setup
        when(employeeService.getAllEmployeeList()).thenReturn(employees);
        when(departmentService.getAllDepartmentList()).thenReturn(departments);

        // Execute and Verify
        mockMvc.perform(get("/employees/listEmployee"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attributeExists("departmentList"))
                .andExpect(model().attributeExists("employeeList"));

        verify(employeeService, times(1)).getAllEmployeeList();
        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void testInsertOrUpdateEmployeeSuccess() throws Exception {
        // Setup
        doNothing().when(employeeService).addEmployee(any(Employee.class));

        // Execute and Verify
        mockMvc.perform(post("/employees/insertOrUpdateEmployee")
                .flashAttr("employee", employee))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees/listEmployee"));

        verify(employeeService, times(1)).addEmployee(any(Employee.class));
    }

    @Test
    public void testInsertOrUpdateEmployeeValidationError() throws Exception {
        // Setup
        Employee invalidEmployee = new Employee();
        invalidEmployee.setEmployeeId(1L);
        // Missing required fields will cause validation errors

        when(employeeService.getAllEmployeeList()).thenReturn(employees);
        when(departmentService.getAllDepartmentList()).thenReturn(departments);

        // Execute and Verify
        mockMvc.perform(post("/employees/insertOrUpdateEmployee")
                .flashAttr("employee", invalidEmployee))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("departmentList"))
                .andExpect(model().attributeExists("employeeList"));

        verify(employeeService, never()).addEmployee(any(Employee.class));
        verify(employeeService, times(1)).getAllEmployeeList();
        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void testManageEmployee() throws Exception {
        // Setup
        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);
        when(employeeService.getAllEmployeeList()).thenReturn(employees);
        when(departmentService.getAllDepartmentList()).thenReturn(departments);

        // Execute and Verify
        mockMvc.perform(get("/employees/manageEmployee")
                .param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attributeExists("departmentList"))
                .andExpect(model().attributeExists("employeeList"));

        verify(employeeService, times(1)).getEmployeeById(1L);
        verify(employeeService, times(1)).getAllEmployeeList();
        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        // Setup
        doNothing().when(employeeService).deleteEmployeeById(anyLong());

        // Execute and Verify
        mockMvc.perform(get("/employees/deleteEmployee")
                .param("employeeId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees/listEmployee"));

        verify(employeeService, times(1)).deleteEmployeeById(1L);
    }
}