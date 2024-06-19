package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode

public class AchievementDO {
    @ExcelProperty({"科研管理系统工作量统计报表","科研编号"})
    @ColumnWidth(20)
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    @ExcelProperty({"科研管理系统工作量统计报表","科研名称"})
    @ColumnWidth(20)
    private String name;

    @ExcelProperty({"科研管理系统工作量统计报表","科研类别"})
    @ColumnWidth(20)
    private String category;

    @ExcelProperty({"科研管理系统工作量统计报表","科研时间"})
    @ColumnWidth(20)
    private String year;

    @ExcelProperty({"科研管理系统工作量统计报表","科研人员编号"})
    @ColumnWidth(20)
    private String personId;

    @ExcelProperty({"科研管理系统工作量统计报表","科研来源"})
    @ColumnWidth(20)
    private String level;

    public float getCalscore() {
        return calscore;
    }

    public void setCalscore(float calscore) {
        this.calscore = calscore;
    }

    @ExcelProperty({"科研管理系统工作量统计报表","单项工作评分"})
    @ColumnWidth(20)
    private float calscore;
}
