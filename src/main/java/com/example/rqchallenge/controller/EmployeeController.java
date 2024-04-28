package com.example.rqchallenge.controller;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.EmployeeByIdResponse;
import com.example.rqchallenge.entity.EmployeeRequest;
import com.example.rqchallenge.entity.ErrorResponse;
import com.example.rqchallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    String upstreamErrorMessage="Upstream Service is Down,Please try after some time";

    //localhost:8080/employees
    @GetMapping
    public ResponseEntity<Object> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
            ErrorResponse errorResponse = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    upstreamErrorMessage
            );
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

    //localhost:8080/employees/search?name=Garrett Winters
    @GetMapping("/search")
    public ResponseEntity<Object> getEmployeesByNameSearch(@RequestParam String name) {
        try {
            List<Employee> employees = employeeService.getEmployeesByNameSearch(name);
            return ResponseEntity.ok(employees);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
            ErrorResponse errorResponse = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    upstreamErrorMessage
            );
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

    //localhost:8080/employees/10
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable String id) {
        try {
            EmployeeByIdResponse employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
            ErrorResponse errorResponse = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    upstreamErrorMessage
            );
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

    //localhost:8080/employees/highest-salary
    @GetMapping("/highest-salary")
    public ResponseEntity<Object> getHighestSalaryOfEmployees() {
        try {
            int highestSalary = employeeService.getHighestSalaryOfEmployees();
            return ResponseEntity.ok(highestSalary);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
            ErrorResponse errorResponse = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    upstreamErrorMessage
            );
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

    //localhost:8080/employees/top-10-highest-earning
    @GetMapping("/top-10-highest-earning")
    public ResponseEntity<Object> getTop10HighestEarningEmployeeNames() {
        try {
            List<Employee> employees = employeeService.getTop10HighestEarningEmployeeNames();
            return ResponseEntity.ok(employees);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
            ErrorResponse errorResponse = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    upstreamErrorMessage
            );
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

   /*
   localhost:8080/employees/create
   {
    "employee_name": "test",
    "employee_salary": "123",
    "employee_age": "23",
    "profile_image":" "
   }
   */
    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeRequest employee) {
        try {
            return ResponseEntity.ok(employeeService.createEmployee(employee));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
            ErrorResponse errorResponse = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    upstreamErrorMessage
            );
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

    //localhost:8080/employees/1
   @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String id) {
       try {
           EmployeeByIdResponse employee = employeeService.deleteEmployee(id);
           return ResponseEntity.ok(employee);
       } catch (HttpClientErrorException | HttpServerErrorException e) {
           HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
           ErrorResponse errorResponse = new ErrorResponse(
                   status.value(),
                   status.getReasonPhrase(),
                   upstreamErrorMessage
           );
           return ResponseEntity.status(status).body(errorResponse);
       }
    }
}
