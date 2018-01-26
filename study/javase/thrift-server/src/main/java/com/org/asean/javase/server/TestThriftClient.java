package com.org.asean.javase.server;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TestThriftClient {

    @Test
    public void testClient(){

        Person person = new Person(1,"Nginx",34);


        try (Socket socket = new Socket("localhost",9999);
             InputStream inputStream = socket.getInputStream();
             TTransport tTransport = new TIOStreamTransport(inputStream)){
            TBinaryProtocol tBinaryProtocol = new TBinaryProtocol(tTransport);

            person.read(tBinaryProtocol);

            System.out.println(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client(){

      //  try (TTransport transport = new TSocket("172.20.2.220",9999);){
        try (TTransport transport = new TSocket("localhost",9999);){


            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);

            PersonService.Client client = new PersonService.Client(protocol);

            Person person = client.getPerson();
            System.out.println(person.toString());

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

}
