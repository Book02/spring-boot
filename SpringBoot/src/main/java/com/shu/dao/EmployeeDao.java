package com.shu.dao;

import com.shu.entity.Department;
import com.shu.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private  DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001,"aa","2512124197@qq.com",0,new Department(101,"教学部"),new Date()));
        employees.put(1002,new Employee(1002,"bb","2512124197@qq.com",1,new Department(102,"市场部"),new Date()));
        employees.put(1003,new Employee(1003,"cc","2512124197@qq.com",1,new Department(103,"运营部"),new Date()));
        employees.put(1004,new Employee(1004,"dd","2512124197@qq.com",0,new Department(104,"技术部"),new Date()));
        employees.put(1005,new Employee(1005,"ee","2512124197@qq.com",1,new Department(105,"客服部"),new Date()));

    }

    private  static  Integer initId=1006;
    //主键自增
    private static Integer initialID = 6;

    //增加一个员工
    public void addEmployee(Employee employee) {
        if (employee.getId() == null)
            employee.setId(initialID);
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeByID(Integer id) {
        return employees.get(id);
    }

    //通过id删除员工
    public void deleteEmployeeByID(int id) {
        employees.remove(id);
    }


}
