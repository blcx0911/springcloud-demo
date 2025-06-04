package com.example.order.service;

import com.example.model.bean.Order;
public interface OrderService {
    Order getOrder(Long userId, Long productId);
}
