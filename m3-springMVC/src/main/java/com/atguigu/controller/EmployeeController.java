package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller  // 首先你得是一个controller， 为了autowire
@RequestMapping("/EmpController")
public class EmployeeController {

    // beacause we use the string "success" very frequent, so that we can define one final string
    // 定义一个静态的常量
    private final static String SUCCESS = "success";

    @RequestMapping(value = "/saveEmp",
                    method = RequestMethod.GET,
                    params = "lastname")        //  默认支持的 method就是 GET
    public String saveEmp() {
        System.out.println("sout: save emp info 牛逼");
        return "success";
    }

    @RequestMapping(path = "/testAnt/?") // ？ 匹配一个字符。
    public String testAnt1() {
        return SUCCESS;
    }

    @RequestMapping(path = "/testAnt/*") // * 匹配任意多个字符。
    public String testAnt2() {
        return SUCCESS;
    }

    @RequestMapping(path = "/testAnt/**") // * 匹配任意多个字符。
    public String testAnt3() {
        return SUCCESS;
    }

    // <a th:href="@{/EmpController/testGetParameter/1001}">获取 parameter的</a><br/>
    //  在URL里面写了 ： 1001  ， 你要获得这个 1001， 就需要使用：
    @RequestMapping(path = "/testGetParameter/{parameterID}")
    public String testGetParameter(@PathVariable("parameterID") Integer empID) {
        System.out.println("the empID is:" + empID);
        return SUCCESS;
    }
}
