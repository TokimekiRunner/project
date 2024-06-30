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
import java.util.*;

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
    private String year;


  @RequestMapping("/rank")
  public List<Rank> Rank(@RequestBody String jsonData){
    Gson gson = new Gson();
    Everything receive = gson.fromJson(jsonData, Everything.class);
     year = receive.getYear();
    System.out.println(year);
    List<Rank> result = achievementService.calrank(year);

    result.sort(Comparator.comparing(Rank::getCalscore).reversed());

    for(Rank rank : result){
      System.out.println(rank.personname);
      System.out.println(rank.calscore);
    }

    return result;

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
     //  System.out.println(achievementService.calwork(everythings));
      sqlSession.close();
        return everythings;
    }


  @PostMapping("/calculate")
  public List<Everything> Calculate(@RequestBody String jsonData) {
    Gson gson = new Gson();
    System.out.println(jsonData);
    //    SearchObject searchObject = gson.fromJson(jsonData, SearchObject.class);
    Everything everything = gson.fromJson(jsonData, Everything.class);
    if(everything.getPerson_id().length()!=0){
    SqlSession sqlSession = MybatisUtil.getSqlSession();
    // 执行sql
    //方式一：getMapper
    TestMapper mapper = sqlSession.getMapper(TestMapper.class);
    List<Everything> everythings = mapper.dsearch(everything);
  achievementService.calwork(everythings);
    sqlSession.close();
    return everythings;}
    else {
      return null;
    }
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
      float totalscore = 0;
      Gson gson = new Gson();
      System.out.println("new");
      System.out.println(jsonData);
      List<Everything> Everythings = gson.fromJson(jsonData, new TypeToken<List<Everything>>(){}.getType());
      List<AchievementDO> achievementDOList = new ArrayList<>();
        for (Everything everything : Everythings) {
            AchievementDO achievementDO = new AchievementDO();
            achievementDO.setId(everything.getId());
            achievementDO.setName(everything.getName());
            achievementDO.setCategory(everything.getCategory());
            achievementDO.setYear(String.valueOf(everything.getYear()));
            achievementDO.setPersonId(everything.getPerson_id());
            achievementDO.setLevel(everything.getLevel());
            achievementDO.setCalscore(everything.getCalscore());
            totalscore += everything.getCalscore();
            achievementDOList.add(achievementDO);
        }
      AchievementDO achievementDO = new AchievementDO();
      achievementDO.setId(null);
      achievementDO.setName(null);
      achievementDO.setCategory(null);
      achievementDO.setYear("科研人员姓名为:");
      achievementDO.setPersonId(Everythings.get(0).getPersonname());
      achievementDO.setLevel("总工作量为:");
      achievementDO.setCalscore(totalscore);
      achievementDOList.add(achievementDO);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename="+"Personwork.xlsx");//或者文件名后缀为xlsx
        ExcelUtils.writeExcel(response,achievementDOList);
        System.out.println("hello");
    }

  @RequestMapping("/download_rank")
  public void Download_rank(HttpServletResponse response,@RequestBody String jsonData) throws IOException {
    Gson gson = new Gson();
    System.out.println(jsonData);
    List<Rank> ranks= gson.fromJson(jsonData, new TypeToken<List<Rank>>(){}.getType());
    List<RankDO> rankDOList = new ArrayList<>();
    for (Rank rank : ranks) {
      RankDO rankDO = new RankDO();
      rankDO.setPerson_id(rank.getPerson_id());
      rankDO.setPersonname(rank.getPersonname());
      rankDO.setCalscore(rank.getCalscore());
      rankDOList.add(rankDO);
    }
    RankDO rankDO = new RankDO();
    rankDO.setPerson_id(null);
    rankDO.setPersonname("年份为");
    rankDO.setCalscore(Float.parseFloat(year));
    rankDOList.add(rankDO);
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition","attachment;filename="+"test.xlsx");//或者文件名后缀为xlsx
    ExcelUtils.writeRank(response,rankDOList);
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
  public int DeletePerson(@RequestBody String jsonData) {
    Gson gson = new Gson();
    System.out.println(jsonData);
    Person person = gson.fromJson(jsonData, Person.class);
    SqlSession sqlSession = MybatisUtil.getSqlSession();
    // 执行sql
    //方式一：getMapper
    TestMapper mapper = sqlSession.getMapper(TestMapper.class);
    List<Everything> everythings = mapper.robust(person);
    int flag = everythings.size();
    System.out.println(flag);
    if (flag != 0 ) {

      return flag;
    }
    achievementService.DeletePerson(person);
    return 0;
  }


}
