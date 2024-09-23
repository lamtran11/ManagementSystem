package com.example.demo.dto;

public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private String departmentName;
    
    public CourseDTO() {}  // Default constructor for Jackson JSON deserialization
    
   
    public CourseDTO(Integer courseId, String courseName, String departmentName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.departmentName = departmentName;
    }


	public Integer getCourseId() {
		return courseId;
	}


	public void setCourseId(Integer courseId) {
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

