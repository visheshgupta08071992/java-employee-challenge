package com.example.rqchallenge.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Employee {

    private String id;
    private String employee_name;
    private String employee_salary;
    private String employee_age;
    private String profile_image;
}
