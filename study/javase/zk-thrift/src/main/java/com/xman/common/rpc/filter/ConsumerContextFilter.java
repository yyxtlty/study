/*    */ package com.xman.common.rpc.filter;
/*    */ 
/*    */ import com.xman.common.rpc.invoke.Invocation;
/*    */ import com.xman.common.rpc.invoke.Invoker;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsumerContextFilter
/*    */   implements Filter
/*    */ {
/*    */   public Object invoke(Invoker invoker, Invocation invocation)
/*    */     throws Throwable
/*    */   {
/* 18 */     boolean selfGen = false;
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     try
/*    */     {
/* 27 */       return invoker.invoke(invocation);
/*    */     } finally {
/* 29 */       if (!selfGen) {}
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 37 */     return "consumerContextFilter";
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/ConsumerContextFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */