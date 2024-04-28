package com.example.rqchallenge.service;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeByIdResponse;
import com.example.rqchallenge.entity.EmployeeRequest;
import com.example.rqchallenge.entity.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final String baseURL = "https://dummy.restapiexample.com/api/v1";

    private final RestTemplate restTemplate;

    public EmployeeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
            String url = baseURL + "/employees";
            EmployeeResponse response = restTemplate.getForObject(url, EmployeeResponse.class);
            return response != null ? response.getData() : new ArrayList<>();
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String name) {
        String url = baseURL + "/employees";
        EmployeeResponse response = restTemplate.getForObject(url, EmployeeResponse.class);
        List<Employee> employeeList=response.getData();
        // List to store matching employees
        List<Employee> matchingEmployees = new ArrayList<>();
        // Iterate through the list of employees and check if the name matches
        for (Employee employee : employeeList) {
            if (employee.getEmployeeName().equals(name)) {
                matchingEmployees.add(employee);
            }
        }

        return matchingEmployees;
    }

    @Override
    public EmployeeByIdResponse getEmployeeById(String id) {
        String url = baseURL + "/employee/" + id;
        return restTemplate.getForObject(url, EmployeeByIdResponse.class);
    }

    @Override
    public int getHighestSalaryOfEmployees() {
        String url = baseURL + "/employees";
        EmployeeResponse response = restTemplate.getForObject(url, EmployeeResponse.class);
        List<Employee> employeeList = response.getData();
        int maxSalary = 0;
        for (Employee employee : employeeList) {
            int salary = employee.getEmployeeSalary();
            if (salary > maxSalary) {
                maxSalary = salary;
            }
        }
        return maxSalary;
    }

    @Override
    public List<Employee> getTop10HighestEarningEmployeeNames() {
        String url = baseURL + "/employees";
        EmployeeResponse response = restTemplate.getForObject(url, EmployeeResponse.class);
        List<Employee> employeeList=response.getData();

        employeeList.sort(Comparator.comparingInt(Employee::getEmployeeSalary).reversed());

        // Take the top 10 employees from the sorted list
        List<Employee> top10Employees = employeeList.subList(0, Math.min(10, employeeList.size()));

        return top10Employees;
    }

   @Override
    public EmployeeByIdResponse createEmployee(EmployeeRequest employee) {
        String url = baseURL + "/create";
        return restTemplate.postForObject(url, employee, EmployeeByIdResponse.class);
    }

    @Override
    public EmployeeByIdResponse deleteEmployee(String id) {
        String url = baseURL + "/delete/" + id;
        return restTemplate.getForObject(url, EmployeeByIdResponse.class);
    }
}
