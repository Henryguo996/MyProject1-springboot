package com.guohenry.myproject1springboot.dao;

import com.guohenry.myproject1springboot.model.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemDao {


    List<CartItem> findAll();

    Optional<CartItem> findByProductId(Long productId);

    CartItem save(CartItem item);

    void deleteByProductId(Long productId);

}
