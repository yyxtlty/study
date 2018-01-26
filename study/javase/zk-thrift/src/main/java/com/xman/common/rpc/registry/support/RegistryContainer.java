/*    */ package com.xman.common.rpc.registry.support;
/*    */ 
/*    */ import com.xman.common.log.ILog;
/*    */ import com.xman.common.log.LogFactory;
/*    */ import com.xman.common.rpc.registry.Registry;
/*    */ import com.xman.common.rpc.registry.zk.ZookeeperRegistry;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.locks.ReentrantLock;
/*    */ 
/*    */ 
/*    */ public class RegistryContainer
/*    */ {
/* 14 */   private static final ILog logger = LogFactory.getLog(RegistryContainer.class);
/*    */   
/* 16 */   private static final ReentrantLock LOCK = new ReentrantLock();
/* 17 */   private static final Map<String, Registry> REGISTRYS = new HashMap();
/*    */   
/*    */   public static void destroyAll() {
/* 20 */     LOCK.lock();
/* 21 */     logger.info("Close all registries " + REGISTRYS.values());
/*    */     try {
/* 23 */       for (Registry registry : REGISTRYS.values()) {
/*    */         try {
/* 25 */           registry.destroy();
/*    */         } catch (Throwable e) {
/* 27 */           logger.error(e.getMessage(), e);
/*    */         }
/*    */       }
/* 30 */       REGISTRYS.clear();
/*    */     }
/*    */     finally {
/* 33 */       LOCK.unlock();
/*    */     }
/*    */   }
/*    */   
/*    */   public static Registry getRegistry(RegistryConfig config)
/*    */   {
/* 39 */     if ((config.addrs == null) || (config.addrs.isEmpty())) {
/* 40 */       return null;
/*    */     }
/*    */     
/* 43 */     LOCK.lock();
/*    */     try {
/* 45 */       Registry registry = (Registry)REGISTRYS.get(config.getKey());
/* 46 */       if (registry != null) {
/* 47 */         return registry;
/*    */       }
/*    */       
/* 50 */       ZookeeperRegistry zregistry = new ZookeeperRegistry();
/* 51 */       zregistry.setHosts(config.addrs);
/* 52 */       zregistry.setTimeout(config.timeout);
/* 53 */       zregistry.setSessionTimeout(config.sessionTimeout);
/* 54 */       zregistry.setRetryPeriod(config.retryPeriod);
/* 55 */       zregistry.setCheckWhenStartup(config.checkWhenStartup);
/* 56 */       zregistry.setClient(config.zkclient);
/* 57 */       zregistry.init();
/*    */       
/* 59 */       REGISTRYS.put(config.getKey(), zregistry);
/* 60 */       return zregistry;
/*    */     } finally {
/* 62 */       LOCK.unlock();
/*    */     }
/*    */   }
/*    */   
/*    */   public static Registry createRegistry(RegistryConfig config)
/*    */   {
/* 68 */     if ((config.addrs == null) || (config.addrs.isEmpty())) {
/* 69 */       return null;
/*    */     }
/*    */     
/* 72 */     ZookeeperRegistry zregistry = new ZookeeperRegistry();
/* 73 */     zregistry.setHosts(config.addrs);
/* 74 */     zregistry.setTimeout(config.timeout);
/* 75 */     zregistry.setSessionTimeout(config.sessionTimeout);
/* 76 */     zregistry.setRetryPeriod(config.retryPeriod);
/* 77 */     zregistry.setCheckWhenStartup(config.checkWhenStartup);
/* 78 */     zregistry.setClient(config.zkclient);
/* 79 */     zregistry.init();
/*    */     
/* 81 */     return zregistry;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/support/RegistryContainer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */