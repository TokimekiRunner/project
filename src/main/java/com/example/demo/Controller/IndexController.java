package com.example.demo.Controller;


import com.example.demo.Share;
import com.example.demo.entity.AdmUser;
import com.example.demo.entity.Everything;
import com.example.demo.service.AchievementService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    private AchievementService achievementService;

    @RequestMapping("/home")
    public String home()
    {
        return "inquiry";
    }

    @RequestMapping("/log")
    public String log()
    {
        return "login";
    }

    @RequestMapping("/adm")
    public String adm()
    {
        return "administration";
    }

    @RequestMapping("/cal")
    public String cal()
    {
        return "calculation";
    }

    @RequestMapping("/add")
    public String test2()
    {
        return "add";
    }

    @RequestMapping("/addperson")
    public String addperson()
    {
        return "addperson";
    }

    @RequestMapping("/searchbyid")
    public String searchbyid()
    {
        return "searchbyid";
    }
    @RequestMapping("/searchbypersonname")
    public String test()
    {
        return "searchbypersonname";
    }

    @RequestMapping("/searchbyachname")
    public String searchbyachname()
    {
        return "searchbyname";
    }


    @RequestMapping("/searchbytype")
    public String searchbytype()
    {
        return "searchbytype";
    }
    @RequestMapping("/deletebyid")
    public String deletebyid()
    {
        return "deletebyid";
    }

    @ResponseBody
    @RequestMapping("/login")
    public int login(@RequestBody String jsonData){
        Gson gson = new Gson();
        AdmUser admUser = gson.fromJson(jsonData, AdmUser.class);
        return achievementService.verify(admUser);

    }
}
