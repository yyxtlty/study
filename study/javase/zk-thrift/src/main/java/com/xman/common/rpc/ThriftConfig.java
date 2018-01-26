/*     */ package com.xman.common.rpc;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import com.xman.common.rpc.filter.trace.TraceConfig;
/*     */ import com.xman.common.rpc.pool.ConnPoolConfig;
/*     */ import com.xman.common.rpc.registry.support.RegistryConfig;
/*     */ import com.xman.common.rpc.util.JsonUtil;
/*     */ import com.xman.common.rpc.util.NetUtil;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.configuration.ConfigurationException;
/*     */ import org.apache.commons.configuration.PropertiesConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThriftConfig
/*     */   implements Cloneable
/*     */ {
/*  19 */   private static final ILog logger = LogFactory.getLog(ThriftConfig.class);
/*     */   
/*     */   public String host;
/*     */   
/*     */   public int port;
/*     */   
/*     */   public String appName;
/*     */   
/*     */   public TraceConfig traceConfig;
/*     */   
/*     */   public RegistryConfig regcfg;
/*     */   
/*     */   public int rigistryDelay;
/*     */   
/*     */   public int retries;
/*     */   
/*     */   public int socketTimeout;
/*     */   
/*     */   public ConnPoolConfig poolConfig;
/*     */   
/*     */   public int heartbeatPeriod;
/*     */   
/*     */   public int minWorkerThreads;
/*     */   
/*     */   public int maxWorkerThreads;
/*     */   
/*     */   public int tframeMaxLength;
/*     */   
/*     */   public int selectorThreads;
/*     */   
/*     */   public int workerThreads;
/*     */   
/*     */   public int acceptQueueSizePerThread;
/*     */   
/*     */ 
/*     */   public ThriftConfig()
/*     */   {
/*  56 */     this("thrift-rpc.properties");
/*     */   }
/*     */   
/*     */ 
/*     */   public ThriftConfig(InputStream inputStream)
/*     */   {
/*  62 */     PropertiesConfiguration prop = new PropertiesConfiguration();
/*     */     try
/*     */     {
/*  65 */       prop.load(inputStream);
/*  66 */       prop.setDelimiterParsingDisabled(false);
/*     */     } catch (ConfigurationException e) {
/*  68 */       throw new RuntimeException("no thrift-rpc.properties file found", e);
/*     */     }
/*     */     
/*  71 */     initConfig(prop);
/*     */   }
/*     */   
/*     */   public ThriftConfig(String configFile)
/*     */   {
/*     */     PropertiesConfiguration prop;
/*     */     try
/*     */     {
/*  79 */       prop = new PropertiesConfiguration(configFile);
/*  80 */       prop.setDelimiterParsingDisabled(false);
/*     */     } catch (Exception e) {
/*  82 */       throw new RuntimeException("no thrift-rpc.properties file found", e);
/*     */     }
/*     */     
/*  85 */     initConfig(prop);
/*     */   }
/*     */   
/*     */   public void initConfig(PropertiesConfiguration prop)
/*     */   {
/*  90 */     initPublicConfig(prop);
/*     */     
/*  92 */     initClientConfig(prop);
/*     */     
/*  94 */     initServerConfig(prop);
/*     */     
/*  96 */     logger.info("thrift use config: " + JsonUtil.toJson(this));
/*     */   }
/*     */   
/*     */   private void initPublicConfig(PropertiesConfiguration prop)
/*     */   {
/* 101 */     this.host = prop.getString("thrift.host", NetUtil.getLocalHost());
/*     */     
/* 103 */     this.port = prop.getInt("thrift.port", 9090);
/* 104 */     if ((this.port <= 0) || (this.port > 65535)) {
/* 105 */       throw new IllegalArgumentException("invalid port, must between (0,65536)");
/*     */     }
/*     */     
/* 108 */     this.appName = prop.getString("thrift.appname", "");
/*     */     
/* 110 */     this.regcfg = new RegistryConfig(prop);
/*     */     
/* 112 */     this.traceConfig = new TraceConfig(prop);
/* 113 */     this.traceConfig.setAppName(this.appName);
/* 114 */     this.traceConfig.setHost(this.host);
/*     */   }
/*     */   
/*     */   private void initClientConfig(PropertiesConfiguration prop)
/*     */   {
/* 119 */     this.poolConfig = new ConnPoolConfig(prop);
/*     */     
/* 121 */     this.retries = prop.getInt("thrift.client.retries", 2);
/* 122 */     if (this.retries < 0) {
/* 123 */       throw new IllegalArgumentException("retries must >= 0");
/*     */     }
/*     */     
/* 126 */     this.socketTimeout = prop.getInt("thrift.client.timeout", 5000);
/* 127 */     if (this.socketTimeout <= 0) {
/* 128 */       throw new IllegalArgumentException("socketTimeout must > 0");
/*     */     }
/*     */     
/* 131 */     this.heartbeatPeriod = prop.getInt("thrift.client.heartbeat.period", 5000);
/* 132 */     if (this.heartbeatPeriod < 0) {
/* 133 */       throw new IllegalArgumentException("heartbeatPeriod must >= 0");
/*     */     }
/*     */   }
/*     */   
/*     */   private void initServerConfig(PropertiesConfiguration prop)
/*     */   {
/* 139 */     this.minWorkerThreads = prop.getInt("thrift.threadpool.minworkerthreads", 1);
/* 140 */     if (this.minWorkerThreads <= 0) {
/* 141 */       throw new IllegalArgumentException("minWorkerThreads must be > 0");
/*     */     }
/*     */     
/* 144 */     this.maxWorkerThreads = prop.getInt("thrift.threadpool.maxworkerthreads", 5);
/* 145 */     if (this.minWorkerThreads > this.maxWorkerThreads) {
/* 146 */       throw new IllegalArgumentException("maxWorkerThreads must be greater than maxWorkerThreads");
/*     */     }
/*     */     
/* 149 */     this.tframeMaxLength = prop.getInt("thrift.tframe.maxlength", 16384000);
/* 150 */     if (this.tframeMaxLength <= 0) {
/* 151 */       throw new IllegalArgumentException("tframeMaxLength must be > 0");
/*     */     }
/*     */     
/* 154 */     this.selectorThreads = prop.getInt("thrift.threadselector.selectorthreads", 2);
/* 155 */     if (this.selectorThreads <= 0) {
/* 156 */       throw new IllegalArgumentException("selectorThreads must be > 0");
/*     */     }
/*     */     
/* 159 */     this.workerThreads = prop.getInt("thrift.threadselector.workerthreads", 5);
/* 160 */     if (this.workerThreads <= 0) {
/* 161 */       throw new IllegalArgumentException("maxWorkerThreads must be > 0");
/*     */     }
/*     */     
/* 164 */     this.acceptQueueSizePerThread = prop.getInt("thrift.threadselector.queuesize", 20);
/* 165 */     if (this.acceptQueueSizePerThread <= 0) {
/* 166 */       throw new IllegalArgumentException("maxWorkerThreads must be > 0");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/ThriftConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */