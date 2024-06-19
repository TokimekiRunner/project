package com.example.demo.service;


import com.example.demo.entity.Achievement;
import com.example.demo.entity.Everything;
import com.example.demo.entity.Person;

import java.util.List;

public interface AchievementService
{
    Achievement shareachievement = new Achievement();
    int insertAchievement(Achievement achievement);
     int insertPerson(Person person);
    String getAchievements();
    //List<Achievement> getAchievementById(int id);
    String getAchievementById(int id);
    String getAchievementByPersonname(String name);
    String getAchievementByname(String name);
    String getAchievementBytype(String type);
    float calwork(List<Achievement> achievements);
    int Insert(Everything everything);
    int Delete(Everything everything);

    int InsertPerson(Person person);
    Person GetPerson(Person person);
    int DeletePerson(Person person);
}
