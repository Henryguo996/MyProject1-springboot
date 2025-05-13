package com.guohenry.myproject1springboot.service.impl;


import com.guohenry.myproject1springboot.dao.UserDao;
import com.guohenry.myproject1springboot.dto.UserLoginRequest;
import com.guohenry.myproject1springboot.dto.UserRegisterRequest;
import com.guohenry.myproject1springboot.model.User;
import com.guohenry.myproject1springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;


@Component
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //檢查 user是否存在
        if(user ==null){
            log.warn("該 email {} 並未註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成密碼的雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(
                userLoginRequest.getPassword().getBytes());

        //比較密碼
        if(user.getPassword().equals(hashPassword)){
            return ResponseEntity.ok().body(user).getBody();
        }else{
            log.warn("email {} 的密碼錯誤",user.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //Check 註冊的Email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if(user != null){
            log.warn("該 email {} 已被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成密碼的雜湊表
        String hashPassword = DigestUtils.md5DigestAsHex(
                userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashPassword);

        //Create account
        return userDao.createUser(userRegisterRequest);
    }

}
