package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentInformation;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentInformation> findAllStudentCourseEnrollments() {
        List<Object[]> results = studentRepository.findStudentCourseEnrollments();
        List<StudentInformation> dtoList = new ArrayList<>();

        for (Object[] row : results) {
            Integer studentId = (row[0] != null) ? ((Number) row[0]).intValue() : null;
            String firstName = (row[1] != null) ? (String) row[1] : "";
            String lastName = (row[2] != null) ? (String) row[2] : "";
            String email = (row[3] != null) ? (String) row[3] : "";
            String departmentName = (row[4] != null) ? (String) row[4] : "";
            String courseName = (row[5] != null) ? (String) row[5] : "";

            if (studentId != null) {
                dtoList.add(new StudentInformation(studentId, firstName, lastName, email, departmentName, courseName));
            } else {
                // Optionally handle the case where studentId is null
                // For example, log a warning or add a placeholder
            }
        }

        return dtoList;
    }

    @Override
    public Student getStudentByLastName(String lastName) {
        return null;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return null;
    }
    
    @Override
    public List<StudentInformation> saveAllStudentFormData(List<StudentInformation> studentData) {
        List<StudentInformation> savedStudents = new ArrayList<>();

        for (StudentInformation info : studentData) {
            // Check if the student already exists by email
            Student student = studentRepository.findByEmail(info.getEmail());

            if (student == null) {
                student = new Student();
                student.setFirstName(info.getFirstName());
                student.setLastName(info.getLastName());
                student.setEmail(info.getEmail());
            }

            // Find or create the department
            Department department = departmentRepository.findByDepartmentName(info.getDepartmentName());
            if (department == null) {
                department = new Department();
                department.setDepartmentName(info.getDepartmentName());
                departmentRepository.save(department);
            }
            student.setDepartment(department);	

            // Find or add courses
            List<Course> courses = new ArrayList<>();
            String[] courseNames = info.getCourseName().split(", ");
            for (String courseName : courseNames) {
                Course course = courseRepository.findByCourseName(courseName);
                if (course == null) {
                    course = new Course();
                    course.setCourseName(courseName);
                    courseRepository.save(course);
                }
                courses.add(course);
            }
            ((Object) student).setCourses(courses);

            // Save the student entity
            student = studentRepository.save(student);

            // Create and return saved StudentInformation DTO
            StudentInformation savedInfo = new StudentInformation(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                department.getDepartmentName(),
                String.join(", ", courseNames)
            );
            savedStudents.add(savedInfo);
        }

        return savedStudents;
    }

}





