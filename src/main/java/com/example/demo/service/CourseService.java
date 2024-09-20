package com.example.demo.service;


import com.example.demo.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    List<CourseDTO> findCourseWithDepartmentName();

}
