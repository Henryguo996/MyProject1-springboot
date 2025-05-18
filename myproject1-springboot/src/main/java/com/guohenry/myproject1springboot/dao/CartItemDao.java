package com.guohenry.myproject1springboot.dao;

import com.guohenry.myproject1springboot.model.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemDao {

    /* 依 userId 取得整個購物車 */
    List<CartItem> findByUserId(Integer userId);

    /* 依 userId + productId 取得一筆 */
    Optional<CartItem> findByUserIdAndProductId(Integer userId, Long productId);

    /* 新增或修改 */
    CartItem save(CartItem item);

    /* 依 userId + productId 刪除 */
    void deleteByUserIdAndProductId(Integer userId, Long productId);
}
