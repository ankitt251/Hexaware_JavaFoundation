package com.hexaware.dao;

import com.hexaware.entity.*;
import com.hexaware.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
 private Connection connection;

 public UserServiceImpl() {
     connection = DBConnection.getConnection();
 }

 @Override
 public boolean registerUser (User user) {
     try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
         statement.setString(1, user.getUsername());
         statement.setString(2, user.getPassword());
         statement.setString(3, user.getRole());
         statement.executeUpdate();
         return true;
     } catch (SQLException e) {
         // Handle exception
         return false;
     }
 }

 @Override
 public User loginUser(String username, String password) {
     try {
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
         statement.setString(1, username);
         statement.setString(2, password);
         ResultSet resultSet = statement.executeQuery();
         if (resultSet.next()) {
             User user = new User();
             user.setUserId(resultSet.getInt("user_id"));
             user.setUsername(resultSet.getString("username"));
             user.setPassword(resultSet.getString("password"));
             user.setRole(resultSet.getString("role"));
             return user;
         } else {
             return null;
         }
     } catch (SQLException e) {
         // Handle exception
         return null;
     }
 }
}
