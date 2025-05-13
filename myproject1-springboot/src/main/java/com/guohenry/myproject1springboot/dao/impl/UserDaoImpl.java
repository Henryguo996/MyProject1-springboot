package com.guohenry.myproject1springboot.dao.impl;

import com.guohenry.myproject1springboot.dao.UserDao;
import com.guohenry.myproject1springboot.dto.UserRegisterRequest;
import com.guohenry.myproject1springboot.model.User;
import com.guohenry.myproject1springboot.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public User getUserByEmail(String email) {
        String sql = " SELECT user_id, email, password, created_date, last_modified_date " +
                " FROM `user` WHERE email = :email ";
        Map<String,Object> map = new HashMap<>();
        map.put("email",email);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList.size() > 0 ? userList.get(0) : null;

    }

    @Override
    public User getUserById(Integer userId) {
        String sql = " SELECT user_id, email, password, created_date, last_modified_date " +
                " FROM `user` WHERE user_id = :userId ";
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        //user.size > 0 回傳第一筆資料，否則回傳null
        return userList.size() > 0 ? userList.get(0) : null;

    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = " INSERT INTO `user`(email, password, created_date, last_modified_date) " +
                " VALUES(:email, :password, :createdDate, :lastModifiedDate) ";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        map.put("createdDate", timestamp);
        map.put("lastModifiedDate", timestamp);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
        int userId = keyHolder.getKey().intValue();

        return userId;

    }
}