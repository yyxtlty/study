//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc.registry.support;

import com.xman.common.rpc.Service;
import com.xman.common.rpc.common.ConcurrentHashSet;
import com.xman.common.rpc.common.NamedThreadFactory;
import com.xman.common.rpc.registry.NotifyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class FailbackRegistry extends AbstractRegistry {
    private final ScheduledExecutorService retryExecutor = Executors.newScheduledThreadPool(1, new NamedThreadFactory("ThriftRegistryFailedRetryTimer", true));
    private ScheduledFuture<?> retryFuture;
    private final Set<Service> failedRegistered = new ConcurrentHashSet();
    private final Set<Service> failedUnregistered = new ConcurrentHashSet();
    private final ConcurrentMap<String, Set<NotifyListener>> failedSubscribed = new ConcurrentHashMap();
    private final ConcurrentMap<String, Set<NotifyListener>> failedUnsubscribed = new ConcurrentHashMap();
    private final ConcurrentMap<String, Map<NotifyListener, List<Service>>> failedNotified = new ConcurrentHashMap();
    protected int retryPeriod = 5000;
    protected boolean checkWhenStartup = false;

    public FailbackRegistry() {
    }

    public void init() {
        this.logger.debug("init failback register");
        this.retryFuture = this.retryExecutor.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    FailbackRegistry.this.retry();
                } catch (Throwable var2) {
                    FailbackRegistry.this.logger.error("Unexpected error occur at failed retry, cause: " + var2.getMessage(), var2);
                }

            }
        }, (long)this.retryPeriod, (long)this.retryPeriod, TimeUnit.MILLISECONDS);
    }

    public Future<?> getRetryFuture() {
        return this.retryFuture;
    }

    public Set<Service> getFailedRegistered() {
        return this.failedRegistered;
    }

    public Set<Service> getFailedUnregistered() {
        return this.failedUnregistered;
    }

    public Map<String, Set<NotifyListener>> getFailedSubscribed() {
        return this.failedSubscribed;
    }

    public Map<String, Set<NotifyListener>> getFailedUnsubscribed() {
        return this.failedUnsubscribed;
    }

    public Map<String, Map<NotifyListener, List<Service>>> getFailedNotified() {
        return this.failedNotified;
    }

    private void addFailedSubscribed(String service, NotifyListener listener) {
        Set<NotifyListener> listeners = (Set)this.failedSubscribed.get(service);
        if (listeners == null) {
            this.failedSubscribed.putIfAbsent(service, new ConcurrentHashSet());
            listeners = (Set)this.failedSubscribed.get(service);
        }

        listeners.add(listener);
    }

    private void removeFailedSubscribed(String service, NotifyListener listener) {
        Set<NotifyListener> listeners = (Set)this.failedSubscribed.get(service);
        if (listeners != null) {
            listeners.remove(listener);
        }

        listeners = (Set)this.failedUnsubscribed.get(service);
        if (listeners != null) {
            listeners.remove(listener);
        }

        Map<NotifyListener, List<Service>> notified = (Map)this.failedNotified.get(service);
        if (notified != null) {
            notified.remove(listener);
        }

    }

    public void register(Service service) {
        super.register(service);
        this.failedRegistered.remove(service);
        this.failedUnregistered.remove(service);

        try {
            this.doRegister(service);
        } catch (Exception var5) {
            boolean check = this.isCheckWhenStartup();
            if (check) {
                throw new IllegalStateException("Failed to register " + service + " to registry cause: " + var5.getMessage(), var5);
            }

            this.logger.error("Failed to register " + service + ", waiting for retry, cause: " + var5.getMessage(), var5);
            this.failedRegistered.add(service);
        }

    }

    public void unregister(Service service) {
        super.unregister(service);
        this.failedRegistered.remove(service);
        this.failedUnregistered.remove(service);

        try {
            this.doUnregister(service);
        } catch (Exception var5) {
            boolean check = this.isCheckWhenStartup();
            if (check) {
                throw new IllegalStateException("Failed to unregister " + service + " to registry cause: " + var5.getMessage(), var5);
            }

            this.logger.error("Failed to uregister " + service + ", waiting for retry, cause: " + var5.getMessage(), var5);
            this.failedUnregistered.add(service);
        }

    }

    public void subscribe(String service, NotifyListener listener) {
        super.subscribe(service, listener);
        this.removeFailedSubscribed(service, listener);

        try {
            this.doSubscribe(service, listener);
        } catch (Exception var6) {
            boolean check = this.isCheckWhenStartup();
            if (check) {
                throw new IllegalStateException("Failed to subscribe " + service + ", cause: " + var6.getMessage(), var6);
            }

            this.logger.error("Failed to subscribe " + service + ", waiting for retry, cause: " + var6.getMessage(), var6);
            this.addFailedSubscribed(service, listener);
        }

    }

    public void unsubscribe(String service, NotifyListener listener) {
        super.unsubscribe(service, listener);
        this.removeFailedSubscribed(service, listener);

        try {
            this.doUnsubscribe(service, listener);
        } catch (Exception var7) {
            boolean check = this.isCheckWhenStartup();
            if (check) {
                throw new IllegalStateException("Failed to unsubscribe " + service + " to registry cause: " + var7.getMessage(), var7);
            }

            this.logger.error("Failed to unsubscribe " + service + ", waiting for retry, cause: " + var7.getMessage(), var7);
            Set<NotifyListener> listeners = (Set)this.failedUnsubscribed.get(service);
            if (listeners == null) {
                this.failedUnsubscribed.putIfAbsent(service, new ConcurrentHashSet());
                listeners = (Set)this.failedUnsubscribed.get(service);
            }

            listeners.add(listener);
        }

    }

    protected void notify(String service, NotifyListener listener, List<Service> services) {
        if (service == null) {
            throw new IllegalArgumentException("notify service == null");
        } else if (listener == null) {
            throw new IllegalArgumentException("notify listener == null");
        } else {
            try {
                this.doNotify(service, listener, services);
            } catch (Exception var6) {
                Map<NotifyListener, List<Service>> listeners = (Map)this.failedNotified.get(service);
                if (listeners == null) {
                    this.failedNotified.putIfAbsent(service, new ConcurrentHashMap());
                    listeners = (Map)this.failedNotified.get(service);
                }

                listeners.put(listener, services);
                this.logger.error("Failed to notify for subscribe " + service + ", waiting for retry, cause: " + var6.getMessage(), var6);
            }

        }
    }

    protected void doNotify(String service, NotifyListener listener, List<Service> services) {
        super.notify(service, listener, services);
    }

    protected void recover() throws Exception {
        this.logger.debug("recover registers and subscribes");
        Set<Service> recoverRegistered = new HashSet(this.getRegistered());
        if (!recoverRegistered.isEmpty()) {
            this.logger.info("Recover register service " + recoverRegistered);
            Iterator i$ = recoverRegistered.iterator();

            while(i$.hasNext()) {
                Service service = (Service)i$.next();
                this.failedRegistered.add(service);
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
                    this.addFailedSubscribed(service, listener);
                }
            }
        }

    }

    protected void retry() {
        this.logger.debug("registry retry check");

    }

    public void destroy() {
        super.destroy();

        try {
            this.retryFuture.cancel(true);
        } catch (Throwable var2) {
            this.logger.warn(var2.getMessage(), var2);
        }

    }

    protected abstract void doRegister(Service var1);

    protected abstract void doUnregister(Service var1);

    protected abstract void doSubscribe(String var1, NotifyListener var2);

    protected abstract void doUnsubscribe(String var1, NotifyListener var2);

    public int getRetryPeriod() {
        return this.retryPeriod;
    }

    public void setRetryPeriod(int retryPeriod) {
        this.retryPeriod = retryPeriod;
    }

    public boolean isCheckWhenStartup() {
        return this.checkWhenStartup;
    }

    public void setCheckWhenStartup(boolean checkWhenStartup) {
        this.checkWhenStartup = checkWhenStartup;
    }
}
