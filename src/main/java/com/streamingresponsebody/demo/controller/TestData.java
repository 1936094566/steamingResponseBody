package com.streamingresponsebody.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestData {
    @RequestMapping("/data")
    public Object getData(){
        Map<Object, Object> map = new HashMap<>();
        return map;
    }

}
