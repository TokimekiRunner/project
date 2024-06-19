package com.example.demo.entity;

public class Person {
    private int person_id;
    private String personname;
    private String department;
    private String position;
    public Person() {
        // 无参构造函数
    }

    public Person(int personId, String name, String department, String position) {
        this.person_id = personId;
        this.personname = name;
        this.department = department;
        this.position = position;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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
