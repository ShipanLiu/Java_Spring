package com.atguigu.dao;

import com.atguigu.pojo.Department;
import com.atguigu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository(value = "empDao")
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;


    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "aa", "aa@163.com", 0, new Department(101, "Dep1")));
        employees.put(1002, new Employee(1002, "bb", "bb@163.com", 1, new Department(102, "Dep2")));
        employees.put(1003, new Employee(1003, "cc", "cc@163.com", 0, new Department(102, "Dep3")));
        employees.put(1004, new Employee(1004, "dd", "dd@163.com", 0, new Department(104, "Dep4")));
        employees.put(1005, new Employee(1005, "ee", "ee@163.com", 1, new Department(105, "Dep5")));
    }

    private static Integer initId = 1006;

    /*
    * add employee into Hashmap enployees (if you pass a id, then just save, if you do not have a id, then give you one id)
    * */
    public void save(Employee emp) {
        // if you do not pass a emp id to me
        if(emp.getId() == null) {
            emp.setId(initId++);
        }
        // there is only a department Nr in the emp but not a name, now let us get the Object department (id, name), and insert it into the
        // new created emp.
        emp.setDepartment(departmentDao.getDep(emp.getDepartment().getId()));  // 我传进来的emp 只带有 dep 的id， 没有 dep的名字
        employees.put(emp.getId(), emp);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee get(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }



}
