package com.example.product.service.impl;

import com.example.model.bean.Product;
import com.example.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Product getProduct(Long productId) {
        log.info("服务已被调用");
        Product product = new Product();
        product.setId(0L);
        product.setNum(2);
        product.setName("apple: "+productId);
        product.setPrice(new BigDecimal("100.00"));
        return product;
    }
}
