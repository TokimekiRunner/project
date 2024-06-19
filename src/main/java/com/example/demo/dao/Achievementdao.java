package com.example.demo.dao;

import com.example.demo.entity.Achievement;
import com.example.demo.entity.Book;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public class Achievementdao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int insertAchievement(Achievement achievement) {
        String sql = "INSERT INTO achievements (id, name, category, year, person_id, level) VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, achievement.getId(), achievement.getName(), achievement.getCategory(),
                achievement.getYear(), achievement.getPersonId(), achievement.getLevel());
    }

    public int insertPerson(Person person) {
        String sql = "INSERT INTO persons (person_id, personname, department, position) VALUES (?,?, ?, ?)";

        return jdbcTemplate.update(sql, person.getPersonId(), person.getPersonname(), person.getDepartment(), person.getPosition());
    }

    public List<Achievement> getAchievementList() {
        //SQL
        String sql = "SELECT  *  FROM achievements ";
        List<Achievement> achievements = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Achievement.class));
        return achievements;
    }


    public List<Achievement> getAchievementListbypersonname(String personname) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE p.personname = ?";

        //返回结果
        List<Achievement> achievements = jdbcTemplate.query(sql, new Object[]{personname}, new BeanPropertyRowMapper(Achievement.class));

        return achievements;
    }

    public List<Person> getPersonbypersonname(String personname) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE p.personname = ?";
        //返回结果

           List<Person> persons=jdbcTemplate.query(sql, new Object[]{personname}, new BeanPropertyRowMapper(Person.class));

        return persons;
    }

    public List<Person> getPersonbyid(int id) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE a.id = ?";
        //返回结果

        List<Person> persons=jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper(Person.class));

        return persons;
    }

    public List<Achievement> getAchievementbyid(int id) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE a.id = ?";
        //返回结果

        List<Achievement> achievements = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper(Achievement.class));

        return achievements;
    }
    public List<Achievement> getAchievementListbyname(String name) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE a.name LIKE \'%"+ name+"%\'";
        //返回结果LIKE '%def%'
        System.out.println(sql);
        List<Achievement> achievements = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Achievement.class));

        return achievements;
    }

    public List<Person> getPersonbyname(String name) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE a.name = ?";
        //返回结果

        List<Person> persons=jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper(Person.class));

        return persons;
    }
    public List<Achievement> getAchievementListbytype(String type) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE a.category = ?";
        //返回结果
        List<Achievement> achievements = jdbcTemplate.query(sql, new Object[]{type}, new BeanPropertyRowMapper(Achievement.class));

        return achievements;
    }

    public List<Person> getPersonbytype(String type) {//一对多
        //SQL
        String sql = "SELECT a.id, a.name, a.year, a.category, a.level, a.person_id, p.personname, p.department, p.position\n" +
                "FROM achievements a\n" +
                "JOIN persons p ON a.person_id = p.person_id\n" +
                "WHERE a.category = ?";
        //返回结果

        List<Person> persons=jdbcTemplate.query(sql, new Object[]{type}, new BeanPropertyRowMapper(Person.class));

        return persons;
    }

public void deleteAchievementbyid(int id) {
        String sql = "DELETE FROM achievements WHERE id = ?";
        jdbcTemplate.update(sql, id);
}
}




