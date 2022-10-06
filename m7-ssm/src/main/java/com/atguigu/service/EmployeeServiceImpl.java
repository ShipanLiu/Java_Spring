package com.atguigu.service;

import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.pojo.Employee;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

    // /* 这个mapper 已经自动装配了   */
    /*  由于 spring.xml 里面的    <mybatis-spring:scan base-package="com.atguigu.mapper"></mybatis-spring:scan>  */
    /*
    * 否则还要写 ：
    * private SqlSession session;
    * OrderMapper mapper = session.getMapper(OrderMapper.class);
      Order order = mapper.getAllEmp();
    * */
    @Autowired // 引入的属性 要用 autowired 来引用， 飘红线 不用管
    @Qualifier("employeeMapper") // 这个 和 autowired 一起使用。
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.selectAll();
    }
}
