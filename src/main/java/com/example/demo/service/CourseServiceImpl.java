package com.example.demo.service;

import com.example.demo.dto.CourseDTO;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findCourseWithDepartmentName() {
        List<Object[]> results = courseRepository.findCourseWithDepartmentName();

        List<CourseDTO> courses = new ArrayList<>();

        for (Object[] row : results) {
            Integer courseId = (row[0] != null) ? ((Number) row[0]).intValue() : null;
            String courseName = (row[1] != null) ? (String) row[1] : "";
            String departmentName = (row[2] != null) ? (String) row[2] : "";

            if(courseId != null) {
                courses.add(new CourseDTO(courseId, courseName, departmentName));
            }
        }
        return courses;
    }
}


