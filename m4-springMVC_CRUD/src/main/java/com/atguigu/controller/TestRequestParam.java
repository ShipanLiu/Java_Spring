package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestRequestParam {

    private final static String SUCCESS = "success";

    @RequestMapping(path = "/requestParam1")
    //if you set the name of the parameters from this method is same as the parameter passed in the request
    // then you can just use the parameters directly
    public String requestParam1(String stuName, @RequestParam("sAge") Integer stuAge) {
        System.out.println("the stuName + stuAge from the request is :" + stuName + ' ' + stuAge);
        return SUCCESS;
    }


}
