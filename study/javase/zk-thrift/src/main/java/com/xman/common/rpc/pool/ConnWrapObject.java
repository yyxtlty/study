/*     */ package com.xman.common.rpc.pool;
/*     */ 
/*     */ public class ConnWrapObject<T> {
/*     */   private final T object;
/*   5 */   private volatile ObjectState state = ObjectState.IDLE;
/*   6 */   private final long createTime = System.currentTimeMillis();
/*   7 */   private volatile long lastBorrowTime = this.createTime;
/*   8 */   private volatile long lastUseTime = this.createTime;
/*   9 */   private volatile long lastReturnTime = this.createTime;
/*  10 */   private volatile long borrowedCount = 0L;
/*     */   
/*     */   public ConnWrapObject(T object) {
/*  13 */     this.object = object;
/*     */   }
/*     */   
/*     */   public T getObject()
/*     */   {
/*  18 */     return (T)this.object;
/*     */   }
/*     */   
/*     */   public long getCreateTime()
/*     */   {
/*  23 */     return this.createTime;
/*     */   }
/*     */   
/*     */ 
/*     */   public long getActiveTimeMillis()
/*     */   {
/*  29 */     long rTime = this.lastReturnTime;
/*  30 */     long bTime = this.lastBorrowTime;
/*     */     
/*  32 */     if (rTime > bTime) {
/*  33 */       return rTime - bTime;
/*     */     }
/*  35 */     return System.currentTimeMillis() - bTime;
/*     */   }
/*     */   
/*     */ 
/*     */   public long getIdleTimeMillis()
/*     */   {
/*  41 */     long elapsed = System.currentTimeMillis() - this.lastReturnTime;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  46 */     return elapsed >= 0L ? elapsed : 0L;
/*     */   }
/*     */   
/*     */   public long getLastBorrowTime()
/*     */   {
/*  51 */     return this.lastBorrowTime;
/*     */   }
/*     */   
/*     */   public long getLastReturnTime()
/*     */   {
/*  56 */     return this.lastReturnTime;
/*     */   }
/*     */   
/*     */   public long getBorrowedCount()
/*     */   {
/*  61 */     return this.borrowedCount;
/*     */   }
/*     */   
/*     */   public long getLastUsedTime()
/*     */   {
/*  66 */     return this.lastUseTime;
/*     */   }
/*     */   
/*     */   static enum ObjectState {
/*  70 */     IDLE,  ALLOCATED;
/*     */     
/*     */     private ObjectState() {}
/*     */   }
/*     */   
/*  75 */   public synchronized boolean allocate() { if (this.state == ObjectState.IDLE) {
/*  76 */       this.state = ObjectState.ALLOCATED;
/*  77 */       this.lastBorrowTime = System.currentTimeMillis();
/*  78 */       this.lastUseTime = this.lastBorrowTime;
/*  79 */       this.borrowedCount += 1L;
/*  80 */       return true;
/*     */     }
/*     */     
/*  83 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized boolean deallocate()
/*     */   {
/*  88 */     if (this.state == ObjectState.ALLOCATED) {
/*  89 */       this.state = ObjectState.IDLE;
/*  90 */       this.lastReturnTime = System.currentTimeMillis();
/*  91 */       return true;
/*     */     }
/*     */     
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public ObjectState getState()
/*     */   {
/*  99 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(ObjectState state)
/*     */   {
/* 104 */     this.state = state;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/pool/ConnWrapObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */