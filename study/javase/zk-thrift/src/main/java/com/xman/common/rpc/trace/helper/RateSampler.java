/*    */ package com.xman.common.rpc.trace.helper;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RateSampler
/*    */   implements Sampler
/*    */ {
/*    */   private final double rate;
/*    */   private final Random random;
/*    */   
/*    */   public RateSampler(double rate)
/*    */   {
/* 19 */     this.rate = rate;
/* 20 */     this.random = new Random();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isSample()
/*    */   {
/* 26 */     if (this.rate <= 0.0D)
/* 27 */       return false;
/* 28 */     if (this.rate >= 1.0D) {
/* 29 */       return true;
/*    */     }
/* 31 */     return this.random.nextDouble() < this.rate;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/trace/helper/RateSampler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */