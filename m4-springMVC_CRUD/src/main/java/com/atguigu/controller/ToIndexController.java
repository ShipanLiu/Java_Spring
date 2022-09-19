package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToIndexController {




    @RequestMapping("/")
    public String toIndexPage() {
        return "index";
    }

    @RequestMapping("/toRestPage")
    public String toRestPage() {
        return "rest_page";
    }
}
