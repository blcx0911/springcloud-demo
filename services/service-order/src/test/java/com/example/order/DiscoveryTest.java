package com.example.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class DiscoveryTest {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Test
    public void discoveryTest() {
        discoveryClient.getServices().forEach(System.out::println);
        for (String service : discoveryClient.getServices()) {
            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                System.out.printf("service:"+service+"host: "+instance.getHost()+" port: "+instance.getPort()+" url: "+instance.getUri());
            }
        }
    }
    @Test
    public void loadBalancerTest(){
        for (int i = 0; i < 5; i++) {
            ServiceInstance choose = loadBalancerClient.choose("service-product");
            System.out.println(choose.getUri());
        }
    }
}
