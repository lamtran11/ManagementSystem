package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CourseDepartment;

@Repository
public interface CourseDepartmentRepository extends JpaRepository<CourseDepartment, Integer> {
}

