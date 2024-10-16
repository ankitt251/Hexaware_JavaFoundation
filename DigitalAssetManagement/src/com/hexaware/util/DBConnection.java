package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Database connection details hardcoded here
                String url = "jdbc:mysql://localhost:3308/asset_management";
                String username = "root";  
                String password = "root"; 

                // Establish the connection to the database
                connection = DriverManager.getConnection(url, username, password);

                System.out.println("Database connection established successfully.");

            } catch (ClassNotFoundException e) {
                System.out.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error while establishing connection to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
