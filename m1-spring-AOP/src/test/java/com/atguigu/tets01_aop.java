package com.atguigu;


import com.atguigu.aop.Calc;
import com.atguigu.aop.impl.CalcImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = "classpath:applicationContext_aop.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class tets01_aop {

    @Autowired
    @Qualifier("calc")
    private Calc calc;


    @Test
    public void testAop() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_aop.xml");
//        Calc calc = context.getBean("calc", Calc.class);  // or CalcImpl.class
//        // 取出 类， 定义对象
        int addValue = calc.add(1, 2);
    }

}
