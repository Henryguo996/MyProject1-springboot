package com.guohenry.myproject1springboot.service.impl;

import com.guohenry.myproject1springboot.dao.CartItemDao;
import com.guohenry.myproject1springboot.model.CartItem;
import com.guohenry.myproject1springboot.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public List<CartItem> getCartItem(Integer userId) {
        return cartItemDao.findAll();
    }

    @Override
    public CartItem addToCart(Integer userId, Long productId, Integer num) {

        CartItem item = cartItemDao.findByProductId(productId).orElse(new CartItem());

        item.setProductId(productId);

        item.setNum(item.getId() == null ? num : item.getNum() + num);

        item.setDeliveryId("1");

        return cartItemDao.save(item);

    }

    @Override
    public CartItem updateCart(Integer userId, Long productId, Integer num, String deliveryId) {

        CartItem item = cartItemDao.findByProductId(productId).orElseThrow();

        if (num != null) item.setNum(num);

        if (deliveryId != null) item.setDeliveryId(deliveryId);

        return cartItemDao.save(item);

    }

    @Override
    public void deleteCartItem(Integer userId, Long productId) {

        cartItemDao.deleteByProductId(productId);

    }

}
