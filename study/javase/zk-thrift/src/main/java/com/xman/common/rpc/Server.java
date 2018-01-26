/*    */ package com.xman.common.rpc;
/*    */ 
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class Server {
/*    */   private String host;
/*    */   private int port;
/*    */   
/*    */   public Server(String host, int port) {
/* 10 */     this.host = host;
/* 11 */     this.port = port;
/*    */   }
/*    */   
/*    */   public static Server valueOf(String key)
/*    */   {
/* 16 */     String[] tmp = StringUtils.split(key, ':');
/* 17 */     return new Server(tmp[0], Integer.parseInt(tmp[1]));
/*    */   }
/*    */   
/*    */   public String getHost()
/*    */   {
/* 22 */     return this.host;
/*    */   }
/*    */   
/*    */   public int getPort()
/*    */   {
/* 27 */     return this.port;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 32 */     return this.host + ":" + this.port;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/Server.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */