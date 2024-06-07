package com.example.demo.service;


import com.example.demo.entity.Achievement;
import com.example.demo.entity.Person;

import java.util.HashSet;
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
    void deleteAchievementbyid(int id);
}
