package com.example.demo.entity;

public class Achievement {

    public int id;
    public String name;
    public String category;
    public int year;
    public int person_id;
    public String level;

    @Override
    public String toString() {
        return "Achievement{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", category='" + category + '\'' +
            ", year=" + year +
            ", person_id=" + person_id +
            ", level='" + level + '\'' +
            '}';
    }

    public Achievement(int id, String name, String category, int year, int person_id, String level) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.year = year;
        this.person_id = person_id;
        this.level = level;
    }
    public Achievement() {
        // 默认构造函数
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPersonId() {
        return person_id;
    }

    public void setPersonId(int person_id) {
        this.person_id = person_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


}

