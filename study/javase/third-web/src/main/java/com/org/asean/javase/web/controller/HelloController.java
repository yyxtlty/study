package com.org.asean.javase.web.controller;

import com.org.asean.javase.web.domain.po.User;
import com.org.asean.javase.web.service.HelloService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String sayHello(@RequestParam("name") String name){

        System.out.println("name is : " + name);
        return "hello" + name;
    }
    @ResponseBody
    @RequestMapping(value = "/getUser" ,method = RequestMethod.GET)
    public User getUser(@RequestParam("id") Long id){

        System.out.println("传入的ID是：" + id);

        User user = helloService.getUser(id);

        System.out.println(user.toString());
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/saveUser" ,method = RequestMethod.GET)
    public void saveUser(){

       System.out.println("开始插入数据");

       helloService.saveUser();

    }
}
