/*    */ package com.xman.common.rpc.filter.trace;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class TraceData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public String id;
/*    */   public String parentId;
/*    */   public boolean sampled;
/*    */   
/*    */   public TraceData() {}
/*    */   
/*    */   public TraceData(TraceData src)
/*    */   {
/* 18 */     this.id = src.id;
/* 19 */     this.parentId = src.parentId;
/* 20 */     this.sampled = src.sampled;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 24 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 28 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getParentId() {
/* 32 */     return this.parentId;
/*    */   }
/*    */   
/*    */   public void setParentId(String parentId) {
/* 36 */     this.parentId = parentId;
/*    */   }
/*    */   
/*    */   public boolean isSampled() {
/* 40 */     return this.sampled;
/*    */   }
/*    */   
/*    */   public void setSampled(boolean sampled) {
/* 44 */     this.sampled = sampled;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/trace/TraceData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */