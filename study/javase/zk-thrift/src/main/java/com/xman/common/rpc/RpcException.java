/*    */ package com.xman.common.rpc;
/*    */ 
/*    */ public class RpcException extends RuntimeException {
/*    */   private static final long serialVersionUID = -2275296727467192665L;
/*    */   
/*  6 */   public RpcException(String message) { super(message); }
/*    */   
/*    */   public RpcException(String message, Throwable e)
/*    */   {
/* 10 */     super(message, e);
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/RpcException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */