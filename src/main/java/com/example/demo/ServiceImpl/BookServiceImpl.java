package com.example.demo.ServiceImpl;

import com.example.demo.dao.Bookdao;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private Bookdao bookdao;
    @Override
    public List<Book> getBooks() {
        return bookdao.getBookList();
    }
    @Override
    public int addBook(Book book) {
        return bookdao.insertBook(book);
    }
}
