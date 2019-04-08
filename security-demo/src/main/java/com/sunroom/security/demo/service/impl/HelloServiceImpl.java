package com.sunroom.security.demo.service.impl;

import com.sunroom.security.demo.service.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String greet(String name) {
        return "Hi, "+name;
    }
}
