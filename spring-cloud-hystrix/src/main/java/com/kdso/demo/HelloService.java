package com.kdso.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kdso on 2017/11/23.
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    //对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        //均衡负载
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
