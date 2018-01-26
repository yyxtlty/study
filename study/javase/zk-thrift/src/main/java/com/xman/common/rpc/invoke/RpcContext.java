/*    */ package com.xman.common.rpc.invoke;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RpcContext implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 10 */   private static final ThreadLocal<RpcContext> CONTEXT = new ThreadLocal()
/*    */   {
/*    */     protected RpcContext initialValue()
/*    */     {
/* 14 */       return new RpcContext();
/*    */     }
/*    */   };
/*    */   
/* 18 */   private final Map<String, Object> attachments = new HashMap();
/*    */   
/*    */   private String remoteHost;
/*    */   
/*    */   private String serverHost;
/*    */   
/*    */ 
/*    */   public static RpcContext getContext()
/*    */   {
/* 27 */     return (RpcContext)CONTEXT.get();
/*    */   }
/*    */   
/*    */   public static void setContext(RpcContext ctx)
/*    */   {
/* 32 */     CONTEXT.set(ctx);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void removeContext()
/*    */   {
/* 45 */     CONTEXT.remove();
/*    */   }
/*    */   
/*    */   public Object getAttachment(String key)
/*    */   {
/* 50 */     return this.attachments.get(key);
/*    */   }
/*    */   
/*    */   public RpcContext addAttachment(String key, Object value)
/*    */   {
/* 55 */     this.attachments.put(key, value);
/* 56 */     return this;
/*    */   }
/*    */   
/*    */   public RpcContext addAttachments(Map<String, Object> attachs)
/*    */   {
/* 61 */     if (attachs != null) {
/* 62 */       this.attachments.putAll(attachs);
/*    */     }
/* 64 */     return this;
/*    */   }
/*    */   
/*    */   public Map<String, Object> getAttachments()
/*    */   {
/* 69 */     return this.attachments;
/*    */   }
/*    */   
/*    */   public String getRemoteHost()
/*    */   {
/* 74 */     return this.remoteHost;
/*    */   }
/*    */   
/*    */   public RpcContext setRemoteHost(String remoteHost)
/*    */   {
/* 79 */     this.remoteHost = remoteHost;
/* 80 */     return this;
/*    */   }
/*    */   
/*    */   public String getServerHost()
/*    */   {
/* 85 */     return this.serverHost;
/*    */   }
/*    */   
/*    */   public RpcContext setServerHost(String serverHost)
/*    */   {
/* 90 */     this.serverHost = serverHost;
/* 91 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/invoke/RpcContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */