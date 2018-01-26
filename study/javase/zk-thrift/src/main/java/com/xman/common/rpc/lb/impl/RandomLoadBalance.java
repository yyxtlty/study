/*    */ package com.xman.common.rpc.lb.impl;
/*    */ 
/*    */ import com.xman.common.rpc.Service;
/*    */ import com.xman.common.rpc.lb.LoadBalance;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class RandomLoadBalance
/*    */   implements LoadBalance
/*    */ {
/* 11 */   private Random rand = new Random();
/*    */   
/*    */ 
/*    */   public Service select(List<Service> services)
/*    */   {
/* 16 */     return (Service)services.get(this.rand.nextInt(services.size()));
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/lb/impl/RandomLoadBalance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */