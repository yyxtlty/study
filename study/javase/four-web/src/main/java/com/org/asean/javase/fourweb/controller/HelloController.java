package com.org.asean.javase.fourweb.controller;


import com.org.asean.javase.fourweb.domain.po.User;
import com.org.asean.javase.fourweb.service.HelloService;
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
    public String  sayHello(){
        System.out.println("hello");
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "/getUser" ,method = RequestMethod.GET)
    public User sayHello(@Param("id") Long id){

        User user = helloService.getUser(id);
        System.out.println(user.toString());
        return user;
    }
}


