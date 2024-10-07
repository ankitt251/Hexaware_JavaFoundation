package com.hexaware.jdbc;

import java.sql.*;
public class DBConnection {
	static String url = "jdbc:mysql://localhost:3308/hexademodb"; 
	static String user = "root"; 
	static String password = "root"; 
	static Connection conn = null;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {


            
            Class.forName("com.mysql.cj.jdbc.Driver"); 

            
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }

        Statement s = conn.createStatement();
        
	}
}
