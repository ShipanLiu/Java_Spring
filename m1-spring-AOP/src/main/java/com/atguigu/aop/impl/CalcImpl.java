package com.atguigu.aop.impl;

import com.atguigu.aop.Calc;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("calc")
public class CalcImpl implements Calc {

    @Override
    public int add(int a, int b) {
        int result = a + b;
        System.out.println("doing adding");
        return result;
    }
}
