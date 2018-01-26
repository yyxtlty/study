package com.org.asean.javase.boot.thriftregist.util;

import com.xman.common.rpc.service.thrift.ServiceProxy;
import com.xman.common.rpc.service.thrift.ThriftService;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ThriftRpcService {

    private final static Logger logger = LoggerFactory
            .getLogger(ThriftRpcService.class);

    private ThriftService thriftService;
    private TTransportFactory transportFactory;
    private TProtocolFactory protocolFactory;
    private String serverType;
    private String thriftServiceName;

    public List<ThriftRpcServiceConfig> serviceConfigList;


    public ThriftRpcService(TTransportFactory transportFactory, TProtocolFactory protocolFactory, String serverType) {
        this.transportFactory = transportFactory;
        this.protocolFactory = protocolFactory;
        this.serverType = serverType;
    }

    public static final int PORT = 9999;

   /* public void startup() {
        try {
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();

            TProcessor processor = new PersonService.Processor<>(new PersonServiceImpl());

            //   TServer server = new TThreadPoolServer(processor,serverSocket,factory);

            //TServerSocket serverSocket = new TServerSocket(9999);
            TServerSocket serverSocket = new TServerSocket(PORT);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket);
            args.inputProtocolFactory(factory);
            args.processor(processor);
            TServer server = new TThreadPoolServer(args);

            //设置一个监听器
            server.setServerEventHandler(new MyTServerEventHandler());
            server.serve();

       *//*     TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(9090);
            TThreadedSelectorServer.Args args1 = new TThreadedSelectorServer.Args(tNonblockingServerSocket);
            args1.inputProtocolFactory(factory);
            args1.processor(processor);
            TThreadedSelectorServer server1 = new TThreadedSelectorServer(args1);
            server1.serve();*//*


        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }*/

    public void startup(){
        try {
            this.thriftService = new ThriftService(transportFactory,protocolFactory,serverType);
            for (ThriftRpcServiceConfig config : this.serviceConfigList) {
                ServiceProxy serviceProxy = new ServiceProxy(Class.forName(config.getServiceClazz()),
                        config.getServiceImpl(),config.getRegisteFlag());

                this.thriftService.add(serviceProxy);
            }

           // Thread serviceThread = new Thread(new ThriftServiceThread(this.thriftService));

            Thread serviceThread= new Thread(new Runnable() {
                @Override
                public void run() {
                    ThriftRpcService.this.thriftService.init();
                }
            });
            serviceThread.start();

            System.out.println("start success!!!");
            logger.info("thrift service " + this.thriftServiceName
                    + " startup success.");
        } catch (Exception e) {
            logger.error("thrift service " + "Hello World"
                    + " startup error.", e);
        }
    }

    public void shutdown() {

    }

    public void setThriftServiceName(String thriftServiceName) {
        this.thriftServiceName = thriftServiceName;
    }

    public void setServiceConfigList(
            List<ThriftRpcServiceConfig> serviceConfigList) {
        this.serviceConfigList = serviceConfigList;
    }

}


