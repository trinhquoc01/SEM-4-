package com.example.demo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getMySQLConnection() throws SQLException {
        Connection con = null;
        String host = "localhost";
        String database = "EmployeeDB";
        String name = "root";
        String password = "";
        String connectionURL = "jdbc:mysql://" + host + ":3308/" + database;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, name, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    public static void main(String[] args) throws SQLException {
        if (DBConnection.getMySQLConnection() != null) {
            System.out.println("Connection successful");
        }
    }
}