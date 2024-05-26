package com.example.demo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {public static Connection getMySQLConnection() throws SQLException {
    Connection conn = null;
    String hostName = "localhost";
    String dbName = "EmployeeManagement_Java";
    String userName = "root";
    String password = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println("Không tìm thấy driver JDBC MySQL!");
        e.printStackTrace();
        return null;
    }

    String connURL = "jdbc:mysql://"+ hostName + ":3306/" + dbName;
    conn = DriverManager.getConnection(connURL,userName,password);
    return conn;
}

    public static void main(String[] args) throws SQLException {
        if(getMySQLConnection() != null) {
            System.out.println("Kết nối thành công!");
        }
    }
}