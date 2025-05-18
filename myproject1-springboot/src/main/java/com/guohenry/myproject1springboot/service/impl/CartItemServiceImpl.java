package com.guohenry.myproject1springboot.service.impl;

import com.guohenry.myproject1springboot.dao.CartItemDao;
import com.guohenry.myproject1springboot.model.CartItem;
import com.guohenry.myproject1springboot.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemDao cartItemDao;

    /* 取得購物車 */
    @Override
    public List<CartItem> getCartItem(Integer userId) {
        return cartItemDao.findByUserId(userId);
    }

    /* 加入購物車 (若已存在就累加數量) */
    @Override
    public CartItem addToCart(Integer userId, Long productId, Integer num) {

        CartItem item = cartItemDao
                .findByUserIdAndProductId(userId, productId)
                .orElse(new CartItem());

        item.setUserId(userId);
        item.setProductId(productId);

        if (item.getId() == null) {          // 新增
            item.setNum(num);
            item.setDeliveryId("1");
        } else {                             // 累加
            item.setNum(item.getNum() + num);
        }
        return cartItemDao.save(item);
    }

    /* 修改數量 / 運送方式 */
    @Override
    public CartItem updateCart(Integer userId,
                               Long productId,
                               Integer num,
                               String deliveryId) {

        CartItem item = cartItemDao
                .findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (num != null)       item.setNum(num);
        if (deliveryId != null) item.setDeliveryId(deliveryId);

        return cartItemDao.save(item);
    }

    /* 刪除單筆 */
    @Override
    public void deleteCartItem(Integer userId, Long productId) {
        cartItemDao.deleteByUserIdAndProductId(userId, productId);
    }
}
