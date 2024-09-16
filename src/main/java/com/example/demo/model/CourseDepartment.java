package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "course_departments")
public class CourseDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_department_id")
    private int courseDepartmentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

    public CourseDepartment() {}

    public CourseDepartment(int courseDepartmentId, Course course, Department department, LocalDate assignedDate) {
        this.courseDepartmentId = courseDepartmentId;
        this.course = course;
        this.department = department;
        this.assignedDate = assignedDate;
    }

    public int getCourseDepartmentId() {
        return courseDepartmentId;
    }

    public void setCourseDepartmentId(int courseDepartmentId) {
        this.courseDepartmentId = courseDepartmentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public String toString() {
        return "CourseDepartment{" +
                "courseDepartmentId=" + courseDepartmentId +
                ", course=" + course +
                ", department=" + department +
                ", assignedDate=" + assignedDate +
                '}';
    }
}
