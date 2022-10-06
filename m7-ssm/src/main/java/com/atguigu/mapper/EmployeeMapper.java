package com.atguigu.mapper;

import com.atguigu.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/*  由于 spring.xml 里面的    <mybatis-spring:scan base-package="com.atguigu.mapper"></mybatis-spring:scan>  */
/* 这个mapper 已经装配了 */
//@Repository
@Repository("employeeMapper")
public interface EmployeeMapper {
    // get info of all emps
    public List<Employee> selectAll();
}
