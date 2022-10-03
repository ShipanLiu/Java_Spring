package com.atguigu.controller;

import com.atguigu.dao.DepartmentDao;
import com.atguigu.dao.EmployeeDao;
import com.atguigu.pojo.Department;
import com.atguigu.pojo.Employee;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

// controller 是和 前端交互用的。  连接  前端  的  Dao  ， 处理连接的业务。   在 更加复杂的业务中， 放在service 里面
@Controller(value = "empController")
public class EmpController {

    // Controller 里面需要 Dao， 所以吧 EmployeeDao 来引进
    @Autowired
    @Property(name = "empDao")
    private EmployeeDao empDao;

    @Autowired
    @Property(name = "depDao")
    private DepartmentDao depDao;

    //@RequestMapping(value = "/emps", method = RequestMethod.GET)  // this is another way
    @GetMapping("/emps") // this uses get method , so it is "getMapping"
    public String getAllEmps(Map<String, Object> map) {
        // get all the emps
        Collection<Employee> emps = empDao.getAll();
        // deliver these emps info out
        map.put("emps", emps);
        return "emp_list";  //  return a emp list HTML  page

    }

    /*
    * 跳转到 添加 Emp 的页面，在之前肯定要把所有的 Dep 查出来 （因为 在 员工添加页面里面要用）
    * 这就 肯定 会用到 DepDao 了
    * */

    @GetMapping("/toSaveEmpPage")
    public String toSaveEmpPage(Map<String, Object> map) {
        Collection<Department> depts = depDao.getDeps();
        // we put all queried data into the map, this map is like a shared memory.
        map.put("depts", depts);
        return "emp_add_save";
    }

    /*
    *
    * here add emp
    *
    * Pojo atomatic parameting
    *
    * */
    @PostMapping("/emps")
    public String saveEmp(Employee emp) {
        System.out.println("emp:" + emp);

        empDao.save(emp);

        /* after saving, getAll again and redirect to getAllEmps() and will show the the emp_list_html again  page*/
        /*when you want redirect to a html page, you have to write "redirect:/new_page.html", please do not forget the .html*/
        return "redirect:/emps"; // redirect to getAllEmps()
    }


    /*
    *
    * delete
    *
    * */

    @DeleteMapping("/emps/{empId}")
    //@PathVariable("empId") Integer id, get the variable from the URL and map it to the:  Integer id
    public String deleteEmp(@PathVariable("empId") Integer id) {
        // delete this emp
        empDao.delete(id);
        return "redirect:/emps";  //  go back to emp_list
    }

    /*
    *
    *
    * 跳转到  modify 页面
    *先把所有的 页面查出来
    *
    * */

    @GetMapping("/toUpdateEmp/{empId}")
    public String toUpdateEmp(@PathVariable("empId") Integer id, Map<String,Object> map) { // 我不知到前端会 传给我多少参数，直接用一个 map
        Employee emp = empDao.get(id);
        map.put("emp", emp);  // 把东西 放入到 map 里面， 相当于 放入到   request 域   里面
        Collection<Department> depts = depDao.getDeps();
        map.put("depts", depts);
        return "emp_update";
    }



    /*  modify 的功能书写  */

    @PutMapping("/emps")
    public String updateEmp(Employee emp) { // form 提交的 数据会 自动生成 Employee 格式， 然后 pojo 入参。
        // 调用修改
        System.out.println(emp);
        empDao.save(emp);
        return "redirect:/emps";  // 修改完之后， 也要查一下。 go to getAllEmps()
    }


}
