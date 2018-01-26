/*     */ package com.xman.common.rpc.pool;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import com.xman.common.rpc.Server;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import org.apache.commons.pool2.PoolUtils;
/*     */ 
/*     */ 
/*     */ public class ConnPool<T>
/*     */ {
/*  15 */   private static final ILog logger = LogFactory.getLog(ConnPool.class);
/*     */   
/*     */   private final Server _server;
/*     */   
/*     */   private ConnPoolConfig _config;
/*     */   
/*  21 */   private ConcurrentLinkedQueue<ConnWrapObject<T>> _pool = null;
/*  22 */   private ConnFactory<T> _factory = null;
/*  23 */   private volatile boolean closed = false;
/*     */   
/*     */   public ConnPool(ConnFactory<T> factory, Server server, ConnPoolConfig config)
/*     */   {
/*  27 */     if (factory == null) {
/*  28 */       throw new IllegalArgumentException("factory may not be null");
/*     */     }
/*     */     
/*  31 */     if (server == null) {
/*  32 */       throw new IllegalArgumentException("server may not be null");
/*     */     }
/*     */     
/*  35 */     this._server = server;
/*  36 */     this._factory = factory;
/*  37 */     this._config = config;
/*     */     
/*  39 */     this._pool = new ConcurrentLinkedQueue();
/*     */   }
/*     */   
/*     */   private ConnWrapObject<T> wrap(T obj)
/*     */   {
/*  44 */     return new ConnWrapObject(obj);
/*     */   }
/*     */   
/*     */   public ConnWrapObject<T> borrowObject() throws Exception
/*     */   {
/*  49 */     assertOpen();
/*     */     
/*  51 */     ConnWrapObject<T> p = null;
/*     */     
/*     */ 
/*     */ 
/*  55 */     while (p == null) {
/*  56 */       boolean create = false;
/*  57 */       p = (ConnWrapObject)this._pool.poll();
/*  58 */       if (p == null) {
/*  59 */         p = wrap(this._factory.makeObject(this._server));
/*  60 */         if (p != null) {
/*  61 */           create = true;
/*     */         }
/*     */       }
/*     */       
/*  65 */       if ((p != null) && 
/*  66 */         (!p.allocate())) {
/*  67 */         p = null;
/*     */       }
/*     */       
/*  70 */       if (p != null) {
/*     */         try {
/*  72 */           this._factory.activateObject(p.getObject());
/*     */         } catch (Exception e) {
/*     */           try {
/*  75 */             destroy(p);
/*     */           } catch (Exception e1) {
/*  77 */             logger.error("destroy object error: ", e);
/*     */           }
/*     */           
/*  80 */           p = null;
/*  81 */           if (create) {
/*  82 */             NoSuchElementException nsee = new NoSuchElementException("Unable to activate object");
/*     */             
/*  84 */             nsee.initCause(e);
/*  85 */             throw nsee;
/*     */           }
/*     */         }
/*  88 */         if ((p != null) && ((isTestOnBorrow()) || ((create) && (isTestOnCreate()))))
/*     */         {
/*  90 */           boolean validate = false;
/*  91 */           Throwable validationThrowable = null;
/*     */           try {
/*  93 */             validate = this._factory.validateObject(p.getObject());
/*     */           } catch (Throwable t) {
/*  95 */             PoolUtils.checkRethrow(t);
/*  96 */             validationThrowable = t;
/*     */           }
/*  98 */           if (!validate) {
/*     */             try {
/* 100 */               destroy(p);
/*     */             } catch (Exception e) {
/* 102 */               logger.error("destroy object error: ", e);
/*     */             }
/*     */             
/* 105 */             p = null;
/* 106 */             if (create) {
/* 107 */               NoSuchElementException nsee = new NoSuchElementException("Unable to validate object");
/*     */               
/* 109 */               nsee.initCause(validationThrowable);
/* 110 */               throw nsee;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 117 */     return p;
/*     */   }
/*     */   
/*     */   public void returnObject(ConnWrapObject<T> obj) throws Exception
/*     */   {
/*     */     try {
/* 123 */       addObjectToPool(obj, true);
/*     */     } catch (Exception e) {
/* 125 */       if (this._factory != null) {
/*     */         try {
/* 127 */           this._factory.destroyObject(obj.getObject());
/*     */         } catch (Exception e2) {
/* 129 */           logger.error("destroy object error: ", e2);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void invalidateObject(ConnWrapObject<T> obj)
/*     */     throws Exception
/*     */   {
/* 138 */     if (this._factory != null) {
/* 139 */       this._factory.destroyObject(obj.getObject());
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void clear()
/*     */   {
/* 145 */     List<ConnWrapObject<T>> toDestroy = new ArrayList(this._pool);
/*     */     
/*     */ 
/* 148 */     this._pool.clear();
/*     */     
/* 150 */     for (int i = 0; i < toDestroy.size(); i++) {
/* 151 */       destroy((ConnWrapObject)toDestroy.get(i));
/*     */     }
/*     */   }
/*     */   
/*     */   private void destroy(ConnWrapObject<T> p)
/*     */   {
/*     */     try {
/* 158 */       this._factory.destroyObject(p.getObject());
/*     */     } catch (Exception e) {
/* 160 */       logger.error("destroy object error: ", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void addObjectToPool(ConnWrapObject<T> t, boolean decrementNumActive)
/*     */     throws Exception
/*     */   {
/* 168 */     T obj = t.getObject();
/* 169 */     boolean success = true;
/* 170 */     if ((isTestOnReturn()) && (!this._factory.validateObject(obj))) {
/* 171 */       success = false;
/*     */     } else {
/* 173 */       this._factory.passivateObject(obj);
/*     */     }
/*     */     
/* 176 */     boolean shouldDestroy = !success;
/* 177 */     int maxIdle = getMaxIdle();
/*     */     
/*     */ 
/* 180 */     if (isClosed()) {
/* 181 */       shouldDestroy = true;
/*     */     }
/* 183 */     else if ((maxIdle >= 0) && (this._pool.size() >= maxIdle)) {
/* 184 */       shouldDestroy = true;
/* 185 */     } else if (success) {
/* 186 */       if (!t.deallocate()) {
/* 187 */         throw new IllegalStateException("Object has already been returned to this pool or is invalid");
/*     */       }
/*     */       
/* 190 */       this._pool.add(t);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 195 */     if (shouldDestroy) {
/*     */       try {
/* 197 */         this._factory.destroyObject(obj);
/*     */       } catch (Exception e) {
/* 199 */         logger.error("destroy object error: ", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void close()
/*     */   {
/* 207 */     if (isClosed()) {
/* 208 */       return;
/*     */     }
/*     */     
/* 211 */     this.closed = true;
/* 212 */     clear();
/*     */   }
/*     */   
/*     */   public void addObject() throws Exception
/*     */   {
/* 217 */     assertOpen();
/* 218 */     if (this._factory == null) {
/* 219 */       throw new IllegalStateException("Cannot add objects without a factory.");
/*     */     }
/*     */     
/* 222 */     T obj = this._factory.makeObject(this._server);
/*     */     try {
/* 224 */       assertOpen();
/* 225 */       addObjectToPool(wrap(obj), false);
/*     */     } catch (IllegalStateException ex) {
/*     */       try {
/* 228 */         this._factory.destroyObject(obj);
/*     */       } catch (Exception ex2) {
/* 230 */         logger.error("destroy object error: ", ex2);
/*     */       }
/*     */       
/* 233 */       throw ex;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isClosed()
/*     */   {
/* 239 */     return this.closed;
/*     */   }
/*     */   
/*     */   private void assertOpen() throws IllegalStateException
/*     */   {
/* 244 */     if (isClosed()) {
/* 245 */       throw new IllegalStateException("Pool not open");
/*     */     }
/*     */   }
/*     */   
/*     */   public int getNumIdle()
/*     */   {
/* 251 */     return this._pool.size();
/*     */   }
/*     */   
/*     */   public Server getRpcServer()
/*     */   {
/* 256 */     return this._server;
/*     */   }
/*     */   
/*     */   public int getMaxIdle()
/*     */   {
/* 261 */     return this._config.maxIdle;
/*     */   }
/*     */   
/*     */   public boolean isTestOnCreate()
/*     */   {
/* 266 */     return this._config.testOnCreate;
/*     */   }
/*     */   
/*     */   public boolean isTestOnBorrow()
/*     */   {
/* 271 */     return this._config.testOnBorrow;
/*     */   }
/*     */   
/*     */   public boolean isTestOnReturn()
/*     */   {
/* 276 */     return this._config.testOnReturn;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/pool/ConnPool.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */