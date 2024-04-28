package com.example.rqchallenge.controller;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeByIdResponse;
import com.example.rqchallenge.entity.EmployeeRequest;
import com.example.rqchallenge.entity.ErrorResponse;
import com.example.rqchallenge.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee("1", "Tiger Nixon", 320800, "61", ""),
                new Employee("2", "Garrett Winters", 170750, "63", "")

        );
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].employee_name").value("Tiger Nixon"))
                .andExpect(jsonPath("$[1].employee_name").value("Garrett Winters"));
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeesByNameSearch() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee("1", "Tiger Nixon", 320800, "61", ""),
                new Employee("2", "Garrett Winters", 170750, "63", "")
        );
        when(employeeService.getEmployeesByNameSearch("Tiger Nixon")).thenReturn(employees);

        mockMvc.perform(get("/employees/search?name=Tiger Nixon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].employee_name").value("Tiger Nixon"));
        verify(employeeService, times(1)).getEmployeesByNameSearch("Tiger Nixon");
    }

    @Test
    void testGetHighestSalaryOfEmployees() throws Exception {
        when(employeeService.getHighestSalaryOfEmployees()).thenReturn(320800);

        mockMvc.perform(get("/employees/highest-salary"))
                .andExpect(status().isOk())
                .andExpect(content().string("320800"));
        verify(employeeService, times(1)).getHighestSalaryOfEmployees();
    }

}

