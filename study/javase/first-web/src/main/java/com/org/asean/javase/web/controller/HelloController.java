package com.org.asean.javase.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String sayHello(){

        System.out.println("hello==========");
        return "hello world";
    }
}
