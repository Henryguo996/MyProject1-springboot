package com.guohenry.myproject1springboot.service;


import com.guohenry.myproject1springboot.dto.ProductQueryParams;
import com.guohenry.myproject1springboot.dto.ProductRequest;
import com.guohenry.myproject1springboot.model.Product;

import java.util.List;

public interface ProductService {



    Integer countProducts(ProductQueryParams prodcutQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
