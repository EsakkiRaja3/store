package com.raja.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*Class for handling web requests*/
@Controller
public class HomeController {
    @Value("${spring.application.name}")
    // Can add any key from application.properties, this will act as flag
    private String appName;

    @RequestMapping("/")
    public String index(){
        System.out.println("AppName:"+appName);
        return "index.html";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
