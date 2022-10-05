package com.atguigu.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("myInterceptor")  // 为了 在 springmvc.xml 里面实现自动装配
public class MyInterceptor implements HandlerInterceptor {

    // execute before controller
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("MyInterceptor => preHandle ");
        return true;
    }

    // execute after controller
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor => postHandle ");
    }

    // 最后执行【渲染视图之后】
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
        System.out.println("MyInterceptor => afterCompletion ");
    }

}
