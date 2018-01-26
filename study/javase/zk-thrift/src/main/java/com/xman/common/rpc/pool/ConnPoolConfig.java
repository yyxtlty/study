/*    */ package com.xman.common.rpc.pool;
/*    */ 
/*    */ import org.apache.commons.configuration.PropertiesConfiguration;
/*    */ 
/*    */ public class ConnPoolConfig
/*    */ {
/*    */   public int maxIdle;
/*    */   public boolean testOnCreate;
/*    */   public boolean testOnBorrow;
/*    */   public boolean testOnReturn;
/*    */   
/*    */   public ConnPoolConfig() {}
/*    */   
/*    */   public ConnPoolConfig(PropertiesConfiguration prop)
/*    */   {
/* 16 */     this.maxIdle = prop.getInt("thrift.connpool.maxidle", 8);
/* 17 */     if (this.maxIdle < 0) {
/* 18 */       throw new IllegalArgumentException("maxIdle must >= 0");
/*    */     }
/* 20 */     this.testOnCreate = prop.getBoolean("thrift.connpool.testoncreate", false);
/*    */     
/* 22 */     this.testOnBorrow = prop.getBoolean("thrift.connpool.testonborrow", false);
/*    */     
/* 24 */     this.testOnReturn = prop.getBoolean("thrift.connpool.testonreturn", false);
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/pool/ConnPoolConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */