package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private int enrollmentId;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false) // Ensure student_id cannot be null
    private Student student; // Use a more descriptive variable name

    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false) // Ensure course_id cannot be null
    private Course courseId; // Use a more descriptive variable name

    @Column(name = "enrollment_date", nullable = false) // Ensure enrollment_date cannot be null
    private LocalDateTime enrollmentDate;

    // Default constructor
    public Enrollment() {
    }

    // Constructor with parameters
    public Enrollment(Student student, Course courseId, LocalDateTime enrollmentDate) {
        this.student = student;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return courseId;
    }

    public void setCourse(Course courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", student=" + student +
                ", course=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
