package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class controller {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello, world! ";
    }



}
