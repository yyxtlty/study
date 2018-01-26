/*     */ package com.xman.common.rpc.common;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ public class ConcurrentHashSet<E>
/*     */   extends AbstractSet<E> implements Set<E>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8672117787651310382L;
/*  13 */   private static final Object PRESENT = new Object();
/*     */   private final ConcurrentHashMap<E, Object> map;
/*     */   
/*     */   public ConcurrentHashSet()
/*     */   {
/*  18 */     this.map = new ConcurrentHashMap();
/*     */   }
/*     */   
/*     */   public ConcurrentHashSet(int initialCapacity) {
/*  22 */     this.map = new ConcurrentHashMap(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<E> iterator()
/*     */   {
/*  33 */     return this.map.keySet().iterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  42 */     return this.map.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  51 */     return this.map.isEmpty();
/*     */   }
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
/*     */   public boolean contains(Object o)
/*     */   {
/*  65 */     return this.map.containsKey(o);
/*     */   }
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
/*     */   public boolean add(E e)
/*     */   {
/*  82 */     return this.map.put(e, PRESENT) == null;
/*     */   }
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
/*     */   public boolean remove(Object o)
/*     */   {
/*  98 */     return this.map.remove(o) == PRESENT;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 106 */     this.map.clear();
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/common/ConcurrentHashSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */