/*    */ package com.xman.common.rpc.client.thrift;
/*    */ 
/*    */ import com.xman.common.log.ILog;
/*    */ import com.xman.common.log.LogFactory;
/*    */ import com.xman.common.rpc.Server;
/*    */ import com.xman.common.rpc.pool.ConnPool;
/*    */ import com.xman.common.rpc.pool.ConnPoolConfig;
/*    */ import com.xman.common.rpc.pool.ConnWrapObject;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.apache.thrift.transport.TSocket;
/*    */ 
/*    */ public class ThriftTSocketPool
/*    */   implements AutoCloseable
/*    */ {
/* 17 */   private static final ILog logger = LogFactory.getLog(ThriftTSocketPool.class);
/*    */   
/* 19 */   private final ConcurrentHashMap<String, ConnPool<TSocket>> lives = new ConcurrentHashMap();
/*    */   private ConnPoolConfig config;
/*    */   
/*    */   public ThriftTSocketPool(ConnPoolConfig config) {
/* 23 */     this.config = config;
/*    */   }
/*    */   
/*    */   public ConnWrapObject<TSocket> getResource(String key) throws Exception
/*    */   {
/* 28 */     ConnPool<TSocket> pool = (ConnPool)this.lives.get(key);
/*    */     
/* 30 */     if (pool == null) {
/* 31 */       this.lives.putIfAbsent(key, new ConnPool(new ThriftTSocketFactory(), Server.valueOf(key), this.config));
/*    */       
/* 33 */       pool = (ConnPool)this.lives.get(key);
/*    */     }
/*    */     
/* 36 */     return pool.borrowObject();
/*    */   }
/*    */   
/*    */   public void returnResource(String key, ConnWrapObject<TSocket> resource)
/*    */   {
/* 41 */     TSocket obj = (TSocket)resource.getObject();
/* 42 */     if (!obj.isOpen()) {
/* 43 */       return;
/*    */     }
/*    */     try
/*    */     {
/* 47 */       ConnPool<TSocket> pool = (ConnPool)this.lives.get(key);
/* 48 */       if (pool != null) {
/* 49 */         pool.returnObject(resource);
/*    */       }
/*    */     } catch (Exception e) {
/* 52 */       logger.error("Could not return the resource to the pool", e);
/*    */     }
/*    */   }
/*    */   
/*    */   public void returnBrokenResource(String key, ConnWrapObject<TSocket> resource)
/*    */   {
/*    */     try {
/* 59 */       ConnPool<TSocket> pool = (ConnPool)this.lives.get(key);
/* 60 */       if (pool != null) {
/* 61 */         pool.invalidateObject(resource);
/*    */       }
/*    */     } catch (Exception e) {
/* 64 */       logger.error("Could not return broken resource to the pool", e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public synchronized void close()
/*    */   {
/* 71 */     for (Map.Entry<String, ConnPool<TSocket>> entry : this.lives.entrySet()) {
/* 72 */       ((ConnPool)entry.getValue()).close();
/*    */     }
/*    */   }
/*    */   
/*    */   public void clear(String key)
/*    */   {
/* 78 */     ConnPool<TSocket> pool = (ConnPool)this.lives.get(key);
/* 79 */     if (pool != null) {
/* 80 */       pool.clear();
/*    */     }
/*    */   }
/*    */   
/*    */   public Map<String, ConnPool<TSocket>> getLives()
/*    */   {
/* 86 */     return this.lives;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/client/thrift/ThriftTSocketPool.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */