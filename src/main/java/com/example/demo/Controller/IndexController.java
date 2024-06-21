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

@Controller
public class IndexController {
    @Autowired
    private AchievementService achievementService;

    @RequestMapping("/home")
    public String home()
    {
        return "inquiry";
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


    @RequestMapping("/login")
    public String login(@RequestBody String jsonData){
        Gson gson = new Gson();
        AdmUser admUser = gson.fromJson(jsonData, AdmUser.class);
        int flag = achievementService.verify(admUser);
        if(flag == 0){
            return "home";
        }else{
            return "adm";
        }
    }

}
