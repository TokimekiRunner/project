package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode

public class RankDO {
  @ExcelProperty({"科研管理系统工作量排名","科研人员ID"})
  @ColumnWidth(20)
  public String person_id;
  @ExcelProperty({"科研管理系统工作量排名","科研人员姓名"})
  @ColumnWidth(20)
  public String personname;

  public float getCalscore() {
    return calscore;
  }

  public void setCalscore(float calscore) {
    this.calscore = calscore;
  }

  public String getPersonname() {
    return personname;
  }

  public void setPersonname(String personname) {
    this.personname = personname;
  }

  public String getPerson_id() {
    return person_id;
  }

  public void setPerson_id(String person_id) {
    this.person_id = person_id;
  }

  @ExcelProperty({"科研管理系统工作量排名","科研工作量"})
  @ColumnWidth(20)
  public float calscore;

}
