package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BookController {

	private BookService bookService;
	
	public BookController (BookService bookService) {
        this.bookService = bookService;
    }
	
	@GetMapping("/bookList")
	public List<Book> findAllBooks() {
        
        return bookService.getAllBook();        
    }
	
	
}
