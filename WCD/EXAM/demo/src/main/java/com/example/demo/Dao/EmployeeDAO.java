package com.example.demo.Dao;

import com.example.demo.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public boolean insertEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee (FullName, Birthday, Address, Position, Department) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getMySQLConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getFullName());
            pstmt.setDate(2, Date.valueOf(employee.getBirthday()));
            pstmt.setString(3, employee.getAddress());
            pstmt.setString(4, employee.getPosition());
            pstmt.setString(5, employee.getDepartment());

            return pstmt.executeUpdate() > 0;
        }
    }

    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> listEmployee = new ArrayList<>();
        String sql = "SELECT * FROM Employee";

        try (Connection conn = DBConnection.getMySQLConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("ID"));
                employee.setFullName(rs.getString("FullName"));
                employee.setBirthday(rs.getDate("Birthday").toString());
                employee.setAddress(rs.getString("Address"));
                employee.setPosition(rs.getString("Position"));
                employee.setDepartment(rs.getString("Department"));

                listEmployee.add(employee);
            }
        }

        return listEmployee;
    }
}
