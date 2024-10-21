package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerBookDTO;
import com.example.demo.repository.CustomerBookRepository;

@Service
public class CustomerBookServiceImpl implements CustomerBookService {

    private final CustomerBookRepository customerBookRepository;

    @Autowired
    public CustomerBookServiceImpl(CustomerBookRepository customerBookRepository) {
        this.customerBookRepository = customerBookRepository;
    }

    @Override
    public List<CustomerBookDTO> getCustomerBookInfo() {
    	
        List<Object[]> results = customerBookRepository.findCustomerBookInfo();
        
        List<CustomerBookDTO> dtoList = new ArrayList<>();

        for (Object[] row : results) {
            Integer id = (Integer) row[0];
            String firstName = (String) row[1];
            String lastName = (String) row[2];
            String email = (String) row[3];
            Long booksBought = (Long) row[4]; // Ensure to cast it to Long

            dtoList.add(new CustomerBookDTO(id, firstName, lastName, email, booksBought));
        }

        return dtoList;
    }
}




