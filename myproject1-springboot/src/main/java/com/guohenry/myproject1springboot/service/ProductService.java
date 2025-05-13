package com.guohenry.myproject1springboot.service;

import com.guohenry.myproject1springboot.dto.ProductRequest;
import com.guohenry.myproject1springboot.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
