package com.org.asean.javase.boot.thriftclient.util;

import com.org.asean.javase.boot.thriftclient.config.ThriftRpcClientConfig;
import com.xman.common.rpc.client.thrift.ClientProxy;
import com.xman.common.rpc.client.thrift.ThriftClient;
import com.xman.common.rpc.protocol.trace.TTraceProtocol;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ThriftRpcClient {
    private final static Logger logger = LoggerFactory
            .getLogger(ThriftRpcClient.class);

    private String thriftClientConfFile;

    private ThriftClient thriftClient;

    public ThriftRpcClient(String thriftClientConfFile)
    {
        this.thriftClientConfFile = thriftClientConfFile;
    }

    private ThriftRpcClient()
    {}

    private String serviceClientName;
    private List<ThriftRpcClientConfig> serviceConfigList;

    public void startup() {
        try {
            this.thriftClient = new ThriftClient(thriftClientConfFile);
            for (ThriftRpcClientConfig config : this.serviceConfigList) {
                ClientProxy clientProxy = new ClientProxy(Class.forName(config
                        .getServiceClazz()), config.isMultiFlag()).setRegister(config
                        .getRegisteFlag());
                if (config.getTframedFlag()) {
                    clientProxy.setTransportFactory(new TFramedTransport.Factory());
                    clientProxy.setProtocolFactory(new TTraceProtocol.Factory(new TBinaryProtocol.Factory()));

                }
                if (!config.getRegisteFlag()) {
                    clientProxy.setUpstreamAddrs(config.getServerAddr());
                }
                this.thriftClient.add(clientProxy);
            }

            this.thriftClient.init();

            logger.debug("thrift client " + this.serviceClientName
                    + " startup success.");
        } catch (Exception e) {
            logger.error("thrift client " + this.serviceClientName
                    + " startup error.", e);
        }

    }

    public void shutdown() {
        if (this.thriftClient != null) {
            try {
                this.thriftClient.stop();

                logger.debug("thrift client " + this.serviceClientName
                        + " shutdown success.");
            } catch (Exception e) {
                logger.error("thrift client " + this.serviceClientName
                        + " shutdown error.", e);
            }
        }
    }

    public TServiceClient getServiceClient(Class<?> ifaceClass) {
        return this.thriftClient.getClient(ifaceClass);
    }

    public void setServiceClientName(String serviceClientName) {
        this.serviceClientName = serviceClientName;
    }

    public void setServiceConfigList(
            List<ThriftRpcClientConfig> serviceConfigList) {
        this.serviceConfigList = serviceConfigList;
    }

}
