package com.kdso.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kdso on 2017/11/22.
 */
@SpringBootApplication
@EnableDiscoveryClient //通过@EnableDiscoveryClient向服务中心注册
@EnableHystrix //@EnableHystrix注解开启Hystrix
public class ServiceHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHystrixApplication.class, args);
    }

    //向程序的ioc注入一个bean: restTemplate;并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}