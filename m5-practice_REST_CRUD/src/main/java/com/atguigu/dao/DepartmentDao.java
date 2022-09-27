package com.atguigu.dao;

import com.atguigu.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository(value = "depDao")  // Repository 是 仓库的意思
public class DepartmentDao {
    private static Map<Integer, Department> deps = null;

    static {
        deps = new HashMap<Integer, Department>();

        deps.put(101, new Department(101, "Dep1"));
        deps.put(102, new Department(102, "Dep2"));
        deps.put(103, new Department(103, "Dep3"));
        deps.put(104, new Department(104, "Dep4"));
        deps.put(105, new Department(105, "Dep5"));
    }

    public Collection<Department> getDeps() {
        return deps.values();
    }

    // get a single dep
    public Department getDep(Integer id) {
        return deps.get(id);
    }
}
