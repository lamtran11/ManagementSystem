package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	
	@Autowired  // コンストラクタインジェクションにより依存性を解決する。
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> getAllBook() {
		// TODO 自動生成されたメソッド・スタブ
		return bookRepository.findAll();
	}

}
