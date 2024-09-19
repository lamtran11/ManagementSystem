package com.example.demo.dto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
public class StudentInformation {

    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentName;
    private String courseName;


}
