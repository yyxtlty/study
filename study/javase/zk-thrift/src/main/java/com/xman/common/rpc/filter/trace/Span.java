/*     */ package com.xman.common.rpc.filter.trace;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Span
/*     */ {
/*     */   public String traceId;
/*     */   public String name;
/*     */   public String id;
/*     */   public String parentId;
/*     */   public List<Annotation> annotations;
/*     */   public boolean sampled;
/*     */   public long duration;
/*     */   public String appName;
/*     */   public String host;
/*     */   
/*     */   public Span addAnnotation(Annotation annotation)
/*     */   {
/*  20 */     if (this.annotations == null) {
/*  21 */       this.annotations = new ArrayList();
/*     */     }
/*     */     
/*  24 */     this.annotations.add(annotation);
/*  25 */     return this;
/*     */   }
/*     */   
/*     */   public String getTraceId()
/*     */   {
/*  30 */     return this.traceId;
/*     */   }
/*     */   
/*     */   public Span setTraceId(String traceId) {
/*  34 */     this.traceId = traceId;
/*  35 */     return this;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  39 */     return this.name;
/*     */   }
/*     */   
/*     */   public Span setName(String name) {
/*  43 */     this.name = name;
/*  44 */     return this;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  48 */     return this.id;
/*     */   }
/*     */   
/*     */   public Span setId(String id) {
/*  52 */     this.id = id;
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public String getParentId() {
/*  57 */     return this.parentId;
/*     */   }
/*     */   
/*     */   public Span setParentId(String parentId) {
/*  61 */     this.parentId = parentId;
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public List<Annotation> getAnnotations() {
/*  66 */     return this.annotations;
/*     */   }
/*     */   
/*     */   public Span setAnnotations(List<Annotation> annotations) {
/*  70 */     this.annotations = annotations;
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isSampled()
/*     */   {
/*  76 */     return this.sampled;
/*     */   }
/*     */   
/*     */   public Span setSampled(boolean sampled) {
/*  80 */     this.sampled = sampled;
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public long getDuration() {
/*  85 */     return this.duration;
/*     */   }
/*     */   
/*     */   public Span setDuration(long duration) {
/*  89 */     this.duration = duration;
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public String getAppName() {
/*  94 */     return this.appName;
/*     */   }
/*     */   
/*     */   public Span setAppName(String appName) {
/*  98 */     this.appName = appName;
/*  99 */     return this;
/*     */   }
/*     */   
/*     */   public String getHost()
/*     */   {
/* 104 */     return this.host;
/*     */   }
/*     */   
/*     */   public Span setHost(String host)
/*     */   {
/* 109 */     this.host = host;
/* 110 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/trace/Span.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */