package com.example.order.controller;

import com.example.model.bean.Order;
import com.example.model.bean.Product;
import com.example.order.properties.OrderProperties;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderProperties orderProperties;

    //创建订单
    @GetMapping("/create")
    public Order getOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        Order order = orderService.getOrder(userId, productId);
        return order;
    }

    //创建订单
    @GetMapping("/config")
    public String getConfig() {

        return "userName" + orderProperties.getName() + "userPassword" + orderProperties.getPassword();
    }


}
