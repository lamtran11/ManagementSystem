package com.example.demo.dto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
public class CourseDTO {

    private int courseId;
    private String courseName;
    private String departmentName;

}
