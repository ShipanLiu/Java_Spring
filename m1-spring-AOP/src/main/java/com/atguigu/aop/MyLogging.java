package com.atguigu.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component("myLogging") // 普通的组件
@Aspect  // 当前业务是 非核心业务
public class MyLogging {

    @Before(value = "execution(public int com.atguigu.aop.impl.CalcImpl.add(int , int ))")  // 表示在执行  add（） 之前执行的函数,  在哪个类里面的哪个方法
    public void beforeMethod(JoinPoint joinPoint) {  // 这个 关节 JoinPoint 里面包含 add()的 所有信息
        // get the target method name
        String targetMethodName = joinPoint.getSignature().getName();
        // get the args[]
        Object[] args = joinPoint.getArgs();
        System.out.println("this mehod is executed before add()");
        System.out.println("method name: " + targetMethodName + "args: " + args);
    }





}
