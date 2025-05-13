package com.guohenry.myproject1springboot.service.impl;

import com.guohenry.myproject1springboot.dao.ProductDao;
import com.guohenry.myproject1springboot.dto.ProductQueryParams;
import com.guohenry.myproject1springboot.dto.ProductRequest;
import com.guohenry.myproject1springboot.model.Product;
import com.guohenry.myproject1springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer countProducts(ProductQueryParams prodcutQueryParams) {
        return productDao.countProducts(prodcutQueryParams);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
            productDao.updateProduct(productId,productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }


}
