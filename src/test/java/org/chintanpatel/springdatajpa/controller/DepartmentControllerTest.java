package org.chintanpatel.springdatajpa.controller;

import org.chintanpatel.springdatajpa.model.Department;
import org.chintanpatel.springdatajpa.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;

    private Department department;
    private List<Department> departments;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();

        department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("IT");

        Department department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setDepartmentName("HR");

        departments = Arrays.asList(department, department2);
    }

    @Test
    public void testListDepartment() throws Exception {
        // Setup
        when(departmentService.getAllDepartmentList()).thenReturn(departments);

        // Execute and Verify
        mockMvc.perform(get("/departments/listDepartment"))
                .andExpect(status().isOk())
                .andExpect(view().name("department-form"))
                .andExpect(model().attributeExists("department"))
                .andExpect(model().attributeExists("departmentList"));

        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void testInsertOrUpdateDepartmentSuccess() throws Exception {
        // Setup
        doNothing().when(departmentService).addDepartment(any(Department.class));

        // Execute and Verify
        mockMvc.perform(post("/departments/insertOrUpdateDepartment")
                .flashAttr("department", department))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments/listDepartment"));

        verify(departmentService, times(1)).addDepartment(any(Department.class));
    }

    @Test
    public void testInsertOrUpdateDepartmentValidationError() throws Exception {
        // Setup
        Department invalidDepartment = new Department();
        invalidDepartment.setDepartmentId(1L);
        invalidDepartment.setDepartmentName(""); // Invalid: empty name

        when(departmentService.getAllDepartmentList()).thenReturn(departments);

        // Execute and Verify
        mockMvc.perform(post("/departments/insertOrUpdateDepartment")
                .flashAttr("department", invalidDepartment))
                .andExpect(status().isOk())
                .andExpect(view().name("department-form"))
                .andExpect(model().attributeExists("departmentList"));

        verify(departmentService, never()).addDepartment(any(Department.class));
        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void testManageDepartment() throws Exception {
        // Setup
        when(departmentService.getDepartmentById(anyLong())).thenReturn(department);
        when(departmentService.getAllDepartmentList()).thenReturn(departments);

        // Execute and Verify
        mockMvc.perform(get("/departments/manageDepartment")
                .param("departmentId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("department-form"))
                .andExpect(model().attributeExists("department"))
                .andExpect(model().attributeExists("departmentList"));

        verify(departmentService, times(1)).getDepartmentById(1L);
        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        // Setup
        doNothing().when(departmentService).deleteDepartmentById(anyLong());

        // Execute and Verify
        mockMvc.perform(get("/departments/deleteDepartment")
                .param("departmentId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments/listDepartment"));

        verify(departmentService, times(1)).deleteDepartmentById(1L);
    }
}