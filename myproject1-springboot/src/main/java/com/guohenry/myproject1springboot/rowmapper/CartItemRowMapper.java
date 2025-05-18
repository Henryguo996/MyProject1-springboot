package com.guohenry.myproject1springboot.rowmapper;

import com.guohenry.myproject1springboot.model.CartItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemRowMapper implements RowMapper<CartItem> {


        @Override
        public CartItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            CartItem cartItem = new CartItem();
            cartItem.setId(resultSet.getLong("id"));
            cartItem.setProductId(resultSet.getLong("product_id"));
            cartItem.setNum(resultSet.getInt("num"));
            cartItem.setDeliveryId(resultSet.getString("delivery_id"));
            return cartItem;
        }

}



