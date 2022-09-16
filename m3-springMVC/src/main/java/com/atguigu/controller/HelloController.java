package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller   // for autowire : 表明this is a controller
public class HelloController {



    // 配置路径 “/”， 映射到WEB-INF/pages/index.html
    @RequestMapping("/")
    public String toIndex() {
        // prefix（WEB-INF/pages/） + name(index) + suffix(.html)
        return "index";
    }

    @RequestMapping("/HelloControllerMethod")
    public String helloWOrld() {
        System.out.println("the helloWorld() method in class HelloController is executed");
        return "success";   // jump to success page
    }

}
