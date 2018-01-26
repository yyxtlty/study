/*    */ package com.xman.common.rpc;
/*    */ 
/*    */ public class ServiceException extends RuntimeException {
/*    */   private static final long serialVersionUID = -5733726019795660634L;
/*    */   
/*    */   public ServiceException(String message) {
/*  7 */     super(message);
/*    */   }
/*    */   
/*    */   public ServiceException(String message, Throwable e) {
/* 11 */     super(message, e);
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/ServiceException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */