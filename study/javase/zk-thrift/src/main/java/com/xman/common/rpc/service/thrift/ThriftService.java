//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc.service.thrift;

import com.xman.common.log.ILog;
import com.xman.common.log.LogFactory;
import com.xman.common.rpc.ServiceException;
import com.xman.common.rpc.ThriftConfig;
import com.xman.common.rpc.protocol.trace.TTraceProcessor;
import com.xman.common.rpc.registry.Registry;
import com.xman.common.rpc.registry.support.RegistryContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportFactory;

public class ThriftService {
    private static final ILog logger = LogFactory.getLog(ThriftService.class);
    public static final String THRIFT_THREAD_POOL = "threadpool";
    public static final String THRIFT_THREAD_THREADSELECTOR = "threadselector";
    private AtomicBoolean inited;
    private AtomicBoolean stoped;
    private Registry registry;
    private ThriftConfig config;
    TServer server;
    private TTransportFactory transportFactory;
    private TProtocolFactory protocolFactory;
    private String serverType;
    private boolean isMulti;
    private List<ServiceProxy> services;

    public ThriftService() {
        this(new TTransportFactory(), new Factory(), "threadselector");
    }

    public ThriftService(String serverType) {
        this(new TTransportFactory(), new Factory(), serverType);
    }

    public ThriftService(TTransportFactory transportFactory, String serverType) {
        this(transportFactory, new Factory(), serverType);
    }

    public ThriftService(TProtocolFactory protocolFactory, String serverType) {
        this(new TTransportFactory(), protocolFactory, serverType);
    }

    public ThriftService(TTransportFactory transportFactory, TProtocolFactory protocolFactory, String serverType) {
        this.inited = new AtomicBoolean();
        this.stoped = new AtomicBoolean();
        this.serverType = "threadselector";
        this.isMulti = true;
        this.transportFactory = transportFactory;
        this.protocolFactory = protocolFactory;
        this.serverType = serverType;
        this.services = new ArrayList();
        this.config = new ThriftConfig();
    }

    public void add(ServiceProxy service) {
        this.services.add(service);
    }

    public void init() {
        if (this.inited.compareAndSet(false, true)) {
            if (!this.isMulti && this.services.size() > 1) {
                throw new IllegalStateException("not multi processor and services num > 0");
            } else {
                boolean register = false;

                for(int i = 0; i < this.services.size(); ++i) {
                    ((ServiceProxy)this.services.get(i)).init(this.config);
                    register = register || ((ServiceProxy)this.services.get(i)).isRegister();
                }

                if (register) {
                    this.registry = RegistryContainer.getRegistry(this.config.regcfg);
                    if (this.registry == null) {
                        throw new IllegalStateException("no registry config found");
                    }
                }

                logger.info("init server use " + this.serverType + " service model");
                if (this.protocolFactory == null) {
                    logger.info("user default binary protocol");
                    this.protocolFactory = new Factory();
                }

                if (this.transportFactory == null) {
                    logger.info("user default transport factory");
                    this.transportFactory = new TTransportFactory();
                }

                try {
                    if (this.serverType.equals("threadselector")) {
                        this.server = this.initTThreadedSelectorServer();
                    } else {
                        if (!this.serverType.equals("threadpool")) {
                            throw new ServiceException("unsupported server type");
                        }

                        this.server = this.initThreadPoolServer();
                    }
                } catch (Exception var7) {
                    if (this.registry != null) {
                        this.registry.destroy();
                    }

                    throw new ServiceException("init failure", var7);
                }

                if (this.registry != null) {
                    this.server.setServerEventHandler(new ThriftService.AbstractTServerEventHandler() {
                        public void preServe() {
                            if (ThriftService.this.config.rigistryDelay > 0) {
                                try {
                                    Thread.sleep((long)ThriftService.this.config.rigistryDelay);
                                } catch (Exception var2) {
                                    ThriftService.logger.error("sleep intercept", var2);
                                }
                            }

                            for(int i = 0; i < ThriftService.this.services.size(); ++i) {
                                if (((ServiceProxy)ThriftService.this.services.get(i)).isRegister()) {
                                    ThriftService.this.registry.register(((ServiceProxy)ThriftService.this.services.get(i)).getService());
                                }
                            }

                        }
                    });
                    Runtime.getRuntime().addShutdownHook(new Thread() {
                        public void run() {
                            RegistryContainer.destroyAll();
                        }
                    });
                }

                try {
                    this.server.serve();
                } finally {
                    this.stop();
                }

            }
        }
    }

    public void stop() {
        if (!this.inited.get()) {
            logger.warn("service not start, so stop do nothing");
        } else if (this.stoped.compareAndSet(false, true)) {
            if (this.registry != null && this.registry.isAvailable()) {
                for(int i = 0; i < this.services.size(); ++i) {
                    this.registry.unregister(((ServiceProxy)this.services.get(i)).getService());
                }

                this.registry = null;
            }

            RegistryContainer.destroyAll();
            if (this.server.isServing()) {
                this.server.stop();
            }

        }
    }

    private TServer initTThreadedSelectorServer() throws Exception {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(this.config.port);
        Args args = new Args(serverSocket);
        TProcessor _processor = null;
        if (this.isMulti) {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();

            for(int i = 0; i < this.services.size(); ++i) {
                ServiceProxy service = (ServiceProxy)this.services.get(i);
                processor.registerProcessor(service.getIfaceName(), (TProcessor)service.getPconstructor().newInstance(service.getProxy()));
            }

            _processor = processor;
        } else {
            ServiceProxy service = (ServiceProxy)this.services.get(0);
            _processor = (TProcessor)service.getPconstructor().newInstance(service.getProxy());
        }

        logger.info("user trace processor");
        TProcessor _processor_ = new TTraceProcessor((TProcessor)_processor);
        args.processor(_processor_);
        args.protocolFactory(this.protocolFactory);
        args.transportFactory(new org.apache.thrift.transport.TFramedTransport.Factory(this.config.tframeMaxLength));
        args.selectorThreads(this.config.selectorThreads);
        args.workerThreads(this.config.workerThreads);
        args.acceptQueueSizePerThread(this.config.acceptQueueSizePerThread);
        return new TThreadedSelectorServer(args);
    }

    private TServer initThreadPoolServer() throws Exception {
        org.apache.thrift.server.TThreadPoolServer.Args args = new org.apache.thrift.server.TThreadPoolServer.Args(new TServerSocket(this.config.port));
        TProcessor _processor = null;
        if (this.isMulti) {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();

            for(int i = 0; i < this.services.size(); ++i) {
                ServiceProxy service = (ServiceProxy)this.services.get(i);
                processor.registerProcessor(service.getIfaceName(), (TProcessor)service.getPconstructor().newInstance(service.getProxy()));
            }

            _processor = processor;
        } else {
            ServiceProxy service = (ServiceProxy)this.services.get(0);
            _processor = (TProcessor)service.getPconstructor().newInstance(service.getProxy());
        }

        logger.info("user trace processor");
        TProcessor _processor_ = new TTraceProcessor((TProcessor)_processor);
        args.processor(_processor_);
        args.protocolFactory(this.protocolFactory);
        args.transportFactory(this.transportFactory);
        args.minWorkerThreads(this.config.minWorkerThreads);
        args.maxWorkerThreads(this.config.maxWorkerThreads);
        return new TThreadPoolServer(args);
    }

    public TTransportFactory getTransportFactory() {
        return this.transportFactory;
    }

    public void setTransportFactory(TTransportFactory transportFactory) {
        this.transportFactory = transportFactory;
    }

    public TProtocolFactory getProtocolFactory() {
        return this.protocolFactory;
    }

    public void setProtocolFactory(TProtocolFactory protocolFactory) {
        this.protocolFactory = protocolFactory;
    }

    public String getServerType() {
        return this.serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public boolean isMulti() {
        return this.isMulti;
    }

    public void setMulti(boolean isMulti) {
        this.isMulti = isMulti;
    }

    public List<ServiceProxy> getServices() {
        return this.services;
    }

    public void setServices(List<ServiceProxy> services) {
        this.services = services;
    }

    private abstract class AbstractTServerEventHandler implements TServerEventHandler {
        private AbstractTServerEventHandler() {
        }

        public ServerContext createContext(TProtocol arg0, TProtocol arg1) {
            return null;
        }

        public void deleteContext(ServerContext arg0, TProtocol arg1, TProtocol arg2) {
        }

        public abstract void preServe();

        public void processContext(ServerContext arg0, TTransport arg1, TTransport arg2) {
        }
    }
}
