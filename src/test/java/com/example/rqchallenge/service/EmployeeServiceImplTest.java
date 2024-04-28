package com.example.rqchallenge.service;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeByIdResponse;
import com.example.rqchallenge.entity.EmployeeRequest;
import com.example.rqchallenge.entity.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(restTemplate);
    }

    @Test
    void testGetAllEmployees() {
        // Mock response from the API
        EmployeeResponse mockResponse = new EmployeeResponse();
        List<Employee> employees = Arrays.asList(
                new Employee("1", "Tiger Nixon", 320800, "61", ""),
                new Employee("2", "Garrett Winters", 170750, "63", "")
        );
        mockResponse.setData(employees);
        when(restTemplate.getForObject(anyString(), eq(EmployeeResponse.class))).thenReturn(mockResponse);

        // Call the service method
        List<Employee> result = employeeService.getAllEmployees();

        // Verify that the service method returns the expected result
        assertEquals(employees, result);
    }

    @Test
    void testGetEmployeesByNameSearch() {
        // Mock response from the API
        EmployeeResponse mockResponse = new EmployeeResponse();
        List<Employee> employees = Arrays.asList(
                new Employee("1", "Tiger Nixon", 320800, "61", ""),
                new Employee("2", "Garrett Winters", 170750, "63", "")
        );
        mockResponse.setData(employees);
        when(restTemplate.getForObject(anyString(), eq(EmployeeResponse.class))).thenReturn(mockResponse);

        // Call the service method
        String nameToSearch = "Garrett Winters";
        List<Employee> result = employeeService.getEmployeesByNameSearch(nameToSearch);

        // Verify that the service method returns the expected result
        assertEquals(1, result.size());
        assertEquals("Garrett Winters", result.get(0).getEmployeeName());;
    }

    @Test
    void testGetEmployeeById() {
        // Mock response from the API
        EmployeeByIdResponse mockResponse = new EmployeeByIdResponse();
        Employee employee = new Employee("1", "Tiger Nixon", 320800, "61", "");
        mockResponse.setData(employee);
        when(restTemplate.getForObject(anyString(), eq(EmployeeByIdResponse.class))).thenReturn(mockResponse);

        // Call the service method
        String idToFetch = "1";
        EmployeeByIdResponse result = employeeService.getEmployeeById(idToFetch);

        // Verify that the service method returns the expected result
        assertEquals(employee, result.getData());
    }

    @Test
    void testGetHighestSalaryOfEmployees() {
        EmployeeResponse mockResponse = new EmployeeResponse();
        List<Employee> employees = Arrays.asList(
                new Employee("1", "Tiger Nixon", 320800, "61", ""),
                new Employee("2", "Garrett Winters", 320809, "63", "")
        );
        mockResponse.setData(employees);
        when(restTemplate.getForObject(anyString(),eq(EmployeeResponse.class))).thenReturn(mockResponse);

        // Call the service method
        int result = employeeService.getHighestSalaryOfEmployees();

        // Verify that the service method returns the expected result
        assertEquals(320809, result);;
    }

    @Test
    void testGetTop10HighestEarningEmployeeNames() {
        // Mock response from the API
        EmployeeResponse mockResponse = new EmployeeResponse();
        List<Employee> employees = Arrays.asList(
                new Employee("1", "Tiger Nixon", 1, "61", ""),
                new Employee("2", "Tiger Nixon", 2, "61", ""),
                new Employee("3", "Tiger Nixon", 3, "61", ""),
                new Employee("4", "Tiger Nixon", 4, "61", ""),
                new Employee("5", "Tiger Nixon", 5, "61", ""),
                new Employee("6", "Tiger Nixon", 6, "61", ""),
                new Employee("7", "Tiger Nixon", 7, "61", ""),
                new Employee("8", "Tiger Nixon", 8, "61", ""),
                new Employee("9", "Tiger Nixon", 9, "61", ""),
                new Employee("10", "Tiger Nixon", 10, "61", ""),
                new Employee("11", "Tiger Nixon", 11, "61", "")
        );
        mockResponse.setData(employees);
        when(restTemplate.getForObject(anyString(), eq(EmployeeResponse.class))).thenReturn(mockResponse);

        // Call the service method
        List<Employee> result = employeeService.getTop10HighestEarningEmployeeNames();

        // Verify that the service method returns the top 10 highest earning employees
        assertEquals(10, result.size());
    }

    @Test
    void testCreateEmployee() {
        // Mock response from the API
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmployeeAge("22");
        employeeRequest.setEmployeeName("Harry");
        employeeRequest.setEmployeeSalary(20000);
        employeeRequest.setProfileImage(" ");

        EmployeeByIdResponse mockResponse = new EmployeeByIdResponse();
        Employee employee = new Employee("1", "Harry", 20000, "22", " ");
        mockResponse.setData(employee);

        when(restTemplate.postForObject(anyString(),eq(employeeRequest) ,eq(EmployeeByIdResponse.class))).thenReturn(mockResponse);

        // Call the service method
        EmployeeByIdResponse result = employeeService.createEmployee(employeeRequest);

        // Verify that the service method returns the expected result
        assertEquals(mockResponse,result);;
    }

    @Test
    void testDeleteEmployee() {
        // Mock response from the API
        EmployeeByIdResponse mockResponse = new EmployeeByIdResponse();
        Employee employee = new Employee("1", "Harry", 20000, "22", " ");
        mockResponse.setData(employee);

        when(restTemplate.getForObject(anyString() ,eq(EmployeeByIdResponse.class))).thenReturn(mockResponse);

        // Call the service method
        String idToDelete = "1";
        EmployeeByIdResponse result = employeeService.deleteEmployee(idToDelete);

        // Verify that the service method returns the expected result
        assertEquals(mockResponse,result);;
    }
}

