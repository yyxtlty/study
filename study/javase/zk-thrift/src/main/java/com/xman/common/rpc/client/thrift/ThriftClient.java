/*     */ package com.xman.common.rpc.client.thrift;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import com.xman.common.rpc.RpcException;
/*     */ import com.xman.common.rpc.ThriftConfig;
/*     */ import com.xman.common.rpc.registry.Registry;
/*     */ import com.xman.common.rpc.registry.support.RegistryContainer;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import org.apache.thrift.TServiceClient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThriftClient
/*     */ {
/*  23 */   private static final ILog logger = LogFactory.getLog(ThriftClient.class);
/*     */   
/*  25 */   private Map<String, ClientProxy> unmodifyProxys = new HashMap();
/*  26 */   private Map<String, TServiceClient> unmodifyClients = new HashMap();
/*     */   
/*     */ 
/*  29 */   private AtomicBoolean inited = new AtomicBoolean();
/*  30 */   private AtomicBoolean stoped = new AtomicBoolean();
/*     */   
/*     */ 
/*     */   private Registry registry;
/*     */   
/*     */   private ThriftConfig config;
/*     */   
/*  37 */   private List<ClientProxy> proxyList = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */   public ThriftClient()
/*     */   {
/*  43 */     this("thrift-rpc.properties");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ThriftClient(String configFile)
/*     */   {
/*  51 */     this.config = new ThriftConfig(configFile);
/*     */   }
/*     */   
/*     */   public ThriftClient(InputStream inputStream) {
/*  55 */     this.config = new ThriftConfig(inputStream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ThriftClient getInstance()
/*     */   {
/*  63 */     return new ThriftClient();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(ClientProxy proxy)
/*     */   {
/*  72 */     this.proxyList.add(proxy);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init()
/*     */   {
/*  80 */     if (!this.inited.compareAndSet(false, true)) {
/*  81 */       return;
/*     */     }
/*     */     
/*  84 */     for (int i = 0; i < this.proxyList.size(); i++) {
/*  85 */       this.unmodifyProxys.put(((ClientProxy)this.proxyList.get(i)).getIfaceName(), this.proxyList.get(i));
/*     */     }
/*     */     
/*  88 */     boolean register = false;
/*  89 */     for (ClientProxy client : this.unmodifyProxys.values()) {
/*  90 */       client.init(this.config);
/*  91 */       this.unmodifyClients.put(client.getIfaceName(), client.getClient());
/*  92 */       if (client.isRegister()) {
/*  93 */         register = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  98 */     if (register) {
/*  99 */       this.registry = RegistryContainer.getRegistry(this.config.regcfg);
/* 100 */       if (this.registry == null) {
/* 101 */         throw new IllegalStateException("no register config found");
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 106 */       if (this.registry != null) {
/* 107 */         for (ClientProxy client : this.unmodifyProxys.values()) {
/* 108 */           if (client.isRegister()) {
/* 109 */             this.registry.register(client.getService());
/* 110 */             this.registry.subscribe(client.getIfaceName(), client.getListener());
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 115 */       stop();
/* 116 */       throw new RpcException("init client failure", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void stop()
/*     */   {
/* 125 */     if (!this.inited.get()) {
/* 126 */       logger.warn("client not start, so stop do nothing");
/* 127 */       return;
/*     */     }
/*     */     
/* 130 */     if (!this.stoped.compareAndSet(false, true)) {
/* 131 */       return;
/*     */     }
/*     */     
/* 134 */     for (ClientProxy client : this.unmodifyProxys.values()) {
/* 135 */       client.stop();
/* 136 */       if ((this.registry != null) && (this.registry.isAvailable()) && (client.isRegister())) {
/* 137 */         this.registry.unregister(client.getService());
/* 138 */         this.registry.unsubscribe(client.getIfaceName(), client.getListener());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TServiceClient getClient(Class<?> ifaceClass)
/*     */   {
/* 148 */     if (!this.inited.get()) {
/* 149 */       throw new IllegalStateException("ThriftClient not inited");
/*     */     }
/*     */     
/* 152 */     return (TServiceClient)this.unmodifyClients.get(ifaceClass.getName());
/*     */   }
/*     */   
/*     */   public List<ClientProxy> getProxyList()
/*     */   {
/* 157 */     return this.proxyList;
/*     */   }
/*     */   
/*     */   public void setProxyList(List<ClientProxy> proxyList)
/*     */   {
/* 162 */     this.proxyList = proxyList;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/client/thrift/ThriftClient.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */