package com.guohenry.myproject1springboot.service;

import com.guohenry.myproject1springboot.model.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> getCartItem(Integer userId);

    CartItem addToCart(Integer userId, Long productId, Integer num);

    CartItem updateCart(Integer userId, Long productId, Integer num, String deliveryId);

    void deleteCartItem(Integer userId, Long productId);
}
