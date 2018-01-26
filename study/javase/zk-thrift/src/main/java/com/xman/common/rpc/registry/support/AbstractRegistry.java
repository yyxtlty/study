//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc.registry.support;

import com.xman.common.log.ILog;
import com.xman.common.log.LogFactory;
import com.xman.common.rpc.Service;
import com.xman.common.rpc.common.ConcurrentHashSet;
import com.xman.common.rpc.registry.NotifyListener;
import com.xman.common.rpc.registry.Registry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractRegistry implements Registry {
    protected final ILog logger = LogFactory.getLog(this.getClass());
    private final Set<Service> registered = new ConcurrentHashSet();
    private final ConcurrentMap<String, Set<NotifyListener>> subscribed = new ConcurrentHashMap();
    protected String hosts;

    public AbstractRegistry() {
    }

    public Set<Service> getRegistered() {
        return this.registered;
    }

    public Map<String, Set<NotifyListener>> getSubscribed() {
        return this.subscribed;
    }

    public void register(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("register service == null");
        } else {
            this.logger.info("Register: " + service);
            this.registered.add(service);
        }
    }

    public void unregister(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("unregister service == null");
        } else {
            this.logger.info("Unregister: " + service);
            this.registered.remove(service);
        }
    }

    public void subscribe(String service, NotifyListener listener) {
        if (service == null) {
            throw new IllegalArgumentException("subscribe service == null");
        } else if (listener == null) {
            throw new IllegalArgumentException("subscribe listener == null");
        } else {
            this.logger.info("Subscribe: " + service);
            Set<NotifyListener> listeners = (Set)this.subscribed.get(service);
            if (listeners == null) {
                this.subscribed.putIfAbsent(service, new ConcurrentHashSet());
                listeners = (Set)this.subscribed.get(service);
            }

            listeners.add(listener);
        }
    }

    public void unsubscribe(String service, NotifyListener listener) {
        if (service == null) {
            throw new IllegalArgumentException("unsubscribe service == null");
        } else if (listener == null) {
            throw new IllegalArgumentException("unsubscribe listener == null");
        } else {
            this.logger.info("Unsubscribe: " + service);
            Set<NotifyListener> listeners = (Set)this.subscribed.get(service);
            if (listeners != null) {
                listeners.remove(listener);
            }

        }
    }

    protected void recover() throws Exception {
        Set<Service> recoverRegistered = new HashSet(this.getRegistered());
        if (!recoverRegistered.isEmpty()) {
            this.logger.info("Recover register service " + recoverRegistered);
            Iterator i$ = recoverRegistered.iterator();

            while(i$.hasNext()) {
                Service service = (Service)i$.next();
                this.register(service);
            }
        }

        Map<String, Set<NotifyListener>> recoverSubscribed = new HashMap(this.getSubscribed());
        if (!recoverSubscribed.isEmpty()) {
            this.logger.info("Recover subscribe service " + recoverSubscribed.keySet());
            Iterator i$ = recoverSubscribed.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<String, Set<NotifyListener>> entry = (Entry)i$.next();
                String service = (String)entry.getKey();
                Iterator ii$ = ((Set)entry.getValue()).iterator();

                while(ii$.hasNext()) {
                    NotifyListener listener = (NotifyListener)i$.next();
                    this.subscribe(service, listener);
                }
            }
        }

    }

    protected void notify(List<Service> services) {
        if (services != null && !services.isEmpty()) {
            Iterator i$ = this.getSubscribed().entrySet().iterator();

            while(true) {
                String service;
                Set listeners;
                do {
                    if (!i$.hasNext()) {
                        return;
                    }

                    Entry<String, Set<NotifyListener>> entry = (Entry)i$.next();
                    service = (String)entry.getKey();
                    listeners = (Set)entry.getValue();
                } while(listeners == null);

                Iterator ii$ = listeners.iterator();

                while(ii$.hasNext()) {
                    NotifyListener listener = (NotifyListener)i$.next();

                    try {
                        this.notify(service, listener, services);
                    } catch (Throwable var9) {
                        this.logger.error("Failed to notify registry event, services: " + services + ", cause: " + var9.getMessage(), var9);
                    }
                }
            }
        }
    }

    protected void notify(String service, NotifyListener listener, List<Service> services) {
        if (service == null) {
            throw new IllegalArgumentException("notify service == null");
        } else if (listener == null) {
            throw new IllegalArgumentException("notify listener == null");
        } else if (services != null && services.size() != 0) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Notify services for subscribe service " + service + ", services: " + services);
            }

            listener.notify(services);
        } else {
            this.logger.warn("Ignore empty notify services for subscribe service " + service);
        }
    }

    public void destroy() {
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Destroy registry");
        }

        Set<Service> destroyRegistered = new HashSet(this.getRegistered());
        if (!destroyRegistered.isEmpty()) {
            Iterator i$ = (new HashSet(this.getRegistered())).iterator();

            while(i$.hasNext()) {
                Service service = (Service)i$.next();

                try {
                    this.unregister(service);
                    if (this.logger.isInfoEnabled()) {
                        this.logger.info("Destroy unregister service " + service);
                    }
                } catch (Throwable var10) {
                    this.logger.warn("Failed to unregister service " + service + " to registry " + this.getHosts() + " on destroy, cause: " + var10.getMessage(), var10);
                }
            }
        }

        Map<String, Set<NotifyListener>> destroySubscribed = new HashMap(this.getSubscribed());
        if (!destroySubscribed.isEmpty()) {
            Iterator i$ = destroySubscribed.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<String, Set<NotifyListener>> entry = (Entry)i$.next();
                String service = (String)entry.getKey();
                Iterator ii$ = ((Set)entry.getValue()).iterator();

                while(ii$.hasNext()) {
                    NotifyListener listener = (NotifyListener)i$.next();

                    try {
                        this.unsubscribe(service, listener);
                        if (this.logger.isInfoEnabled()) {
                            this.logger.info("Destroy unsubscribe service " + service);
                        }
                    } catch (Throwable var9) {
                        this.logger.warn("Failed to unsubscribe service " + service + " to registry " + this.getHosts() + " on destroy, cause: " + var9.getMessage(), var9);
                    }
                }
            }
        }

    }

    public String getHosts() {
        return this.hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }
}
