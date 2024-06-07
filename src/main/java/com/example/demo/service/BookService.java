package com.example.demo.service;

import com.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public interface BookService {

    List<Book> getBooks();
    int addBook(Book book);
}
