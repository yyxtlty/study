package com.org.asean.javase.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThriftSerial {

    @Test
    public void test(){

        Person person = new Person(1,"Tom",23);


      //  ExecutorService executorService = Executors.newCachedThreadPool();
        try( ServerSocket serverSocket = new ServerSocket(9999);

             Socket socket = serverSocket.accept();
             OutputStream outputStream = socket.getOutputStream();
             TTransport tTransport = new TIOStreamTransport(outputStream)){
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
            TBinaryProtocol tp = new TBinaryProtocol(tTransport);

            try {
                person.write(tp);
            } catch (Exception e) {
                e.printStackTrace();
            }
          /*  byte[] buf = out.toByteArray();
            System.out.println(buf.length);
            for(byte b :buf){
                System.out.print(b + "\t");
            }*/

        }catch (Exception e){

        }
    }
    
    
    @Test
    public void service(){

        try {


           TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();

           TProcessor processor = new PersonService.Processor<>(new PersonServiceImpl());

         //   TServer server = new TThreadPoolServer(processor,serverSocket,factory);

            TServerSocket serverSocket = new TServerSocket(9999);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket);
            args.inputProtocolFactory(factory);
            args.processor(processor);
            TServer server = new TThreadPoolServer(args);
            server.serve();

            /*TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(9090);
            TThreadedSelectorServer.Args args1 = new TThreadedSelectorServer.Args(tNonblockingServerSocket);
            args1.inputProtocolFactory(factory);
            args1.processor(processor);
            TThreadedSelectorServer server1 = new TThreadedSelectorServer(args1);
            server1.serve();*/

            //ExecutorService executorService = Executors.newCachedThreadPool();


        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }
}
