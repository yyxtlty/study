package com.org.asean.javase.boot.thriftregist.server;

import com.org.asean.javase.server.Person;
import com.org.asean.javase.server.PersonService;
import com.xman.common.service.http.annotation.HttpService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;


@Component("personService")
@HttpService
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPerson() throws TException {
        Person person = new Person(2, "tomcat", 45);
        return person;
    }
}
