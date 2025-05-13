package com.guohenry.myproject1springboot.controller;


import com.guohenry.myproject1springboot.dto.UserLoginRequest;
import com.guohenry.myproject1springboot.dto.UserRegisterRequest;
import com.guohenry.myproject1springboot.model.User;
import com.guohenry.myproject1springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //request body
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        //創建
        Integer userId =  userService.register(userRegisterRequest);
        //取得
        User user  = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}