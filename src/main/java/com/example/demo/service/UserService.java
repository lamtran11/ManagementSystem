package com.example.demo.service;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {
    List<User> findAllStudents();


}

