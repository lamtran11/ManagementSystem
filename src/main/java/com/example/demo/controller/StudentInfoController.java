package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.CourseDepartment;
import com.example.demo.model.Department;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentInfoController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @GetMapping("/courses")
    public List<CourseDepartment> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> new Course(course.getCourseId(), course.getCourseName(), course.getDepartment().getDepartmentName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

}
