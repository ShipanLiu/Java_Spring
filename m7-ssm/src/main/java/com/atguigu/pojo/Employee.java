package com.atguigu.pojo;

public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;
    private String empEmail;
    private Integer deptId;

    public Employee() {
    }

    public Employee(Integer empId, String empName, Double empSalary, String empEmail, Integer deptId) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empEmail = empEmail;
        this.deptId = deptId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                ", empEmail='" + empEmail + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
