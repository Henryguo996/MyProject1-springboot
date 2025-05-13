package com.guohenry.myproject1springboot.dao;

import com.guohenry.myproject1springboot.dto.ProductRequest;
import com.guohenry.myproject1springboot.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
