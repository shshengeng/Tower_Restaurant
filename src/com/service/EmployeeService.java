package com.service;

import com.dao.EmployeeDao;
import com.domain.Employee;

public class EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao();

    public EmployeeService(){};

    //get an employ object by input id and password
    public Employee getEmployeeByIdAndPwd(String id, String password) throws Exception {
        return employeeDao.querySingle("select * from employee where empId = ? and password = md5(?)", Employee.class, "7362612", "123456");
    }
}
