/*    */ package com.xman.common.rpc.registry.support;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.apache.commons.configuration.PropertiesConfiguration;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegistryConfig
/*    */ {
/*    */   public String addrs;
/*    */   public int timeout;
/*    */   public int sessionTimeout;
/*    */   public int retryPeriod;
/*    */   public boolean checkWhenStartup;
/*    */   public String zkclient;
/*    */   
/*    */   public RegistryConfig() {}
/*    */   
/*    */   public RegistryConfig(PropertiesConfiguration prop)
/*    */   {
/* 23 */     String[] hosts = prop.getStringArray("thrift.registry.addr");
/* 24 */     Arrays.sort(hosts);
/* 25 */     this.addrs = StringUtils.join(hosts, ',');
/*    */     
/* 27 */     this.sessionTimeout = prop.getInt("thrift.registry.session", 5000);
/* 28 */     if (this.sessionTimeout <= 0) {
/* 29 */       throw new IllegalArgumentException("sessionTimeout must > 0");
/*    */     }
/*    */     
/* 32 */     this.timeout = prop.getInt("thrift.registry.timeout", 2000);
/* 33 */     if (this.timeout <= 0) {
/* 34 */       throw new IllegalArgumentException("timeout must > 0");
/*    */     }
/*    */     
/* 37 */     this.retryPeriod = prop.getInt("thrift.registry.retry.period", 5000);
/* 38 */     if (this.retryPeriod <= 0) {
/* 39 */       throw new IllegalArgumentException("retryPeriod must > 0");
/*    */     }
/*    */     
/* 42 */     this.checkWhenStartup = prop.getBoolean("thrift.registry.check", false);
/*    */     
/* 44 */     this.zkclient = prop.getString("thrift.registry.zkclient", "curator");
/* 45 */     if ((!"curator".equals(this.zkclient)) && (!"zkclient".equals(this.zkclient)))
/*    */     {
/* 47 */       throw new IllegalArgumentException("zkclient must be [curator|zkclient]");
/*    */     }
/*    */   }
/*    */   
/*    */   public String getKey()
/*    */   {
/* 53 */     return this.addrs;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 58 */     return this.addrs;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/registry/support/RegistryConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */