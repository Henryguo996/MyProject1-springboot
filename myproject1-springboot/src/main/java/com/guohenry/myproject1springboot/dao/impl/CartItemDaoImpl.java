package com.guohenry.myproject1springboot.dao.impl;

import com.guohenry.myproject1springboot.dao.CartItemDao;
import com.guohenry.myproject1springboot.model.CartItem;
import com.guohenry.myproject1springboot.rowmapper.CartItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* 依 userId 取全部 */
    @Override
    public List<CartItem> findByUserId(Integer userId) {
        String sql = """
                     SELECT id, user_id, product_id, num, delivery_id
                     FROM cart_items
                     WHERE user_id = :uid
                     """;
        return namedParameterJdbcTemplate.query(sql,
                Map.of("uid", userId),
                new CartItemRowMapper());
    }

    /* 依 userId + productId 取單筆 */
    @Override
    public Optional<CartItem> findByUserIdAndProductId(Integer userId, Long productId) {

        String sql = """
                     SELECT id, user_id, product_id, num, delivery_id
                     FROM cart_items
                     WHERE user_id = :uid AND product_id = :pid
                     """;

        Map<String, Object> params = Map.of("uid", userId, "pid", productId);

        List<CartItem> list = namedParameterJdbcTemplate.query(sql, params, new CartItemRowMapper());

        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    /* 新增或修改 */
    @Override
    public CartItem save(CartItem item) {

        if (item.getId() == null) {                     // ------- insert -------
            String sql = """
                         INSERT INTO cart_items
                         (user_id, product_id, num, delivery_id)
                         VALUES (:uid, :pid, :num, :did)
                         """;

            MapSqlParameterSource ps = new MapSqlParameterSource()
                    .addValue("uid", item.getUserId())
                    .addValue("pid", item.getProductId())
                    .addValue("num", item.getNum())
                    .addValue("did", item.getDeliveryId());

            KeyHolder key = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, ps, key);
            item.setId(key.getKey().longValue());

        } else {                                        // ------- update -------
            String sql = """
                         UPDATE cart_items
                         SET num = :num,
                             delivery_id = :did
                         WHERE id = :id
                         """;

            Map<String, Object> params = new HashMap<>();
            params.put("num", item.getNum());
            params.put("did", item.getDeliveryId());
            params.put("id",  item.getId());

            namedParameterJdbcTemplate.update(sql, params);
        }
        return item;
    }

    /* 刪除單筆 */
    @Override
    public void deleteByUserIdAndProductId(Integer userId, Long productId) {

        String sql = """
                     DELETE FROM cart_items
                     WHERE user_id = :uid AND product_id = :pid
                     """;

        namedParameterJdbcTemplate.update(sql,
                Map.of("uid", userId, "pid", productId));
    }
}
