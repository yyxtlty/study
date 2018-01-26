//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc.registry.zk;

import com.xman.common.rpc.Service;
import com.xman.common.rpc.registry.NotifyListener;
import com.xman.common.rpc.registry.support.AbstractRegistry;
import com.xman.common.rpc.registry.zk.client.ChildListener;
import com.xman.common.rpc.registry.zk.client.StateListener;
import com.xman.common.rpc.registry.zk.client.impl.CuratorZookeeperClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class CuratorZookeeperRegistry extends AbstractRegistry {
    private final ConcurrentMap<String, ConcurrentMap<NotifyListener, ChildListener>> zkListeners = new ConcurrentHashMap();
    private AtomicBoolean inited = new AtomicBoolean();
    private AtomicBoolean destroyed = new AtomicBoolean();
    private CuratorZookeeperClient zkClient;
    private String root = "thrift";
    private boolean ephemeral = true;
    private int timeout = 2000;
    private int sessionTimeout = 5000;

    public CuratorZookeeperRegistry() {
    }

    public void init() {
        if (this.getHosts() == null) {
            throw new IllegalStateException("registry address == null");
        } else if (this.inited.compareAndSet(false, true)) {
            this.logger.debug("init registry");
            String group = this.getRoot();
            if (!group.startsWith("/")) {
                group = "/" + group;
            }

            this.root = group;
            this.zkClient = new CuratorZookeeperClient(this.getHosts(), this.getSessionTimeout(), this.getTimeout());
            this.zkClient.addStateListener(new StateListener() {
                private boolean firstConnected = true;

                public void stateChanged(int state) {
                    CuratorZookeeperRegistry.this.logger.debug("zk state change " + state);
                    if (state == 1) {
                        CuratorZookeeperRegistry.this.logger.info("state change to connected " + this.firstConnected);
                    }

                    if (state == 2) {
                        try {
                            CuratorZookeeperRegistry.this.recover();
                        } catch (Exception var3) {
                            CuratorZookeeperRegistry.this.logger.error(var3.getMessage(), var3);
                        }
                    }

                }
            });
        }
    }

    public boolean isAvailable() {
        return this.zkClient.isConnected();
    }

    public void destroy() {
        if (this.destroyed.compareAndSet(false, true)) {
            super.destroy();

            try {
                this.zkClient.close();
            } catch (Exception var2) {
                this.logger.warn("Failed to close zookeeper client " + this.getHosts() + ", cause: " + var2.getMessage(), var2);
            }

        }
    }

    public void register(Service service) {
        super.register(service);

        try {
            this.zkClient.create(this.toUrlPath(service), this.isEphemeral());
        } catch (Throwable var3) {
            this.logger.error("Failed to register " + service + " to zookeeper " + this.getHosts() + ", cause: " + var3.getMessage(), var3);
        }

    }

    public void unregister(Service service) {
        super.unregister(service);

        try {
            this.zkClient.delete(this.toUrlPath(service));
        } catch (Throwable var3) {
            this.logger.error("Failed to unregister " + service + " to zookeeper " + this.getHosts() + ", cause: " + var3.getMessage(), var3);
        }

    }

    public void subscribe(final String service, final NotifyListener listener) {
        super.subscribe(service, listener);

        try {
            List<Service> services = new ArrayList();
            String path = this.toSubscribePath(service);
            ConcurrentMap<NotifyListener, ChildListener> listeners = (ConcurrentMap)this.zkListeners.get(service);
            if (listeners == null) {
                this.zkListeners.putIfAbsent(service, new ConcurrentHashMap());
                listeners = (ConcurrentMap)this.zkListeners.get(service);
            }

            ChildListener zkListener = (ChildListener)listeners.get(listener);
            if (zkListener == null) {
                listeners.putIfAbsent(listener, new ChildListener() {
                    public void childChanged(String parentPath, List<String> currentChilds) {
                        if (CuratorZookeeperRegistry.this.logger.isDebugEnabled()) {
                            CuratorZookeeperRegistry.this.logger.debug("path is updated: " + parentPath + "," + currentChilds);
                        }

                        CuratorZookeeperRegistry.this.notify(service, listener, CuratorZookeeperRegistry.this.toUrlsWithoutEmpty(service, currentChilds));
                    }
                });
                zkListener = (ChildListener)listeners.get(listener);
            }

            this.zkClient.create(path, false);
            List<String> children = this.zkClient.addChildListener(path, zkListener);
            if (children != null) {
                services.addAll(this.toUrlsWithoutEmpty(service, children));
            }

            this.notify(service, listener, services);
        } catch (Throwable var8) {
            this.logger.error("Failed to subscribe " + service + " to zookeeper " + this.getHosts() + ", cause: " + var8.getMessage(), var8);
        }

    }

    public void unsubscribe(String service, NotifyListener listener) {
        super.unsubscribe(service, listener);

        try {
            ConcurrentMap<NotifyListener, ChildListener> listeners = (ConcurrentMap)this.zkListeners.get(service);
            if (listeners != null) {
                ChildListener zkListener = (ChildListener)listeners.get(listener);
                if (zkListener != null) {
                    this.zkClient.removeChildListener(this.toSubscribePath(service), zkListener);
                }
            }
        } catch (Throwable var5) {
            this.logger.error("Failed to unsubscribe " + service + " to zookeeper " + this.getHosts() + ", cause: " + var5.getMessage(), var5);
        }

    }

    private String toRootDir() {
        return this.root.equals("/") ? this.root : this.root + "/";
    }

    private String toSubscribePath(String service) {
        return this.toRootDir() + service + "/" + "providers";
    }

    private String toServicePath(Service service) {
        return this.toRootDir() + Service.encode(service.name);
    }

    private String toCategoryPath(Service service) {
        return this.toServicePath(service) + "/" + service.category;
    }

    private String toUrlPath(Service service) {
        return this.toCategoryPath(service) + "/" + Service.encode(service.toString());
    }

    private List<Service> toUrlsWithoutEmpty(String service, List<String> providers) {
        List<Service> services = new ArrayList();
        if (providers != null && providers.size() > 0) {
            Iterator i$ = providers.iterator();

            while(i$.hasNext()) {
                String provider = (String)i$.next();
                provider = Service.decode(provider);
                Service s = Service.valueOf(provider);
                if (s != null) {
                    services.add(s);
                } else {
                    this.logger.error("invalid provider: " + provider);
                }
            }
        }

        return services;
    }

    public String getRoot() {
        return this.root;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getSessionTimeout() {
        return this.sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public boolean isEphemeral() {
        return this.ephemeral;
    }
}
