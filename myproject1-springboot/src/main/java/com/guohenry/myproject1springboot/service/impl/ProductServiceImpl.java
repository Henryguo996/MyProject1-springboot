package com.guohenry.myproject1springboot.service.impl;

import com.guohenry.myproject1springboot.dao.ProductDao;
import com.guohenry.myproject1springboot.model.Product;
import com.guohenry.myproject1springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
