/*    */ package com.xman.common.rpc.common;
/*    */ 
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ public class NamedThreadFactory implements ThreadFactory
/*    */ {
/*  8 */   private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);
/*    */   
/* 10 */   private final AtomicInteger threadNum = new AtomicInteger(1);
/*    */   
/*    */   private final String prefix;
/*    */   
/*    */   private final boolean daemo;
/*    */   
/*    */   private final ThreadGroup group;
/*    */   
/*    */   public NamedThreadFactory()
/*    */   {
/* 20 */     this("pool-" + POOL_SEQ.getAndIncrement(), false);
/*    */   }
/*    */   
/*    */   public NamedThreadFactory(String prefix)
/*    */   {
/* 25 */     this(prefix, false);
/*    */   }
/*    */   
/*    */   public NamedThreadFactory(String prefix, boolean daemo)
/*    */   {
/* 30 */     this.prefix = (prefix + "-thread-");
/* 31 */     this.daemo = daemo;
/* 32 */     SecurityManager s = System.getSecurityManager();
/* 33 */     this.group = (s == null ? Thread.currentThread().getThreadGroup() : s.getThreadGroup());
/*    */   }
/*    */   
/*    */   public Thread newThread(Runnable runnable)
/*    */   {
/* 38 */     String name = this.prefix + this.threadNum.getAndIncrement();
/* 39 */     Thread ret = new Thread(this.group, runnable, name, 0L);
/* 40 */     ret.setDaemon(this.daemo);
/* 41 */     return ret;
/*    */   }
/*    */   
/*    */   public ThreadGroup getThreadGroup()
/*    */   {
/* 46 */     return this.group;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/common/NamedThreadFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */