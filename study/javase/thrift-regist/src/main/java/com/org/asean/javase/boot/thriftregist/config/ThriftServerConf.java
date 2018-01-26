package com.org.asean.javase.boot.thriftregist.config;


import com.org.asean.javase.boot.thriftregist.server.PersonServiceImpl;
import com.org.asean.javase.boot.thriftregist.util.ThriftRpcService;
import com.org.asean.javase.boot.thriftregist.util.ThriftRpcServiceConfig;
import com.org.asean.javase.server.PersonService;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TTransportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThriftServerConf {
    @Value("${thrift.service.order.name}")
    String thriftServiceName;

//    @Value("${thrift.service.order.configfile}")
//    private String thriftServiceConfFile;

    @Value("${thrift.service.order.transportFactoryName}")
    private String transportFactoryName;;

    @Value("${thrift.service.order.protocolFactoryName}")
    private String protocolFactoryName;;

    @Value("${thrift.service.order.serverType}")
    private String serverType;

    @Value("${thrift.service.order.registerFlag}")
    private String registerFlag;

    @Autowired
    private PersonServiceImpl personService;

    @Bean(name = "thriftRpcService",initMethod = "startup",destroyMethod = "shutdown")
    public ThriftRpcService getThriftService(){

        ThriftRpcService thriftRpcService = adaptThriftRpcService();
        thriftRpcService.setThriftServiceName(thriftServiceName);
        List<ThriftRpcServiceConfig> serviceConfigs = new ArrayList<ThriftRpcServiceConfig>();
        //有其它thrift service时，继续添加
        // orderManagerService
        ThriftRpcServiceConfig managerServiceConfig = new ThriftRpcServiceConfig();
        managerServiceConfig.setServiceClazz(PersonService.class.getName());
        managerServiceConfig.setServiceImpl(personService);
        managerServiceConfig.setRegisteFlag(Boolean.valueOf(registerFlag));
        serviceConfigs.add(managerServiceConfig);
        thriftRpcService.setServiceConfigList(serviceConfigs);
        return thriftRpcService;
    }

    private ThriftRpcService adaptThriftRpcService() {
        TTransportFactory transportFactory = null;
        TProtocolFactory protocolFactory = null;

        if(StringUtils.isBlank(transportFactoryName) || transportFactoryName.equals("TTransportFactory"))
        {
            transportFactory = new TTransportFactory();
        }

        if(StringUtils.isBlank(protocolFactoryName) || protocolFactoryName.equals("TBinaryProtocol"))
        {
            protocolFactory = new TBinaryProtocol.Factory();
        }

        ThriftRpcService thriftRpcService = new ThriftRpcService(transportFactory,protocolFactory,serverType);
        return thriftRpcService;
    }
}
