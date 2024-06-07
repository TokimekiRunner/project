package com.example.demo.mapper;
import com.example.demo.entity.Book;
public interface BookMapper {
    Book getBook(int id);
    int update(Book book);
}
