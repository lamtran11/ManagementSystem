package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.StudentInformation;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/findAll")
    public List<StudentInformation> getAllStudentCourseEnrollments() {
        return studentService.findAllStudentCourseEnrollments();
    }


}


