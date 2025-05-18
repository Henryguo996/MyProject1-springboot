package com.guohenry.myproject1springboot.controller;

import com.guohenry.myproject1springboot.model.CartItem;
import com.guohenry.myproject1springboot.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")                  // dev 階段先全部放行
@RequestMapping("/users/{userId}/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    /* 取得整個購物車 */
    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItem(@PathVariable Integer userId) {
        List<CartItem> list = cartItemService.getCartItem(userId);
        return ResponseEntity.ok(list);
    }

    /* 新增項目 */
    @PostMapping
    public ResponseEntity<CartItem> addToCart(@PathVariable Integer userId,
                                              @RequestBody Map<String, Object> body) {

        Long    productId = Long.valueOf(body.get("productId").toString());
        Integer num       = Integer.valueOf(body.get("num").toString());

        CartItem item = cartItemService.addToCart(userId, productId, num);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    /* 修改數量 / 運送方式 */
    @PutMapping("/{productId}")
    public ResponseEntity<CartItem> updateCart(@PathVariable Integer userId,
                                               @PathVariable Long productId,
                                               @RequestBody Map<String, Object> body) {

        Integer num = body.containsKey("num")
                ? Integer.valueOf(body.get("num").toString()) : null;

        String deliveryId = body.containsKey("deliveryId")
                ? body.get("deliveryId").toString() : null;

        CartItem item = cartItemService.updateCart(userId, productId, num, deliveryId);
        return ResponseEntity.ok(item);
    }

    /* 刪除單筆 */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer userId,
                                           @PathVariable Long productId) {

        cartItemService.deleteCartItem(userId, productId);
        return ResponseEntity.noContent().build();
    }
}
