package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StudentInformation;
import com.example.demo.model.Student;

public interface StudentService {

    List<StudentInformation> findAllStudentCourseEnrollments();

    Student getStudentByLastName(String lastName);

    Student getStudentByEmail(String email);
    
//    List<StudentInformation> saveAllStudentFormData(List<StudentInformation> studentData);
}

