package com.org.asean.javase.five.web.controller;

import com.org.asean.javase.five.web.domain.po.User;
import com.org.asean.javase.five.web.service.HelloService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String sayHello(){

        System.out.println("hello明");
        return "hello明";
    }

    @ResponseBody
    @RequestMapping(value = "/getUser" ,method = RequestMethod.GET)
    public User getUser(@Param("id") Long id){

        User user = helloService.getUser(id);
        return user;

    }

}
