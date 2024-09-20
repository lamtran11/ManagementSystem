package com.example.demo.repository;

import com.example.demo.dto.StudentInformation;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Optional: custom query methods, if needed

    @Query(value = "SELECT S.student_id, S.first_name, S.last_name, S.email, D.department_name, STRING_AGG(C.course_name, ', ') AS courses " +
            "FROM Enrollments E " +
            "LEFT JOIN Students S ON E.student_id = S.student_id " +
            "LEFT JOIN Courses C ON E.course_id = C.course_id " +
            "LEFT JOIN Course_Departments CD ON CD.course_id = C.course_id " +
            "LEFT JOIN Departments D ON CD.department_id = D.department_id " +
            "GROUP BY S.student_id, S.first_name, S.last_name, S.email, D.department_name ",

            nativeQuery = true)

    List<Object[]> findStudentCourseEnrollments();

}
