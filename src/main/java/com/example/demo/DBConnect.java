package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DBConnect {
    static Connection connection = null;

    public Connection getConnection() {
        String databaseName = "demo";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        String username = "root1";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Database connected!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database connection failure!");
        }

        return connection;
    }
}
