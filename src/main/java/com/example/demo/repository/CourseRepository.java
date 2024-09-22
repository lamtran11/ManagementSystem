package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Additional methods for course management, Add here.

    @Query(value = "SELECT C.course_name, D.department_name" +
            " FROM course_departments CD " +
            "JOIN courses C ON CD.course_id = C.course_id " +
            "JOIN departments D ON CD.department_id = D.department_id",
            nativeQuery = true
    )
    List<Object[]> findCourseWithDepartmentName();
    
}	