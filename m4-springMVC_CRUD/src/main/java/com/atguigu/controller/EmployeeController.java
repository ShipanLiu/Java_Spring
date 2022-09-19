/*
*
* because  we have to deal with the employee, so we create a EmployeeController to realize the mapping
*
*
* */



package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    // 重复使用的 字符串 需要 重复定义
    private final static String SUCCESS = "success";


    // 添加
    @RequestMapping(path = "/emp", method = RequestMethod.POST )
    public String saveEmp() {

        System.out.println(" save Emp with success");
        return SUCCESS;
    }

    // 查询
    /*我们要提取URL中的 parameter*/
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String getEmpByID(@PathVariable("id") Integer empid) {
        System.out.println("getEmpByID: " + empid);
        return SUCCESS;
    }



    //修改 （without parameters）
    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String changeEmp() {
        System.out.println("change emp with success");
        return SUCCESS;
    }





    //删除(with parameters)
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id") Integer empId) {
        System.out.println("delete emp with success, the deleted Emp: " + empId);
        return SUCCESS;
    }


}
