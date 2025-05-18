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
@CrossOrigin(origins = "*")               // 開發階段先全部放行，正式環境請鎖定網域
@RequestMapping("/users/{userId}/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    /** 取得使用者整個購物車 */
    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItem(@PathVariable Integer userId) {

        List<CartItem> cartItemList = cartItemService.getCartItem(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemList);
    }

    /** 新增一筆項目
     *  body 範例：{"productId":12,"num":1}
     */
    @PostMapping
    public ResponseEntity<CartItem> addToCart(
            @PathVariable Integer userId,
            @RequestBody Map<String, Object> body) {

        Long    productId = Long.valueOf(body.get("productId").toString());
        Integer num       = Integer.valueOf(body.get("num").toString());

        CartItem item = cartItemService.addToCart(userId, productId, num);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    /** 修改數量或運送方式
     *  body 可含 {"num":3}、{"deliveryId":"2"} 或兩者都有
     */
    @PutMapping("/{productId}")
    public ResponseEntity<CartItem> updateCart(
            @PathVariable Integer userId,
            @PathVariable Long    productId,
            @RequestBody Map<String, Object> body) {

        Integer num = body.containsKey("num")
                ? Integer.valueOf(body.get("num").toString()) : null;

        String deliveryId = body.containsKey("deliveryId")
                ? body.get("deliveryId").toString() : null;

        CartItem item = cartItemService.updateCart(userId, productId, num, deliveryId);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    /** 從購物車刪除一項 */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Integer userId,
            @PathVariable Long    productId) {

        cartItemService.deleteCartItem(userId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204
    }
}
