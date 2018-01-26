/*    */ package com.xman.common.rpc.lb.impl;
/*    */ 
/*    */ import com.xman.common.rpc.Service;
/*    */ import com.xman.common.rpc.lb.LoadBalance;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ public class RRLoadBalance
/*    */   implements LoadBalance
/*    */ {
/* 12 */   private AtomicInteger inc = new AtomicInteger(UUID.randomUUID().toString().hashCode());
/*    */   
/*    */ 
/*    */   public Service select(List<Service> services)
/*    */   {
/* 17 */     return (Service)services.get(Math.abs(this.inc.incrementAndGet()) % services.size());
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/lb/impl/RRLoadBalance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */