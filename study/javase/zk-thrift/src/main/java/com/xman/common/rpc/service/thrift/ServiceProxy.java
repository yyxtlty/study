/*     */ package com.xman.common.rpc.service.thrift;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import com.xman.common.log.LogFactory;
/*     */ import com.xman.common.rpc.FilterProvider;
/*     */ import com.xman.common.rpc.Service;
/*     */ import com.xman.common.rpc.ServiceException;
/*     */ import com.xman.common.rpc.ThriftConfig;
/*     */ import com.xman.common.rpc.filter.Filter;
/*     */ import com.xman.common.rpc.invoke.Invocation;
/*     */ import com.xman.common.rpc.invoke.Invoker;
/*     */ import com.xman.common.rpc.invoke.RpcInvocation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import net.sf.cglib.core.Signature;
/*     */ import net.sf.cglib.proxy.Enhancer;
/*     */ import net.sf.cglib.proxy.MethodInterceptor;
/*     */ import net.sf.cglib.proxy.MethodProxy;
/*     */ import org.apache.thrift.TProcessor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServiceProxy
/*     */   implements MethodInterceptor
/*     */ {
/*  29 */   private static final ILog logger = LogFactory.getLog(ServiceProxy.class);
/*     */   
/*     */ 
/*  32 */   private AtomicBoolean inited = new AtomicBoolean();
/*  33 */   private AtomicLong requestNum = new AtomicLong();
/*  34 */   private AtomicLong rspSpend = new AtomicLong();
/*     */   
/*     */   private Service service;
/*     */   
/*     */   private String ifaceName;
/*     */   
/*     */   private Constructor<?> pconstructor;
/*     */   
/*     */   private List<Filter> filterChain;
/*     */   
/*     */   private Class<?> ifaceClass;
/*     */   
/*     */   private ClassLoader classLoader;
/*     */   
/*     */   private Object ifaceImpl;
/*     */   
/*     */   private boolean register;
/*     */   
/*     */   private Class<?> ifaceInterface;
/*     */   
/*     */ 
/*     */   public ServiceProxy() {}
/*     */   
/*     */ 
/*     */   public ServiceProxy(Class<?> ifaceClass, Object ifaceImpl)
/*     */   {
/*  60 */     this(ifaceClass, ifaceImpl, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ServiceProxy(Class<?> ifaceClass, Object ifaceImpl, boolean register)
/*     */   {
/*  71 */     this.ifaceClass = ifaceClass;
/*  72 */     this.ifaceName = ifaceClass.getName();
/*  73 */     this.ifaceImpl = ifaceImpl;
/*  74 */     this.register = register;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void init(ThriftConfig config)
/*     */   {
/*  82 */     if (!this.inited.compareAndSet(false, true)) {
/*  83 */       return;
/*     */     }
/*     */     
/*  86 */     if (this.ifaceName == null) {
/*  87 */       throw new IllegalArgumentException("config ifaceName == null");
/*     */     }
/*     */     
/*  90 */     if (this.ifaceImpl == null) {
/*  91 */       throw new IllegalArgumentException("config ifaceImpl == null");
/*     */     }
/*     */     
/*  94 */     this.service = new Service(config.host, config.port, this.ifaceName, "providers");
/*     */     
/*  96 */     if (this.classLoader == null) {
/*  97 */       this.classLoader = ServiceProxy.class.getClassLoader();
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 102 */       Class<?> processorClass = this.classLoader.loadClass(this.ifaceName + "$Processor");
/* 103 */       Class<?> ifaceClass = this.classLoader.loadClass(this.ifaceName + "$Iface");
/* 104 */       this.ifaceInterface = ifaceClass;
/* 105 */       this.pconstructor = processorClass.getConstructor(new Class[] { ifaceClass });
/*     */     } catch (ClassNotFoundException|NoSuchMethodException|SecurityException e) {
/* 107 */       throw new ServiceException("init service proxy failure", e);
/*     */     }
/*     */     
/* 110 */     this.filterChain = FilterProvider.configProviderFilters(config);
/*     */   }
/*     */   
/*     */   public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable
/*     */   {
/* 115 */     return invoke(this.ifaceImpl, args, proxy);
/*     */   }
/*     */   
/*     */   Object getProxy()
/*     */   {
/* 120 */     Enhancer enhancer = new Enhancer();
/*     */     
/* 122 */     enhancer.setInterfaces(new Class[] { this.ifaceInterface });
/* 123 */     enhancer.setCallback(this);
/*     */     
/* 125 */     return enhancer.create();
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
/*     */   private Object invoke(Object target, Object[] args, final MethodProxy proxy)
/*     */     throws Throwable
/*     */   {
/* 139 */     logger.debug("invoker provider fitler chain:{}" + this.filterChain);
/*     */     
/* 141 */     Invocation invocation = RpcInvocation.getRpcInvocation(target, args).setClient(false).setMethodName(proxy.getSignature().getName()).setIfaceClass(this.ifaceClass);
/*     */     
/*     */ 
/* 144 */     Invoker finalInvoker = new Invoker()
/*     */     {
/*     */       public Object invoke(Invocation invocation) throws Throwable
/*     */       {
/* 148 */         return proxy.invoke(invocation.getImpl(), invocation.getArguments());
/*     */       }
/* 150 */     };
/* 151 */     Invoker invokerWrapper = FilterProvider.buildInvokerChain(this.filterChain, finalInvoker);
/* 152 */     return invokerWrapper.invoke(invocation);
/*     */   }
/*     */   
/*     */ 
/*     */   public Class<?> getIfaceClass()
/*     */   {
/* 158 */     return this.ifaceClass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIfaceClass(Class<?> ifaceClass)
/*     */   {
/* 167 */     this.ifaceClass = ifaceClass;
/* 168 */     this.ifaceName = ifaceClass.getName();
/*     */   }
/*     */   
/*     */   public Object getIfaceImpl()
/*     */   {
/* 173 */     return this.ifaceImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIfaceImpl(Object ifaceImpl)
/*     */   {
/* 182 */     this.ifaceImpl = ifaceImpl;
/*     */   }
/*     */   
/*     */   public boolean isRegister()
/*     */   {
/* 187 */     return this.register;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRegister(boolean register)
/*     */   {
/* 196 */     this.register = register;
/*     */   }
/*     */   
/*     */   public AtomicLong getRequestNum()
/*     */   {
/* 201 */     return this.requestNum;
/*     */   }
/*     */   
/*     */   public AtomicLong getRspSpend()
/*     */   {
/* 206 */     return this.rspSpend;
/*     */   }
/*     */   
/*     */   void setRspSpend(AtomicLong rspSpend)
/*     */   {
/* 211 */     this.rspSpend = rspSpend;
/*     */   }
/*     */   
/*     */   public Service getService()
/*     */   {
/* 216 */     return this.service;
/*     */   }
/*     */   
/*     */   public Constructor<TProcessor> getPconstructor()
/*     */   {
/* 221 */     return (Constructor<TProcessor>) this.pconstructor;
/*     */   }
/*     */   
/*     */   public String getIfaceName()
/*     */   {
/* 226 */     return this.ifaceName;
/*     */   }
/*     */   
/*     */   public ClassLoader getClassLoader()
/*     */   {
/* 231 */     return this.classLoader;
/*     */   }
/*     */   
/*     */   public ServiceProxy setClassLoader(ClassLoader classLoader)
/*     */   {
/* 236 */     this.classLoader = classLoader;
/* 237 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/service/thrift/ServiceProxy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */