package com.example.demo.Controller;

import com.example.demo.Share;
import com.example.demo.Util.ExcelUtils;
import com.example.demo.entity.Achievement;
import com.example.demo.entity.Book;
import com.example.demo.service.AchievementService;
import com.example.demo.service.BookService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller
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
    public List<Book> list(){
        System.out.println("hello"+share.getTmpachievements());
        return bookService.getBooks();
    }

    @RequestMapping("/download")
    public void testExcel(HttpServletResponse response) throws IOException {
        List<AchievementDO> achievementDOList = new ArrayList<>();
               List<Achievement> achievementList = share.getTmpachievements();
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

    @RequestMapping("/insert_achievement")
    public String insert_achievement(@RequestParam("id") int id
    ,@RequestParam("name") String name
    ,@RequestParam("level") String level
    ,@RequestParam("type") String type
    ,@RequestParam("year") int year
    ,@RequestParam("person_id") int person_id){
        System.out.println("hello");
        Achievement achievement = new Achievement(id,name,type,year,person_id,level);
        achievementService.insertAchievement(achievement);
        return "redirect:/home";

    }

    @RequestMapping("/insert_person")
    public String insert_achievement(@RequestParam("person_id") int person_id
            ,@RequestParam("personname") String personname
            ,@RequestParam("department") String department
            ,@RequestParam("position") String position){
        Person person = new Person(person_id,personname,department,position);
        achievementService.insertPerson(person);
        return "redirect:/home";

    }
    @RequestMapping("/deleteachievementbyid")
    public String DeleteAchievementsById(@RequestParam("id") int id,Model model){
        achievementService.deleteAchievementbyid(id);
        return "redirect:/home";
    }


    @RequestMapping("/getachievements")
    public String getAchievements(Model model){
        Gson gson = new Gson();
        String string = achievementService.getAchievements();

        JsonArray jsonArray = gson.fromJson(string, JsonArray.class);

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> jsonArrayList = gson.fromJson(string, listType);
  //      String personlist = jsonArrayList.get(0);
        String achievementlist = jsonArrayList.get(0);
//
//        Type personListType = new TypeToken<List<Person>>() {}.getType();
//        List<Person> personList = gson.fromJson(personlist, personListType);

        Type achListType = new TypeToken<List<Achievement>>() {}.getType();
        List<Achievement> AchList = gson.fromJson(achievementlist, achListType);
    //    System.out.println(personlist);
        model.addAttribute("jsonDataList",AchList);
        share.setTmpachievements(AchList);
        return "result";
    }

    @RequestMapping("/getachievementbyid")
    public String getAchievementsById(@RequestParam("id") int id,Model model){
        Gson gson = new Gson();
        String string = achievementService.getAchievementById(id);

        JsonArray jsonArray = gson.fromJson(string, JsonArray.class);

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> jsonArrayList = gson.fromJson(string, listType);
        String personlist = jsonArrayList.get(0);
        String achievementlist = jsonArrayList.get(1);

        Type personListType = new TypeToken<List<Person>>() {}.getType();
        List<Person> personList = gson.fromJson(personlist, personListType);

        Type achListType = new TypeToken<List<Achievement>>() {}.getType();
        List<Achievement> AchList = gson.fromJson(achievementlist, achListType);

        System.out.println(achievementlist);
        System.out.println(personlist);
        model.addAttribute("jsonDataList",AchList);

        return "result";
    }

  //  @ResponseBody
    @RequestMapping("/getachievementbypersonname")
    @GetMapping("/jsondata")
    public String getAchievementsByPersonname(@RequestParam("personname") String personname,Model model){

        Gson gson = new Gson();
        String string = achievementService.getAchievementByPersonname(personname);

        JsonArray jsonArray = gson.fromJson(string, JsonArray.class);

       Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> jsonArrayList = gson.fromJson(string, listType);
        String personlist = jsonArrayList.get(0);
        String achievementlist = jsonArrayList.get(1);

        Type personListType = new TypeToken<List<Person>>() {}.getType();
        List<Person> personList = gson.fromJson(personlist, personListType);

        Type achListType = new TypeToken<List<Achievement>>() {}.getType();
        List<Achievement> AchList = gson.fromJson(achievementlist, achListType);

       System.out.println(achievementlist);
        System.out.println(personlist);
        model.addAttribute("jsonDataList",AchList);

        return "result";
    }


    @RequestMapping("/getachievementbyname")
    @GetMapping("/jsondata")
    public String getAchievementsByname(@RequestParam("name") String name,Model model){

        Gson gson = new Gson();
        String string = achievementService.getAchievementByname(name);

        JsonArray jsonArray = gson.fromJson(string, JsonArray.class);

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> jsonArrayList = gson.fromJson(string, listType);
        String personlist = jsonArrayList.get(0);
        String achievementlist = jsonArrayList.get(1);

        Type personListType = new TypeToken<List<Person>>() {}.getType();
        List<Person> personList = gson.fromJson(personlist, personListType);

        Type achListType = new TypeToken<List<Achievement>>() {}.getType();
        List<Achievement> AchList = gson.fromJson(achievementlist, achListType);

        System.out.println(achievementlist);
        System.out.println(personlist);
        model.addAttribute("jsonDataList",AchList);

        return "result";
    }

    @RequestMapping("/getachievementbytype")
    @GetMapping("/jsondata")
    public String getAchievementsBytype(@RequestParam("type") String type,Model model){

        Gson gson = new Gson();
        String string = achievementService.getAchievementBytype(type);

        JsonArray jsonArray = gson.fromJson(string, JsonArray.class);

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> jsonArrayList = gson.fromJson(string, listType);
        String personlist = jsonArrayList.get(0);
        String achievementlist = jsonArrayList.get(1);

        Type personListType = new TypeToken<List<Person>>() {}.getType();
        List<Person> personList = gson.fromJson(personlist, personListType);

        Type achListType = new TypeToken<List<Achievement>>() {}.getType();
        List<Achievement> AchList = gson.fromJson(achievementlist, achListType);

        System.out.println(achievementlist);
        System.out.println(personlist);
        model.addAttribute("jsonDataList",AchList);

        return "result";
    }

}
