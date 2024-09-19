package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

    private UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/findAll")
    public List<User> getAllStudents() {
        return userService.findAllStudents();
    }

}


