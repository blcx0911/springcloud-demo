package com.example.order.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.example.model.bean.Order;
import com.example.model.bean.Product;
import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Override
    public Order getOrder(Long userId, Long productId) {
        Product product = getProduct(productId);

        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setNickName("菠萝吹雪");
        order.setAddress("花果山");
        order.setProductList(Arrays.asList(product));
        order.setUserId(userId);
        return order;
    }

    public Product getProduct(Long productId) {
        //List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        //ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url= String.format("http://service-product"+"/product/%s", productId);
        log.info("远程调用url:{}",url);
        Product product = restTemplate.getForObject(url, Product.class);
        log.info("订单信息"+product.toString());
        return product;
    };
}
