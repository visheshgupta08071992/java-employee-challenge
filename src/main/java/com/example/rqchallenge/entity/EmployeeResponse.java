package com.example.rqchallenge.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeResponse {

    private String status;
    private List<Employee> data;
    private String message;
}
