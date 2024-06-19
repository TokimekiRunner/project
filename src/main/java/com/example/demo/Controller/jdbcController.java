package com.example.demo.Controller;

import com.example.demo.Share;
import com.example.demo.Util.ExcelUtils;
import com.example.demo.Util.MybatisUtil;
import com.example.demo.entity.Achievement;
import com.example.demo.entity.Book;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.AchievementService;
import com.example.demo.service.BookService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.*;
@Component
@RestController
@CrossOrigin
public class jdbcController {
    Share share = new Share();

    System.Logger logger = System.getLogger(Controller.class.getName());
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private BookService bookService;
    @Autowired
    private AchievementService achievementService;


    @ResponseBody
    //写一个list请求，查询数据库信息
    @RequestMapping("/all")
    public List<Achievement> list(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        // 执行sql
        //方式一：getMapper
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        List<Achievement> achievements = mapper.getall();


        //关闭sqlSession
        sqlSession.close();
        return achievements;
    }

   // @ResponseBody
    @PostMapping("/test")
    public List<Everything> handleAjaxRequest(@RequestBody String jsonData) {
        Gson gson = new Gson();
        System.out.println(jsonData);
    //    SearchObject searchObject = gson.fromJson(jsonData, SearchObject.class);
      Everything everything = gson.fromJson(jsonData, Everything.class);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        // 执行sql
        //方式一：getMapper
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        List<Everything> everythings = mapper.dsearch(everything);
       // System.out.println(achievementService.calwork(achievements));
      sqlSession.close();
        return everythings;
    }


  @PostMapping("/delete")
  public int Delete(@RequestBody String jsonData) {
    Gson gson = new Gson();
    System.out.println(jsonData);
  //  Everything everything = gson.fromJson(jsonData, Everything.class);
    Everything[] everythingArray = gson.fromJson(jsonData, Everything[].class);
    Everything everything = everythingArray[0];
    // 执行sql
    //方式一：getMapper
    achievementService.Delete(everything);
    // System.out.println(achievementService.calwork(achievements));
    return 1;
  }


    @RequestMapping("/download")
    public void testExcel(HttpServletResponse response,@RequestBody String jsonData) throws IOException {
      Gson gson = new Gson();
      System.out.println(jsonData);
      List<Achievement> achievementList = gson.fromJson(jsonData, new TypeToken<List<Achievement>>(){}.getType());
      List<AchievementDO> achievementDOList = new ArrayList<>();
      System.out.println(achievementList.get(0).toString());
        for (Achievement achievement : achievementList) {
            AchievementDO achievementDO = new AchievementDO();
            achievementDO.setId(achievement.getId());
            achievementDO.setName(achievement.getName());
            achievementDO.setCategory(achievement.getCategory());
            achievementDO.setYear(String.valueOf(achievement.getYear()));
            achievementDO.setPersonId(achievement.getPersonId());
            achievementDO.setLevel(achievement.getLevel());
            achievementDOList.add(achievementDO);
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename="+"test.xlsx");//或者文件名后缀为xlsx
        ExcelUtils.writeExcel(response,achievementDOList);
        System.out.println("hello");
    }


    @RequestMapping("/alll")
    public List<Map<String,Object>> bookList(){

        String sql = "SELECT  *  FROM book ";
        List<Map<String,Object>> list_maps=jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @RequestMapping("/input")
    public int input(@RequestParam("name") String name){

        String sql = "insert into book values(101,?,'lyl',152)";
        return jdbcTemplate.update(sql,name);
    }
  @PostMapping("/insert")
  public void InsertInfo(@RequestBody String jsonData) {
    Gson gson = new Gson();
    System.out.println(jsonData);
    Everything everything = gson.fromJson(jsonData, Everything.class);
    achievementService.Insert(everything);
    return;
  }

  @PostMapping("/insertperson")
  public void InsertPerson(@RequestBody String jsonData) {
      Gson gson = new Gson();
      System.out.println(jsonData);
      Person person = gson.fromJson(jsonData, Person.class);
      achievementService.InsertPerson(person);
      return;
  }

  @PostMapping("/getperson")
  public Person GetPerson(@RequestBody String jsonData) {
    Gson gson = new Gson();
    System.out.println(jsonData);
    Person person = gson.fromJson(jsonData, Person.class);
    Person result = achievementService.GetPerson(person);
    return result;
  }

  @PostMapping("/deleteperson")
  public void DeletePerson(@RequestBody String jsonData) {
    Gson gson = new Gson();
    System.out.println(jsonData);
    Person person = gson.fromJson(jsonData, Person.class);
    achievementService.DeletePerson(person);
    return ;
  }
}
