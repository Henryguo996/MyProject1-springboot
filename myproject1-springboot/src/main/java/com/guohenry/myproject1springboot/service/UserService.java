package com.guohenry.myproject1springboot.service;

import  com.guohenry.myproject1springboot.dto.UserLoginRequest;
import  com.guohenry.myproject1springboot.dto.UserRegisterRequest;
import  com.guohenry.myproject1springboot.model.User;

public interface UserService {
    User login(UserLoginRequest userLoginRequest);

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}