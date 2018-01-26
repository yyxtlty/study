/*    */ package com.xman.common.rpc.invoke;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RpcInvocation
/*    */   implements Invocation
/*    */ {
/*    */   private Class<?> ifaceClass;
/*    */   private String methodName;
/*    */   private Object[] arguments;
/*    */   private Object impl;
/* 22 */   private final Map<String, Object> attachments = new HashMap();
/*    */   private boolean isClient;
/*    */   
/*    */   public static RpcInvocation getRpcInvocation(Object impl, Object[] argObjects)
/*    */   {
/* 27 */     return new RpcInvocation(impl, argObjects);
/*    */   }
/*    */   
/*    */   public RpcInvocation(Object impl, Object[] argObjects) {
/* 31 */     this.impl = impl;
/* 32 */     this.arguments = argObjects;
/*    */   }
/*    */   
/*    */ 
/*    */   public Object[] getArguments()
/*    */   {
/* 38 */     return this.arguments;
/*    */   }
/*    */   
/*    */   public Class<?> getIface()
/*    */   {
/* 43 */     return this.ifaceClass;
/*    */   }
/*    */   
/*    */   public String getMethodName()
/*    */   {
/* 48 */     return this.methodName;
/*    */   }
/*    */   
/*    */   public Map<String, Object> getAttachments()
/*    */   {
/* 53 */     return this.attachments;
/*    */   }
/*    */   
/*    */   public Object getAttachment(String key)
/*    */   {
/* 58 */     return this.attachments.get(key);
/*    */   }
/*    */   
/*    */   public boolean isClient()
/*    */   {
/* 63 */     return this.isClient;
/*    */   }
/*    */   
/*    */   public Class<?> getIfaceClass() {
/* 67 */     return this.ifaceClass;
/*    */   }
/*    */   
/*    */   public RpcInvocation setIfaceClass(Class<?> ifaceClass) {
/* 71 */     this.ifaceClass = ifaceClass;
/* 72 */     return this;
/*    */   }
/*    */   
/*    */   public RpcInvocation setMethodName(String methodName) {
/* 76 */     this.methodName = methodName;
/* 77 */     return this;
/*    */   }
/*    */   
/*    */   public RpcInvocation addAttachments(String key, Object value) {
/* 81 */     this.attachments.put(key, value);
/* 82 */     return this;
/*    */   }
/*    */   
/*    */   public RpcInvocation setClient(boolean isClient) {
/* 86 */     this.isClient = isClient;
/* 87 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */   public Object getImpl()
/*    */   {
/* 93 */     return this.impl;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/invoke/RpcInvocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */