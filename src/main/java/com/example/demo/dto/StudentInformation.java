package com.example.demo.dto;

import java.util.List;

public class StudentInformation {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String departmentName;
    private List<String> courseNames;

    // Constructor with all the fields
    public StudentInformation(Integer studentId, String firstName, String lastName, String email, 
                              String address, String departmentName, List<String> courseNames) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.departmentName = departmentName;
        this.courseNames = courseNames;
    }

    // Getters and setters (if necessary)

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }
}
