package com.example.demo.entity;

public class Person {
    private int personId;
    private String personname;
    private String department;
    private String position;
    public Person() {
        // 无参构造函数
    }

    public Person(int personId, String name, String department, String position) {
        this.personId = personId;
        this.personname = name;
        this.department = department;
        this.position = position;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
