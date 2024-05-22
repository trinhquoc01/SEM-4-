package com.example.demo.model;

import com.example.demo.entity.Employee;


import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
