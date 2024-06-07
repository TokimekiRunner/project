package com.example.demo.ServiceImpl;

import com.example.demo.dao.Achievementdao;
import com.example.demo.dao.Bookdao;
import com.example.demo.entity.Achievement;
import com.example.demo.entity.Book;
import com.example.demo.entity.Person;
import com.example.demo.service.AchievementService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {



    @Resource
    private Achievementdao achievementdao;
    @Override
    public int insertAchievement(Achievement achievement){
        return achievementdao.insertAchievement(achievement);
    }
    @Override
    public int insertPerson(Person person){
        return achievementdao.insertPerson(person);
    }

    @Override
    public String getAchievements(){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Achievement> achievementList = achievementdao.getAchievementList();
   //     String personJson = gson.toJson(personList);
  //      System.out.println(personJson);
        String achievementJson = gson.toJson(achievementList);
        System.out.println(achievementJson);
     //   jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();    }




    @Override
    public String getAchievementByPersonname(String name){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbypersonname(name);
        List<Achievement> achievementList = achievementdao.getAchievementListbypersonname(name);
        String personJson = gson.toJson(personList);
        String achievementJson = gson.toJson(achievementList);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }

    @Override

    public String getAchievementById(int id) {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbyid(id);
        List<Achievement> achievementList = achievementdao.getAchievementbyid(id);
        String personJson = gson.toJson(personList);
        System.out.println(personJson);
        String achievementJson = gson.toJson(achievementList);
        System.out.println(achievementJson);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }
    @Override
    public String getAchievementByname(String name){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbyname(name);
        List<Achievement> achievementList = achievementdao.getAchievementListbyname(name);
        String personJson = gson.toJson(personList);
        String achievementJson = gson.toJson(achievementList);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }

    @Override
    public String getAchievementBytype(String type){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbytype(type);
        List<Achievement> achievementList = achievementdao.getAchievementListbytype(type);
        String personJson = gson.toJson(personList);
        String achievementJson = gson.toJson(achievementList);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }
    @Override
    public void deleteAchievementbyid(int id){
        achievementdao.deleteAchievementbyid(id);
    }

}
