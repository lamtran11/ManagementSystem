package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "course_instructors")
public class CourseInstructor {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_instructor_id")
    private int courseInstructorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

    public CourseInstructor() {

    }

    public CourseInstructor(int courseInstructorId, Course course, Instructor instructor, LocalDate assignedDate) {
        this.courseInstructorId = courseInstructorId;
        this.course = course;
        this.instructor = instructor;
        this.assignedDate = assignedDate;
    }

    public int getCourseInstructorId() {
        return courseInstructorId;
    }

    public void setCourseInstructorId(int courseInstructorId) {
        this.courseInstructorId = courseInstructorId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public String toString() {
        return "CourseInstructor{" +
                "courseInstructorId=" + courseInstructorId +
                ", course=" + course +
                ", instructor=" + instructor +
                ", assignedDate=" + assignedDate +
                '}';
    }
}
