package com.example.rqchallenge.service;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeByIdResponse;
import com.example.rqchallenge.entity.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByNameSearch(String name);

    EmployeeByIdResponse getEmployeeById(String id);

    int getHighestSalaryOfEmployees();

    List<Employee> getTop10HighestEarningEmployeeNames();

    EmployeeByIdResponse createEmployee(EmployeeRequest employee);

    EmployeeByIdResponse deleteEmployee(String id);
}
