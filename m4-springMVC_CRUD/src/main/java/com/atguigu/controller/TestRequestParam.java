package com.atguigu.controller;


import com.atguigu.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

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

    /* springMVC also supports the pojo params only if the attribute name same as method parameter names*/
    @RequestMapping(path = "/saveEmp", method = RequestMethod.POST)
    public String saveEmp(Employee employee) {
        System.out.println("employee is : " + employee);
        return SUCCESS;
    }
    
    @RequestMapping("/getHeader")
    public String getHeader(@RequestHeader("Accept-Language") String lan,
                            @RequestHeader("Referer") String ref) {
        System.out.println("Accept-Language: " + lan);
        System.out.println("Referer: " + ref);
        return SUCCESS;
    }


    /*创建 session 的时候， 就会自动设置 cookie*/
    @RequestMapping("/setCookie")
    public String setCookie(HttpSession session) {
        //Cookie cookie = new Cookie();
        System.out.println("sessionID: " + session.getId());
        return SUCCESS;
    }

    //获取 session 的时候
    @RequestMapping("/getCookie")
    public String getCookie(@CookieValue("JSESSIONID") String cookieValue) {
        System.out.println("cookieValue: " + cookieValue);
        return SUCCESS;
    }

}
