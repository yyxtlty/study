package com.asean.boot.testspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    public String sayHello(){

        System.out.println("hello world");
        return "Hellon你好";
    }
}
