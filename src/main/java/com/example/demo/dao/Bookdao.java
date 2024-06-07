package com.example.demo.dao;

import com.example.demo.entity.Achievement;
import com.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class Bookdao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int insertBook(Book book) {
       // INSERT INTO project.book (id, name, author, price) VALUES (100, 'csapp', 'ghk', 100)
        String sql = "insert into book values(?,?,?,?)";
        return jdbcTemplate.update(sql, book.getId(),book.getName(),book.getAuthor(),book.getPrice());
    }
    public List<Book> getBookList() {
        //SQL
        String sql = "SELECT  *  FROM book ";
        //返回结果
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Book.class));
    }


}
