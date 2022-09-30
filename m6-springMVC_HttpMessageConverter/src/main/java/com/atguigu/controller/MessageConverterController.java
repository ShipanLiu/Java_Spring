package com.atguigu.controller;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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

}
