package com.kdso.demo;

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

    public String hiService(String name) {
        //均衡负载
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

}
