package com.org.asean.javase.boot.thriftclient.config;

import com.org.asean.javase.boot.thriftclient.util.ThriftRpcClient;
import com.org.asean.javase.server.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ThriftClientConfig {
    @Value("${thrift.client.name}")
    String thriftClientNamme;

    @Value("${thrift.client.upstreamAddr}")
    String serviceUpstreamAddr;

    @Value("${thrift.client.configfile}")
    private String thriftClientConfFile;

    @Value("${thrift.client.registerFlag}")
    private String registerFlag;

    @Value("${thrift.client.isMulti}")
    private String isMulti;

    @Value("${thrift.client.nonBlockFlag}")
    private String nonBlockFlag;

    @Bean(name = "thriftRpcClient",initMethod = "startup", destroyMethod = "shutdown")
    public ThriftRpcClient getThriftRpcClient() {
        ThriftRpcClient thriftRpcClient = new ThriftRpcClient(thriftClientConfFile);
        thriftRpcClient.setServiceClientName(thriftClientNamme);

        List<ThriftRpcClientConfig> serviceConfigs = new ArrayList<ThriftRpcClientConfig>();

        //使用其它client时继续添加

        ThriftRpcClientConfig personServiceConfig = new ThriftRpcClientConfig();
        personServiceConfig.setServiceClazz(PersonService.class.getName());
        personServiceConfig.setRegisteFlag(Boolean.valueOf(registerFlag));
        personServiceConfig.setTframedFlag(Boolean.valueOf(nonBlockFlag));
        personServiceConfig.setMultiFlag(Boolean.valueOf(isMulti));
        personServiceConfig.setServerAddr(serviceUpstreamAddr);
        serviceConfigs.add(personServiceConfig);




        thriftRpcClient.setServiceConfigList(serviceConfigs);
        return thriftRpcClient;
    }

}
