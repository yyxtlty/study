package com.org.asean.javase.server;

import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPerson() throws TException {
        Person person = new Person(2, "tomcat", 45);
        return person;
    }
}
