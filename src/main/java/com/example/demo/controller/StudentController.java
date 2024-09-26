package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentInformation;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

    private StudentService studentService;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/findAll")
    public List<StudentInformation> getAllStudentCourseEnrollments() {
        return studentService.findAllStudentCourseEnrollments();
    }
    
    @PostMapping("/saveStudentFormData")
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
        // Create a new student entity
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAddress(studentRequest.getAddress());
        student.setBirthdate(studentRequest.getBirthdate());
        
        // Fetch or create the department
        Department department = departmentRepository.findByName(studentRequest.getDepartmentName());
        if (department == null) {
            department = new Department();
            department.setDepartmentName(studentRequest.getDepartmentName());
            department = departmentRepository.save(department);
        }
        student.setDepartment(department); // Associate department with the student
        
        // Create enrollments for courses
        for (String courseName : studentRequest.getCourseNames()) {
            Course course = courseRepository.findByName(courseName);
            if (course == null) {
                // Optionally, create the course if it doesn't exist
                course = new Course();
                course.setCourseName(courseName);
                course = courseRepository.save(course);
            }
            
            // Create an enrollment and link student and course
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setEnrollmentDate(LocalDateTime.now());
            
            student.getEnrollments().add(enrollment); // Add enrollment to student
        }
        
        // Save the student along with their enrollments
        Student savedStudent = studentService.saveStudent(student);
        
        // Return the saved student as a response
        return ResponseEntity.ok(savedStudent);
    }

   
    // DTO class to handle incoming JSON structure
    public static class StudentRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private LocalDate birthdate; // Optional
        private List<String> courseNames;
        private String departmentName;
        
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public LocalDate getBirthdate() {
			return birthdate;
		}
		public void setBirthdate(LocalDate birthdate) {
			this.birthdate = birthdate;
		}
		public List<String> getCourseNames() {
			return courseNames;
		}
		public void setCourseNames(List<String> courseNames) {
			this.courseNames = courseNames;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

       
    }
   
    
}


