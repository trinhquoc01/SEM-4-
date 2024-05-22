package com.example.demo.model;

import com.example.demo.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void addEmployee(Employee employee) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getMySQLConnection();
            String sql = "INSERT INTO Employees (FullName, Birthday, Address, Position, Department) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getFullName());
            stmt.setDate(2, employee.getBirthday());
            stmt.setString(3, employee.getAddress());
            stmt.setString(4, employee.getPosition());
            stmt.setString(5, employee.getDepartment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getMySQLConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Employees";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String fullName = rs.getString("FullName");
                Date birthday = rs.getDate("Birthday");
                String address = rs.getString("Address");
                String position = rs.getString("Position");
                String department = rs.getString("Department");
                Employee employee = new Employee(fullName, birthday, address, position, department);
                employee.setId(id);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }
}
