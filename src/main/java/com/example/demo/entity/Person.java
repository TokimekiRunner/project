package com.example.demo.entity;

public class Person {
    private String person_id;
    private String personname;
    private String department;
    private String position;
    public Person() {
        // 无参构造函数
    }

    public Person(String personId, String name, String department, String position) {
        this.person_id = personId;
        this.personname = name;
        this.department = department;
        this.position = position;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
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
