package com.org.asean.javase.boot.thriftclient.controller;


import com.org.asean.javase.boot.thriftclient.server.impl.PersonThriftServiceImpl;
import com.org.asean.javase.server.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonThriftServiceImpl personThriftService;

    @RequestMapping(value = "/hello")
    public String sayHello(){

        System.out.println("hello");

        return "hello";

    }

    @RequestMapping(value = "/getPerson")
    public Person getPerson(){
        Person person = personThriftService.getPerson();
        System.out.println(person.toString());
        return person;
    }
}
