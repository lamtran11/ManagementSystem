package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class CourseDTO {
    private int courseId;
    private String courseName;
    private String departmentName;
    
    public CourseDTO() {}  // Default constructor for Jackson JSON deserialization
    
    public CourseDTO(int courseId, String courseName, String departmentName) {
    	this.courseId = courseId;
        this.courseName = courseName;
        this.departmentName = departmentName;
    }

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
    
    
}

