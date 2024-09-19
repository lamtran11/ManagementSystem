package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

//    private UserService userService;
//
//    @Autowired
//     public LoginController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Return the name of the login view (e.g., login.html)
    }

}
