package com.example.rqchallenge.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class EmployeeByIdResponse {

    private String status;
    private Employee data;
    private String message;
}
