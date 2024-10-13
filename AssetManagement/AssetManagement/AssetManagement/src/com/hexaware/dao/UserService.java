package com.hexaware.dao;

import com.hexaware.entity.User;

public interface UserService {
 boolean registerUser (User user);
 User loginUser(String username, String password);
}
