/*    */ package com.xman.common.rpc.filter.trace;
/*    */ 
/*    */ public class Annotation
/*    */ {
/*    */   public long timestamp;
/*    */   public String value;
/*    */   
/*    */   public long getTimestamp() {
/*  9 */     return this.timestamp;
/*    */   }
/*    */   
/*    */   public void setTimestamp(long timestamp)
/*    */   {
/* 14 */     this.timestamp = timestamp;
/*    */   }
/*    */   
/*    */   public String getValue()
/*    */   {
/* 19 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value)
/*    */   {
/* 24 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/filter/trace/Annotation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */