package com.guohenry.myproject1springboot.rowmapper;

import com.guohenry.myproject1springboot.constant.ProductCategory;
import com.guohenry.myproject1springboot.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

    public class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product();
            product.setProductId(resultSet.getInt("product_id"));
            product.setProductName(resultSet.getString("product_name"));

            //Enum Type 轉換
            product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));

            product.setImageUrl(resultSet.getString("image_url"));
            product.setPrice(resultSet.getInt("price"));
            product.setStock(resultSet.getInt("stock"));
            product.setDescription(resultSet.getString("description"));
            product.setCreatedDate(resultSet.getTimestamp("created_date"));
            product.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

            return product;
        }
    }

