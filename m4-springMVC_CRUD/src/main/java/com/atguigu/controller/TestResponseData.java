package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes(value = {"stuName"}) // 把 resuest域（是default 域）中的数据 同步到 session 域中。
public class TestResponseData {


    private final static String SUCCESS = "success";

    @GetMapping("/testResponseData")
    public ModelAndView testResponseData() {

        ModelAndView mv = new ModelAndView();
        // set the name of the view
        mv.setViewName("response_success");
        // now the data(stuName: jiba) will be shared in the pool(request/session/servletSession)
        //  这是我response回去的内容（不会出现乱码的 情况）
        mv.addObject("stuName","ModelAndView");
        return mv;
    }

    /* use Map to deal with the response data*/
    @GetMapping("/testMapResponseData")
    public String testMapResponseData(Map<String,Object> map) {
        map.put("stuName", "Map");
        return "response_success";
    }

    /* use model to deal with the response data*/
    @GetMapping("/testModelResponseData")
    public String testModelResponseData(Model model) {
        model.addAttribute("stuName", "Model");
        return "response_success";
    }

    /* use model to deal with the response data*/
    @GetMapping("/testModelMapResponseData")
    public String testModelMapResponseData(ModelMap modelmap) {
        modelmap.addAttribute("stuName", "ModelMap");
        return "response_success";
    }

    /*测试响应数据， 【其他域 对象】*/
    @GetMapping("/testScopeResponseData")
    public String testScopeResponseData(HttpSession session) {
        session.setAttribute("stuAge", 24);

        return "response_success";
    }


    /* 测试 redirect !!!!*/
    @GetMapping("/testRedirect")
    public String testRedirect() {
        System.out.println("test redirect");
        return "redirect:/redirect_success.html"; //  加上 / 就是绝对路径了
    }


}
