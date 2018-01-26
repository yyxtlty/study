/*     */ package com.xman.common.rpc.filter.trace;
/*     */ 
/*     */ import com.alibaba.mtc.MtContextThreadLocal;
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import com.xman.common.rpc.trace.helper.Sampler;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.apache.commons.lang.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TraceUtils
/*     */ {
/*  19 */   private static final ILog logger = LogFactory.getLog(TraceUtils.class);
/*     */   
/*  21 */   private static MtContextThreadLocal<Span> parentSpanLocal = new MtContextThreadLocal();
/*     */   
/*  23 */   private static ThreadLocal<Span> subSpanLocal = new ThreadLocal();
/*     */   
/*  25 */   private static MtContextThreadLocal<AtomicLong> autoIncr = new MtContextThreadLocal();
/*     */   
/*     */ 
/*     */ 
/*     */   public static String getTracerId()
/*     */   {
/*  31 */     Span pspan = getParentSpan();
/*  32 */     if (pspan != null) {
/*  33 */       return pspan.getTraceId();
/*     */     }
/*  35 */     Span subSpan = getSubSpan();
/*  36 */     if (subSpan != null) {
/*  37 */       return subSpan.getTraceId();
/*     */     }
/*  39 */     return null;
/*     */   }
/*     */   
/*     */   public static void setParentSpan(Span span) {
/*  43 */     parentSpanLocal.set(span);
/*  44 */     autoIncr.set(new AtomicLong(0L));
/*     */   }
/*     */   
/*     */   public static Span getParentSpan() {
/*  48 */     return (Span)parentSpanLocal.get();
/*     */   }
/*     */   
/*     */   public static void removeParentSpan() {
/*  52 */     Span pSpan = (Span)parentSpanLocal.get();
/*  53 */     if (pSpan != null) {
/*  54 */       parentSpanLocal.remove();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setSubSpan(Span span) {
/*  59 */     subSpanLocal.set(span);
/*     */   }
/*     */   
/*     */   public static Span getSubSpan() {
/*  63 */     return (Span)subSpanLocal.get();
/*     */   }
/*     */   
/*     */   public static void removeSubSpan() {
/*  67 */     subSpanLocal.remove();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Span newRootSpan(String spanName, Sampler sampler)
/*     */   {
/*  78 */     Span span = new Span();
/*  79 */     span.setId("1");
/*  80 */     logger.debug("root span flag:{}");
/*     */     
/*  82 */     span.setName(spanName);
/*  83 */     span.setParentId(span.getId());
/*  84 */     span.setSampled(sampler.isSample());
/*  85 */     return span;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Span newSubSpan(Span parentSpan, String spanName, Sampler sampler)
/*     */   {
/*  96 */     Validate.notNull(parentSpan, "ParentSpan must not null");
/*  97 */     Span span = new Span();
/*  98 */     AtomicLong atomicLong = (AtomicLong)autoIncr.get();
/*  99 */     String number = Thread.currentThread().getId() + "";
/* 100 */     span.setId(parentSpan.getId() + "." + number);
/* 101 */     span.setTraceId(parentSpan.getTraceId());
/* 102 */     span.setName(spanName);
/* 103 */     span.setParentId(parentSpan.getId());
/* 104 */     span.setSampled(parentSpan.isSampled());
/* 105 */     return span;
/*     */   }
/*     */   
/*     */   public static TraceData toTraceData(Span span) {
/* 109 */     Validate.notNull(span, "Span must not null");
/* 110 */     TraceData traceData = new TraceData();
/* 111 */     traceData.setId(span.getId());
/* 112 */     traceData.setParentId(span.getParentId());
/* 113 */     traceData.setSampled(span.isSampled());
/* 114 */     return traceData;
/*     */   }
/*     */   
/*     */   public static Span toSpan(TraceData traceData, String appName) {
/* 118 */     Validate.notNull(traceData, "TraceData must not null");
/* 119 */     Span span = new Span();
/* 120 */     span.setId(traceData.getId());
/*     */     
/* 122 */     span.setSampled(traceData.isSampled());
/* 123 */     span.setParentId(traceData.getParentId());
/* 124 */     span.setName(appName);
/* 125 */     return span;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/trace/TraceUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */