package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Department findByDepartmentName(String departmentName);

}

