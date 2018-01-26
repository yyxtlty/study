/*     */ package com.xman.common.rpc.filter.trace;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import com.xman.common.rpc.filter.Filter;
/*     */ import com.xman.common.rpc.invoke.Invocation;
/*     */ import com.xman.common.rpc.invoke.Invoker;
/*     */ import com.xman.common.rpc.invoke.RpcContext;
/*     */ import com.xman.common.rpc.trace.helper.QpsAwareSampler;
/*     */ import com.xman.common.rpc.trace.helper.RateSampler;
/*     */ import com.xman.common.rpc.trace.helper.Sampler;
/*     */ import com.xman.common.rpc.util.JsonUtil;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceFilter
/*     */   implements Filter
/*     */ {
/*  22 */   static final ILog logger = LogFactory.getLog(TraceFilter.class);
/*     */   
/*  24 */   private static final Logger rawLogger = LoggerFactory.getLogger("trace-log");
/*     */   private final TraceConfig config;
/*     */   private final Sampler sampler;
/*     */   private final boolean client;
/*     */   
/*     */   public TraceFilter(TraceConfig traceConfig, boolean client)
/*     */   {
/*  31 */     Sampler sampler = new QpsAwareSampler();
/*  32 */     if ("qps".equals(traceConfig.getSampler())) {
/*  33 */       int qps = traceConfig.getQpsBase();
/*  34 */       sampler = new QpsAwareSampler(qps);
/*     */     } else {
/*  36 */       double rate = traceConfig.getRate();
/*  37 */       sampler = new RateSampler(rate);
/*     */     }
/*     */     
/*  40 */     this.sampler = sampler;
/*  41 */     this.config = traceConfig;
/*  42 */     this.client = client;
/*     */   }
/*     */   
/*     */   public Object invoke(Invoker invoker, Invocation invocation)
/*     */     throws Throwable
/*     */   {
/*  48 */     logger.debug("invoke trace filter, client={}" + this.client);
/*  49 */     RpcContext ctx = RpcContext.getContext();
/*  50 */     String spanName = invocation.getIface().getName() + "." + invocation.getMethodName();
/*     */     try { TraceData traceData;
/*  52 */       if (this.client) {
/*  53 */         Span toSendSpan = clientSend(spanName);
/*  54 */         ctx.addAttachment("rpcontext.attach.tracedata", TraceUtils.toTraceData(toSendSpan));
/*     */       } else {
/*  56 */         traceData = (TraceData)ctx.getAttachment("rpcontext.attach.tracedata");
/*  57 */         if (traceData != null) {
/*  58 */           serverRecv(TraceUtils.toSpan(traceData, spanName));
/*     */         }
/*     */       }
/*  61 */       return invoker.invoke(invocation);
/*     */     } finally {
/*  63 */       if (this.client) {
/*  64 */         clientRecv();
/*     */       } else {
/*  66 */         serverSend();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void serverSend()
/*     */   {
/*  73 */     Span pspan = TraceUtils.getParentSpan();
/*  74 */     if ((pspan == null) || (!pspan.isSampled())) {
/*  75 */       TraceUtils.removeParentSpan();
/*  76 */       return;
/*     */     }
/*  78 */     List<Annotation> _ants = pspan.getAnnotations();
/*  79 */     long srTm = 0L;
/*  80 */     for (Annotation _ant : _ants) {
/*  81 */       if ("sr".equals(_ant.getValue())) {
/*  82 */         srTm = _ant.getTimestamp();
/*  83 */         break;
/*     */       }
/*     */     }
/*  86 */     long ssTm = System.currentTimeMillis();
/*  87 */     pspan.setDuration(ssTm - srTm);
/*  88 */     Annotation ant = new Annotation();
/*  89 */     ant.setTimestamp(ssTm);
/*  90 */     ant.setValue("ss");
/*  91 */     pspan.addAnnotation(ant);
/*  92 */     pspan.setHost(this.config.getHost());
/*  93 */     pspan.setAppName(this.config.getAppName());
/*  94 */     TraceUtils.removeParentSpan();
/*  95 */     rawLogger.info(JsonUtil.toJson(pspan));
/*     */   }
/*     */   
/*     */   private void serverRecv(Span recvSpan)
/*     */   {
/* 100 */     TraceUtils.setParentSpan(recvSpan);
/* 101 */     if (recvSpan == null) {
/* 102 */       return;
/*     */     }
/* 104 */     if (recvSpan.isSampled()) {
/* 105 */       Annotation ant = new Annotation();
/* 106 */       ant.setTimestamp(System.currentTimeMillis());
/* 107 */       ant.setValue("sr");
/* 108 */       recvSpan.addAnnotation(ant);
/*     */     }
/*     */   }
/*     */   
/*     */   private Span clientSend(String spanName)
/*     */   {
/* 114 */     Span pspan = TraceUtils.getParentSpan();
/* 115 */     Span toSendSpan = new Span();
/* 116 */     if (pspan == null) {
/* 117 */       toSendSpan = TraceUtils.newRootSpan(spanName, this.sampler);
/*     */     } else {
/* 119 */       toSendSpan = TraceUtils.newSubSpan(pspan, spanName, this.sampler);
/*     */     }
/* 121 */     if (toSendSpan.isSampled()) {
/* 122 */       Annotation ant = new Annotation();
/* 123 */       ant.setTimestamp(System.currentTimeMillis());
/* 124 */       ant.setValue("cs");
/* 125 */       toSendSpan.addAnnotation(ant);
/*     */     }
/* 127 */     TraceUtils.setSubSpan(toSendSpan);
/* 128 */     return toSendSpan;
/*     */   }
/*     */   
/*     */   private void clientRecv()
/*     */   {
/* 133 */     Span span = TraceUtils.getSubSpan();
/* 134 */     if ((span == null) || (!span.isSampled())) {
/* 135 */       TraceUtils.removeSubSpan();
/* 136 */       return;
/*     */     }
/* 138 */     List<Annotation> _ants = span.getAnnotations();
/* 139 */     long csTm = 0L;
/* 140 */     for (Annotation _ant : _ants) {
/* 141 */       if ("cs".equals(_ant.getValue())) {
/* 142 */         csTm = _ant.getTimestamp();
/* 143 */         break;
/*     */       }
/*     */     }
/* 146 */     long crTm = System.currentTimeMillis();
/* 147 */     span.setDuration(crTm - csTm);
/* 148 */     Annotation ant = new Annotation();
/* 149 */     ant.setTimestamp(crTm);
/* 150 */     ant.setValue("cr");
/* 151 */     span.addAnnotation(ant);
/* 152 */     span.setHost(this.config.getHost());
/* 153 */     span.setAppName(this.config.getAppName());
/* 154 */     TraceUtils.removeSubSpan();
/*     */     
/* 156 */     rawLogger.info(JsonUtil.toJson(span));
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 162 */     return "traceFilter";
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/trace/TraceFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */