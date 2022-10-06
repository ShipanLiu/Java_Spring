package com.atguigu.controller;

import com.atguigu.pojo.Employee;
import com.atguigu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    /*
         * spring可以自动帮你把Bean里面引用的对象的setter/getter方法省略，它会自动帮你set/get（其实是通过反射技术实现的）。
        @Autowired注释进行自动注入时，spring容器中匹配的候选Bean数目必须有且仅有一个。
        当找不到一个匹配的Bean时，spring容器将抛出BeanCreationException异常，并指出必须至少拥有一个匹配的Bean。
        如果spring容器中拥有多个候选Bean，spring容器在启动时也会抛出BeanCreationException
        这个时候就可以借助@Qualifier注释指定注入Bean的名称，这样@Autowired遇到多个候选Bean的问题也就解决了。
        ————————————————
    * */
    // 这里我们需要 service 的依赖
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;


    @GetMapping("emps")
    public String getAllEmps(Map<String, Object> map) {  // 数据需要共享， 所以放到 map 里面， map 用的 分享的域 是request 域，
                                                         // 想要用session域， 需要用原生的。
        System.out.println("getAllEmps() method is executed");
        List<Employee> allEmp = employeeService.getEmployees();
        System.out.println(allEmp);
        map.put("emps", allEmp);
        return "empList";
    }
}
