package com.guohenry.myproject1springboot.dao;

import com.guohenry.myproject1springboot.dto.UserRegisterRequest;
import com.guohenry.myproject1springboot.model.User;

public interface UserDao {

    User getUserByEmail(String email);

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}