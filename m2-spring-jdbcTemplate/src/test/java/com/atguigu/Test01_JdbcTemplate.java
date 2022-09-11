package com.atguigu;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test01_JdbcTemplate {
    @Test
    public void testJdbcTemplate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemp = context.getBean("jdbcTemplate", JdbcTemplate.class);
        System.out.println(jdbcTemp);

        // 测试 API 增删改(全哟个update) 查： query

        /*因为 sql 写在  string 里面， 所以 不好*/


    }
}
