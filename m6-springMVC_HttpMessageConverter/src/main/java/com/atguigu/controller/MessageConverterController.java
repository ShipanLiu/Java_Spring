package com.atguigu.controller;

import com.atguigu.pojo.Employee;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @ResponseBody  // if you add "ResponseBody hier, then all og the return inside of the method will not
// return html but just string"
public class MessageConverterController {

    private final static String SUCCESS = "success";

    @RequestMapping("testRequestBody")
    // 参数可以用 pojo， 就用 pojo， 没有 pojo， 有哪个参数， 就写哪个参数
//    public String testRequestBody(String lastName, String email) {
//
//        return SUCCESS;
//    }
    // 现在我们用请求体。 来把 参数放进来
    public String testRequestBody(@RequestBody String reqBody) {
        System.out.println("reqBody = " + reqBody);
        return SUCCESS;
    }


    // use HttpEntity
    @RequestMapping("/testHttpEntity")
    public String testHttpEntity(HttpEntity<String> request) {  // Both headers and body are string
        System.out.println("reqHeader = " + request.getHeaders());
        System.out.println("reqBody = " + request.getBody());
        return SUCCESS;
    }

    // test @ResponseBody
    @RequestMapping("/testResponseBody")
    @ResponseBody // 不再 返回 html 页面了，反而返回 string
    public String testResponseBody() {
        System.out.println("testResponseBody");
        return "hello, this is a response message, wish you lucky";
    }


    @RequestMapping("/testJson")
    @ResponseBody //在导入 处理 json 的jar 包之后， @ResponseBody 会自动 返回json 格式
    public Employee testJson() {
        // 将Employee 对象转换成 json 格式 响应
        Employee emp = new Employee(1001, "jier", "jier@gmail.com", 1);
        System.out.println("now deal with the json");
        return emp;

        // 我给前端工程师  返回  json 格式的 数据 !!!  前后端 就是靠 json 进行沟通的

        /*
        * {
            "id": 1001,
            "lastName": "jier",
            "email": "jier@gmail.com",
            "gender": 1
          }
        *
        *
        * */
    }

}
