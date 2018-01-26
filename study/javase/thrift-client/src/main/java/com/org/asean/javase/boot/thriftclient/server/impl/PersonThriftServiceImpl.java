package com.org.asean.javase.boot.thriftclient.server.impl;

import com.org.asean.javase.boot.thriftclient.server.PersonThriftServer;
import com.org.asean.javase.boot.thriftclient.util.ThriftRpcClient;
import com.org.asean.javase.server.Person;
import com.org.asean.javase.server.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonThriftServiceImpl implements PersonThriftServer {

    @Resource(name = "thriftRpcClient")
    private ThriftRpcClient managerThriftRpcClient;

    private PersonService.Client personThriftRpcClient;


    //private TSerializer serializer = new TSerializer(new TSimpleJSONProtocol.Factory());


    private PersonService.Client getPersonClient() {
        if (personThriftRpcClient == null) {
            personThriftRpcClient = (PersonService.Client) managerThriftRpcClient
                    .getServiceClient(PersonService.class);
        }
        return personThriftRpcClient;
    }

    @Override
    public Person getPerson(){

        Person person = null;

        try {
            person = getPersonClient().getPerson();
        } catch (TException e) {
            e.printStackTrace();
        }

        return person;
    }
}
