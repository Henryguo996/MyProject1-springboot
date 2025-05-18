package com.guohenry.myproject1springboot.dao.impl;

import com.guohenry.myproject1springboot.dao.CartItemDao;
import com.guohenry.myproject1springboot.model.CartItem;
import com.guohenry.myproject1springboot.rowmapper.CartItemRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CartItemDaoImpl implements CartItemDao {

    private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** 取得全部購物車項目 */
    @Override
    public List<CartItem> findAll() {

        String sql = "SELECT id, product_id, num, delivery_id FROM cart_items";

        return namedParameterJdbcTemplate.query(sql, new CartItemRowMapper());

    }

    /** 依商品 id 取得一筆 (如果沒有回傳 Optional.empty()) */
    @Override
    public Optional<CartItem> findByProductId(Long productId) {
        String sql = "SELECT id, product_id, num, delivery_id " +
                "FROM cart_items WHERE product_id = :pid";

        Map<String, Object> params = Map.of("pid", productId);

        List<CartItem> list = namedParameterJdbcTemplate.query(sql, params, new CartItemRowMapper());


        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    /** 新增或更新購物車項目 */
    @Override
    public CartItem save(CartItem item) {

        if (item.getId() == null) {                       // ---------- insert ----------
            String sql = "INSERT INTO cart_items " +
                    "(product_id, num, delivery_id) " +
                    "VALUES (:pid, :num, :did)";

            MapSqlParameterSource ps = new MapSqlParameterSource()
                    .addValue("pid", item.getProductId())
                    .addValue("num", item.getNum())
                    .addValue("did", item.getDeliveryId());

            KeyHolder key = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, ps, key);
            item.setId(key.getKey().longValue());

        } else {                                          // ---------- update ----------
            String sql = "UPDATE cart_items " +
                    "SET num = :num, delivery_id = :did " +
                    "WHERE id = :id";

            Map<String, Object> params = new HashMap<>();
            params.put("num", item.getNum());
            params.put("did", item.getDeliveryId());
            params.put("id", item.getId());

            namedParameterJdbcTemplate.update(sql, params);
        }

        return item;
    }

    /** 依商品 id 刪除 */
    @Override
    public void deleteByProductId(Long productId) {
        String sql = "DELETE FROM cart_items WHERE product_id = :pid";
        namedParameterJdbcTemplate.update(sql, Map.of("pid", productId));
    }
}
