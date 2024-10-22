package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerBookDTO;
import com.example.demo.service.CustomerBookService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CustomerBookController {
	
    private final CustomerBookService customerBookService;
    
    @Autowired
    public CustomerBookController(CustomerBookService customerBookService) {
        this.customerBookService = customerBookService;
    }
    
    @GetMapping("/customer-books")
    public ResponseEntity<List<Map<String, Object>>> getCustomerBookInfo() {
    	
        List<CustomerBookDTO> customerBookData = customerBookService.getCustomerBookInfo();
        	
        List<Map<String, Object>> response = new ArrayList<>();
        	
        for (CustomerBookDTO data : customerBookData) {
        	
            Map<String, Object> map = new HashMap<>();
            
            map.put("customerId", data.getCustomerId());
            map.put("firstName", data.getCustomerFirstName());
            map.put("lastName", data.getCustomerLastName());
            map.put("email", data.getEmail());
            map.put("booksBought", data.getBookId());
            
            response.add(map);
        }
        
        return ResponseEntity.ok(response);
    }
}




