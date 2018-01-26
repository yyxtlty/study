/*     */ package com.xman.common.rpc.registry.zk.client;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.CopyOnWriteArraySet;
/*     */ 
/*     */ public abstract class AbstractZookeeperClient<TargetChildListener> implements ZookeeperClient
/*     */ {
/*  13 */   protected final ILog logger = LogFactory.getLog(getClass());
/*     */   
/*     */   private final String hosts;
/*     */   
/*  17 */   private final Set<StateListener> stateListeners = new CopyOnWriteArraySet();
/*     */   
/*  19 */   private final ConcurrentMap<String, ConcurrentMap<ChildListener, TargetChildListener>> childListeners = new ConcurrentHashMap();
/*     */   
/*  21 */   private volatile boolean closed = false;
/*     */   
/*     */   public AbstractZookeeperClient(String hosts) {
/*  24 */     this.hosts = hosts;
/*     */   }
/*     */   
/*     */   public String getHosts()
/*     */   {
/*  29 */     return this.hosts;
/*     */   }
/*     */   
/*     */   public void create(String path, boolean ephemeral)
/*     */   {
/*  34 */     int i = path.lastIndexOf('/');
/*  35 */     if (i > 0) {
/*  36 */       create(path.substring(0, i), false);
/*     */     }
/*  38 */     if (ephemeral) {
/*  39 */       createEphemeral(path);
/*     */     } else {
/*  41 */       createPersistent(path);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addStateListener(StateListener listener)
/*     */   {
/*  47 */     this.stateListeners.add(listener);
/*     */   }
/*     */   
/*     */   public void removeStateListener(StateListener listener)
/*     */   {
/*  52 */     this.stateListeners.remove(listener);
/*     */   }
/*     */   
/*     */   public Set<StateListener> getSessionListeners()
/*     */   {
/*  57 */     return this.stateListeners;
/*     */   }
/*     */   
/*     */   public List<String> addChildListener(String path, ChildListener listener)
/*     */   {
/*  62 */     ConcurrentMap<ChildListener, TargetChildListener> listeners = (ConcurrentMap)this.childListeners.get(path);
/*  63 */     if (listeners == null) {
/*  64 */       this.childListeners.putIfAbsent(path, new ConcurrentHashMap());
/*  65 */       listeners = (ConcurrentMap)this.childListeners.get(path);
/*     */     }
/*  67 */     TargetChildListener targetListener = listeners.get(listener);
/*  68 */     if (targetListener == null) {
/*  69 */       listeners.putIfAbsent(listener, createTargetChildListener(path, listener));
/*  70 */       targetListener = listeners.get(listener);
/*     */     }
/*  72 */     return addTargetChildListener(path, targetListener);
/*     */   }
/*     */   
/*     */   public void removeChildListener(String path, ChildListener listener)
/*     */   {
/*  77 */     ConcurrentMap<ChildListener, TargetChildListener> listeners = (ConcurrentMap)this.childListeners.get(path);
/*  78 */     if (listeners != null) {
/*  79 */       TargetChildListener targetListener = listeners.remove(listener);
/*  80 */       if (targetListener != null) {
/*  81 */         removeTargetChildListener(path, targetListener);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void stateChanged(int state)
/*     */   {
/*  88 */     this.logger.debug("state changed: " + state);
/*  89 */     for (StateListener sessionListener : getSessionListeners()) {
/*  90 */       sessionListener.stateChanged(state);
/*     */     }
/*     */   }
/*     */   
/*     */   public void close()
/*     */   {
/*  96 */     if (this.closed) {
/*  97 */       return;
/*     */     }
/*  99 */     this.closed = true;
/*     */     try {
/* 101 */       doClose();
/*     */     } catch (Throwable t) {
/* 103 */       this.logger.warn(t.getMessage(), t);
/*     */     }
/*     */   }
/*     */   
/*     */   protected abstract void doClose();
/*     */   
/*     */   protected abstract void createPersistent(String paramString);
/*     */   
/*     */   protected abstract void createEphemeral(String paramString);
/*     */   
/*     */   protected abstract TargetChildListener createTargetChildListener(String paramString, ChildListener paramChildListener);
/*     */   
/*     */   protected abstract List<String> addTargetChildListener(String paramString, TargetChildListener paramTargetChildListener);
/*     */   
/*     */   protected abstract void removeTargetChildListener(String paramString, TargetChildListener paramTargetChildListener);
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/zk/client/AbstractZookeeperClient.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */