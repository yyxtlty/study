/*    */ package com.xman.common.rpc.trace.helper;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QpsAwareSampler
/*    */   implements Sampler
/*    */ {
/*    */   private final int bench;
/*    */   private volatile Long lastTime;
/* 16 */   private AtomicLong qps = new AtomicLong();
/*    */   
/*    */   public QpsAwareSampler() {
/* 19 */     this(100);
/*    */   }
/*    */   
/*    */   public QpsAwareSampler(int bench) {
/* 23 */     this.bench = bench;
/* 24 */     this.lastTime = Long.valueOf(System.currentTimeMillis());
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isSample()
/*    */   {
/* 30 */     boolean isSample = true;
/* 31 */     long n = this.qps.incrementAndGet();
/* 32 */     if (System.currentTimeMillis() - this.lastTime.longValue() < 1000L) {
/* 33 */       if (n > this.bench) {
/* 34 */         isSample = false;
/*    */       }
/*    */     } else {
/* 37 */       this.qps.getAndSet(1L);
/* 38 */       this.lastTime = Long.valueOf(System.currentTimeMillis());
/*    */     }
/* 40 */     return isSample;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/trace/helper/QpsAwareSampler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */