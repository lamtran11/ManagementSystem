package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "instructors")
public class Instructor {

	@Id
    @Column(name = "instructor_id")
    private String instructorId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private short active;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAt;

    // Relationship with Courses (Many instructors can teach many courses)
    @ManyToMany(mappedBy = "instructors")
    private List<Course> courses = new ArrayList<>();

    public Instructor() {}

    public Instructor(String instructorId, String firstName, String lastName, String email, LocalDate hireDate, LocalDate createdAt, String password) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.createdAt = createdAt;
        this.password = password;
    }


    public String getInstructorId() {
        return instructorId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", createdAt=" + createdAt +
                '}';
    }


}
