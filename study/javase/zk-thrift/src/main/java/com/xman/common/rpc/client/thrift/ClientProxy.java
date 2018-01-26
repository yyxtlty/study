//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc.client.thrift;

import com.xman.common.log.ILog;
import com.xman.common.log.LogFactory;
import com.xman.common.rpc.FilterProvider;
import com.xman.common.rpc.RpcException;
import com.xman.common.rpc.Service;
import com.xman.common.rpc.ThriftConfig;
import com.xman.common.rpc.filter.Filter;
import com.xman.common.rpc.invoke.Invocation;
import com.xman.common.rpc.invoke.Invoker;
import com.xman.common.rpc.invoke.RpcInvocation;
import com.xman.common.rpc.lb.LoadBalance;
import com.xman.common.rpc.lb.impl.RRLoadBalance;
import com.xman.common.rpc.pool.ConnWrapObject;
import com.xman.common.rpc.registry.NotifyListener;
import com.xman.common.rpc.registry.Registry;
import com.xman.common.rpc.registry.sstatic.StaticRegistry;
import com.xman.common.rpc.util.JsonUtil;
import com.xman.common.rpc.util.NetUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

public class ClientProxy implements MethodInterceptor {
    private static final ILog logger = LogFactory.getLog(ClientProxy.class);
    private AtomicBoolean inited;
    private Service service;
    private AtomicReference<List<Service>> servicesRef;
    private NotifyListener listener;
    private ThriftTSocketPool pool;
    private String ifaceName;
    private Class<?> objectClass;
    private LoadBalance loadbalance;
    private Registry staticRegistry;
    private ThriftConfig config;
    private TServiceClientFactory<TServiceClient> clientFactory;
    private List<Filter> filterChain;
    private boolean isMulti;
    private Class<?> ifaceClass;
    private boolean register;
    private String upstreamAddrs;
    private TTransportFactory transportFactory;
    private TProtocolFactory protocolFactory;
    private String lbType;
    private ClassLoader classLoader;
    private int retries;
    private int socketTimeout;
    private boolean noKeepalive;

    public ClientProxy(Class<?> ifaceClass) {
        this(ifaceClass, true);
    }

    public ClientProxy(Class<?> ifaceClass, boolean isMulti) {
        this.inited = new AtomicBoolean();
        this.servicesRef = new AtomicReference();
        this.lbType = "roundrobin";
        this.retries = -1;
        this.socketTimeout = -1;
        this.noKeepalive = false;
        this.isMulti = isMulti;
        this.ifaceClass = ifaceClass;
        this.ifaceName = ifaceClass.getName();
    }

    void init(ThriftConfig config) {
        if (this.ifaceClass == null) {
            throw new IllegalArgumentException("ifaceClass == null");
        } else if (this.inited.compareAndSet(false, true)) {
            logger.info("init " + this.ifaceName + " client proxy");
            this.config = config;
            if (!this.register) {
                if (this.upstreamAddrs == null) {
                    throw new IllegalArgumentException("register is false but no upstream addrs config");
                }

                this.staticRegistry = new StaticRegistry(this.upstreamAddrs, config.heartbeatPeriod);
            }

            if (!"roundrobin".equals(this.lbType)) {
                throw new IllegalStateException("unspport loadbalance type");
            } else {
                this.loadbalance = new RRLoadBalance();
                if (this.classLoader == null) {
                    this.classLoader = ClientProxy.class.getClassLoader();
                }

                try {
                    this.objectClass = this.classLoader.loadClass(this.ifaceName + "$Client");
                    Class<?> fi = this.classLoader.loadClass(this.ifaceName + "$Client$Factory");
                    this.clientFactory = (TServiceClientFactory)fi.newInstance();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException var3) {
                    if (this.staticRegistry != null) {
                        this.staticRegistry.destroy();
                    }

                    throw new RpcException("init client failure", var3);
                }

                if (this.protocolFactory == null) {
                    this.protocolFactory = new Factory();
                }

                if (this.transportFactory == null) {
                    this.transportFactory = new TTransportFactory();
                }

                this.service = new Service(NetUtil.getLocalHost(), config.port, this.ifaceName, "consumers");
                this.listener = new NotifyListener() {
                    public void notify(List<Service> news) {
                        List<Service> olds = (List)ClientProxy.this.servicesRef.get();
                        ClientProxy.logger.info("receive service update notify, old:{}, new:{}" + JsonUtil.toJson(olds) + "," + JsonUtil.toJson(news));
                        if (olds != null) {
                            for(int i = 0; i < olds.size(); ++i) {
                                Service old = (Service)olds.get(i);
                                ClientProxy.logger.info("clear pool start {}" + System.currentTimeMillis());
                                ClientProxy.this.pool.clear(old.getKey());
                                ClientProxy.logger.info("clear pool end {}" + System.currentTimeMillis());
                            }
                        }

                        ClientProxy.this.servicesRef.set(news);
                    }
                };
                if (this.staticRegistry != null) {
                    this.staticRegistry.subscribe(this.ifaceName, this.listener);
                }

                this.pool = new ThriftTSocketPool(config.poolConfig);
                this.filterChain = FilterProvider.configConsumerFilters(config);
            }
        }
    }

    void stop() {
        if (this.staticRegistry != null) {
            this.staticRegistry.unsubscribe(this.ifaceName, this.listener);
            this.staticRegistry.destroy();
            this.staticRegistry = null;
        }

        this.pool.close();
    }

    TServiceClient getClient() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.objectClass);
        enhancer.setCallback(this);
        Class<?>[] classArr = new Class[]{TProtocol.class};
        Object[] objArr = new Object[]{this.protocolFactory.getProtocol((TTransport)null)};
        return (TServiceClient)enhancer.create(classArr, objArr);
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        int socketTimeout;
        if (method.getDeclaringClass() == Object.class) {
            Constructor<?> constructor = this.objectClass.getConstructor(TProtocol.class);
            Object myClassReflect = constructor.newInstance(new TBinaryProtocol((TTransport)null));
            Class[] ctype = new Class[args.length];

            for(socketTimeout = 0; socketTimeout < args.length; ++socketTimeout) {
                ctype[socketTimeout] = args[socketTimeout].getClass();
            }

            Method method1 = this.objectClass.getMethod(method.getName(), ctype);
            return method1.invoke(myClassReflect, args);
        } else {
            List<Service> services = (List)this.servicesRef.get();
            if (services != null && services.size() != 0) {
                int timeout = 0;
                int retries = this.retries;
                if (retries == -1) {
                    retries = this.config.retries;
                }

                socketTimeout = this.socketTimeout;
                if (socketTimeout == -1) {
                    socketTimeout = this.config.socketTimeout;
                }

                for(int i = 0; i < retries + 1; ++i) {
                    Service service = this.loadbalance.select(services);
                    String key = service.getKey();
                    ConnWrapObject<TSocket> t = null;
                    TSocket socket = null;
                    boolean broken = false;
                    boolean clear = false;

                    try {
                        Throwable c;
                        try {
                            if (this.noKeepalive) {
                                socket = new TSocket(service.host, service.port);
                                socket.open();
                            } else {
                                t = this.pool.getResource(key);
                                socket = (TSocket)t.getObject();
                            }

                            socket.setTimeout(socketTimeout);
                            TTransport transport = this.transportFactory.getTransport(socket);
                            Object protocol;
                            if (this.isMulti) {
                                protocol = new TMultiplexedProtocol(this.protocolFactory.getProtocol(transport), this.ifaceName);
                            } else {
                                protocol = this.protocolFactory.getProtocol(transport);
                            }

                            Object var18 = this.invoke(this.clientFactory.getClient((TProtocol)protocol), args, proxy);
                            return var18;
                        } catch (TTransportException var26) {
                            logger.error(" invoke " + service.toString() + " [" + method.getName() + "] failure: " + ExceptionMsg.TTRAS[var26.getType()] + "," + var26.getMessage(), var26);
                            broken = true;
                            c = var26.getCause();
                            if (c == null || !(c instanceof ConnectException) && !"Broken pipe".equals(c.getMessage())) {
                                throw var26;
                            }

                            clear = true;
                        } catch (TApplicationException var27) {
                            if (var27.getType() != 5) {
                                logger.error("invoke " + service.toString() + " [" + method.getName() + "] got exception: " + ExceptionMsg.TAPP[var27.getType()] + "," + var27.getMessage(), var27);
                                throw var27;
                            }

                            c = null;
                            return c;
                        } catch (TProtocolException var28) {
                            logger.error("invoke " + service.toString() + " [" + method.getName() + "] failure: " + ExceptionMsg.TPRO[var28.getType()] + "," + var28.getMessage(), var28);
                            throw var28;
                        } catch (TException var29) {
                            logger.error("invoke " + service.toString() + " [" + method.getName() + "] failure: " + var29.getMessage(), var29);
                            throw var29;
                        } catch (Throwable var30) {
                            broken = true;
                            logger.error("invoke " + service.toString() + " [" + method.getName() + "] failure,unknown exception: " + var30.getMessage(), var30);
                            throw var30;
                        }
                    } finally {
                        if (this.noKeepalive) {
                            socket.close();
                        } else if (t != null) {
                            if (clear) {
                                this.pool.clear(key);
                            } else if (!broken) {
                                this.pool.returnResource(key, t);
                            } else {
                                this.pool.returnBrokenResource(key, t);
                            }
                        }

                    }
                }

                if (timeout == retries + 1) {
                    throw new RpcException("invoke " + method.getName() + " failed, timedout");
                } else {
                    throw new RpcException("invoke " + method.getName() + " failed");
                }
            } else {
                throw new RpcException("no live service found");
            }
        }
    }

    private Object invoke(Object target, Object[] args, final MethodProxy proxy) throws Throwable {
        logger.debug("invoker consumer filter chain:{}" + this.filterChain);
        Invocation invocation = RpcInvocation.getRpcInvocation(target, args).setMethodName(proxy.getSignature().getName()).setIfaceClass(this.ifaceClass);
        Invoker finalInvoker = new Invoker() {
            public Object invoke(Invocation invocation) throws Throwable {
                return proxy.invoke(invocation.getImpl(), invocation.getArguments());
            }
        };
        Invoker invokerWrapper = FilterProvider.buildInvokerChain(this.filterChain, finalInvoker);
        return invokerWrapper.invoke(invocation);
    }

    public Class<?> getIfaceClass() {
        return this.ifaceClass;
    }

    public boolean isMulti() {
        return this.isMulti;
    }

    public ClientProxy setMulti(boolean isMulti) {
        this.isMulti = isMulti;
        return this;
    }

    public ClientProxy setIfaceClass(Class<?> ifaceClass) {
        this.ifaceClass = ifaceClass;
        this.ifaceName = ifaceClass.getName();
        return this;
    }

    public TTransportFactory getTransportFactory() {
        return this.transportFactory;
    }

    public ClientProxy setTransportFactory(TTransportFactory transportFactory) {
        this.transportFactory = transportFactory;
        return this;
    }

    public TProtocolFactory getProtocolFactory() {
        return this.protocolFactory;
    }

    public ClientProxy setProtocolFactory(TProtocolFactory protocolFactory) {
        this.protocolFactory = protocolFactory;
        return this;
    }

    public String getUpstreamAddrs() {
        return this.upstreamAddrs;
    }

    public ClientProxy setUpstreamAddrs(String upstreamAddrs) {
        this.upstreamAddrs = upstreamAddrs;
        return this;
    }

    public String getLbType() {
        return this.lbType;
    }

    public ClientProxy setLbType(String lbType) {
        this.lbType = lbType;
        return this;
    }

    public boolean isRegister() {
        return this.register;
    }

    public ClientProxy setRegister(boolean register) {
        this.register = register;
        return this;
    }

    public int getRetries() {
        return this.retries;
    }

    public ClientProxy setRetries(int retries) {
        this.retries = retries;
        return this;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public ClientProxy setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public ClientProxy setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        return this;
    }

    public Service getService() {
        return this.service;
    }

    public NotifyListener getListener() {
        return this.listener;
    }

    public String getIfaceName() {
        return this.ifaceName;
    }

    public boolean isNoKeepalive() {
        return this.noKeepalive;
    }

    public ClientProxy setNoKeepalive(boolean noKeepalive) {
        this.noKeepalive = noKeepalive;
        return this;
    }
}
