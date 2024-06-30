package com.example.demo.mapper;

import com.example.demo.entity.*;

import java.util.List;

public interface TestMapper {
    List<Achievement> getall();
    List<Everything> dsearch(Everything everything);
    Everything allsearch(int id);
    Everything awardsearch(int id);
    int insert(Everything everything);
    int insertresearch(Everything everything);
    int insertpatent(Everything everything);
    int insertvisit(Everything everything);
    int insertaward(Everything everything);
    int delete(Everything everything);
    int deleteresearch(Everything everything);
    int deletevisit(Everything everything);
    int deleteaward(Everything everything);
    int deletepatent(Everything everything);
    int insertperson(Person person);
    Person getperson(Person person);
    List<Everything> robust(Person person);

    List<Person>getallperson();
    int deleteperson(Person person);
    String getpassword(AdmUser admUser);
}
