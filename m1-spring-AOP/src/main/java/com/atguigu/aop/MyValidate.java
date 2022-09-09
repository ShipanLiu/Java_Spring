package com.atguigu.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/* 这个是 第二个切面。 第一个切面是 MyLogging*/
@Component
@Aspect
@Order(value = 1)// 优先级， 越小 优先级越高
public class MyValidate {

    /*  这是数据的验证功能， 所以只是使用 @Before 就行了*/
    @Pointcut(value="execution(* com.atguigu.aop.impl.*.*(..))")
    public void myPointCut(){}


    @Before(value = " myPointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("MyValidate: this method happens before add");
    }
}
