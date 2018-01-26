/*     */ package com.xman.common.rpc.filter.trace;
/*     */ 
/*     */ import org.apache.commons.configuration.PropertiesConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceConfig
/*     */ {
/*     */   public static final String LOG_COLLECTOR = "log";
/*     */   public static final String EMPTY_COLLECTOR = "empty";
/*     */   public static final String QPS_SAMPLER = "qps";
/*     */   public static final String RATE_SAMPLER = "rate";
/*     */   public static final String FILE_COLLECTOR = "file";
/*     */   public static final String KAFKA_COLLECTOR = "kafka";
/*     */   private boolean enable;
/*     */   private String sampler;
/*     */   private double rate;
/*     */   private int qpsBase;
/*     */   private String collector;
/*     */   private String level;
/*     */   private String appName;
/*     */   private String host;
/*     */   
/*     */   public TraceConfig(PropertiesConfiguration prop)
/*     */   {
/*  44 */     this.enable = prop.getBoolean("thrift.trace.enable", false);
/*  45 */     this.sampler = prop.getString("thrift.trace.sampler", "qps");
/*  46 */     if (this.sampler.equals("qps")) {
/*  47 */       this.qpsBase = prop.getInt("thrift.trace.qps", 100);
/*     */     } else {
/*  49 */       this.rate = prop.getDouble("thrift.trace.rate", 0.1D);
/*     */     }
/*     */     
/*  52 */     this.collector = prop.getString("thrift.trace.collector", "kafka");
/*     */     
/*  54 */     this.level = prop.getString("thrift.trace.level", "INFO");
/*     */   }
/*     */   
/*     */   public boolean isEnable()
/*     */   {
/*  59 */     return this.enable;
/*     */   }
/*     */   
/*     */   public void setEnable(boolean enable)
/*     */   {
/*  64 */     this.enable = enable;
/*     */   }
/*     */   
/*     */   public String getSampler()
/*     */   {
/*  69 */     return this.sampler;
/*     */   }
/*     */   
/*     */   public double getRate()
/*     */   {
/*  74 */     return this.rate;
/*     */   }
/*     */   
/*     */   public int getQpsBase()
/*     */   {
/*  79 */     return this.qpsBase;
/*     */   }
/*     */   
/*     */   public String getCollector()
/*     */   {
/*  84 */     return this.collector;
/*     */   }
/*     */   
/*     */   public void setCollector(String collector)
/*     */   {
/*  89 */     this.collector = collector;
/*     */   }
/*     */   
/*     */   public String getLevel()
/*     */   {
/*  94 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(String level)
/*     */   {
/*  99 */     this.level = level;
/*     */   }
/*     */   
/*     */   public void setSampler(String sampler)
/*     */   {
/* 104 */     this.sampler = sampler;
/*     */   }
/*     */   
/*     */   public void setRate(double rate)
/*     */   {
/* 109 */     this.rate = rate;
/*     */   }
/*     */   
/*     */   public void setQpsBase(int qpsBase)
/*     */   {
/* 114 */     this.qpsBase = qpsBase;
/*     */   }
/*     */   
/*     */   public String getAppName()
/*     */   {
/* 119 */     return this.appName;
/*     */   }
/*     */   
/*     */   public void setAppName(String appName)
/*     */   {
/* 124 */     this.appName = appName;
/*     */   }
/*     */   
/*     */   public String getHost()
/*     */   {
/* 129 */     return this.host;
/*     */   }
/*     */   
/*     */   public void setHost(String host)
/*     */   {
/* 134 */     this.host = host;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/trace/TraceConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */