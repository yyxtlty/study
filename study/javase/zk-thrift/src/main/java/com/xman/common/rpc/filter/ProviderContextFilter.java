/*    */ package com.xman.common.rpc.filter;
/*    */ 
/*    */ import com.xman.common.rpc.invoke.Invocation;
/*    */ import com.xman.common.rpc.invoke.Invoker;
/*    */ import com.xman.common.rpc.invoke.RpcContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProviderContextFilter
/*    */   implements Filter
/*    */ {
/*    */   public Object invoke(Invoker invoker, Invocation invocation)
/*    */     throws Throwable
/*    */   {
/* 17 */     RpcContext ctx = RpcContext.getContext();
/* 18 */     String traceId = (String)ctx.getAttachment("rpcontext.attach.traceid");
/* 19 */     if (traceId != null) {}
/*    */     
/*    */     try
/*    */     {
/* 23 */       Object localObject1 = invoker.invoke(invocation);return localObject1;
/*    */     }
/*    */     finally {}
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 31 */     return "providerContextFilter";
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/ProviderContextFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */