package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode

public class AchievementDO {
    @ExcelProperty("荣誉编号")
    @ColumnWidth(20)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    @ExcelProperty("标题")
    @ColumnWidth(20)
    private String name;

    @ExcelProperty("类别")
    @ColumnWidth(20)
    private String category;

    @ExcelProperty("年份")
    @ColumnWidth(20)
    private String year;

    @ExcelProperty("人员ID")
    @ColumnWidth(20)
    private int personId;

    @ExcelProperty("等级")
    @ColumnWidth(20)
    private String level;

}
