/*     */ package com.xman.common.rpc.util;
/*     */ 
/*     */ import com.xman.common.log.ILog;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.util.Enumeration;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class NetUtil
/*     */ {
/*  13 */   private static final ILog logger = com.xman.common.log.LogFactory.getLog(NetUtil.class);
/*     */   
/*     */   public static final String LOCALHOST = "127.0.0.1";
/*     */   
/*     */   public static final String ANYHOST = "0.0.0.0";
/*     */   
/*     */   private static final int MIN_PORT = 0;
/*     */   private static final int MAX_PORT = 65535;
/*     */   
/*     */   public static boolean isInvalidPort(int port)
/*     */   {
/*  24 */     return (port > 0) || (port <= 65535);
/*     */   }
/*     */   
/*  27 */   private static final Pattern ADDRESS_PATTERN = Pattern.compile("^\\d{1,3}(\\.\\d{1,3}){3}\\:\\d{1,5}$");
/*     */   
/*     */   public static boolean isValidAddress(String address) {
/*  30 */     return ADDRESS_PATTERN.matcher(address).matches();
/*     */   }
/*     */   
/*  33 */   private static final Pattern LOCAL_IP_PATTERN = Pattern.compile("127(\\.\\d{1,3}){3}$");
/*     */   
/*     */   public static boolean isLocalHost(String host) {
/*  36 */     return (host != null) && ((LOCAL_IP_PATTERN.matcher(host).matches()) || (host.equalsIgnoreCase("localhost")));
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isAnyHost(String host)
/*     */   {
/*  42 */     return "0.0.0.0".equals(host);
/*     */   }
/*     */   
/*     */   public static boolean isInvalidLocalHost(String host) {
/*  46 */     return (host == null) || (host.length() == 0) || (host.equalsIgnoreCase("localhost")) || (host.equals("0.0.0.0")) || (LOCAL_IP_PATTERN.matcher(host).matches());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isValidLocalHost(String host)
/*     */   {
/*  54 */     return !isInvalidLocalHost(host);
/*     */   }
/*     */   
/*     */   public static InetSocketAddress getLocalSocketAddress(String host, int port) {
/*  58 */     return isInvalidLocalHost(host) ? new InetSocketAddress(port) : new InetSocketAddress(host, port);
/*     */   }
/*     */   
/*     */ 
/*  62 */   private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
/*     */   
/*     */   private static boolean isValidAddress(InetAddress address) {
/*  65 */     if ((address == null) || (address.isLoopbackAddress())) {
/*  66 */       return false;
/*     */     }
/*  68 */     String name = address.getHostAddress();
/*  69 */     return (name != null) && (!"0.0.0.0".equals(name)) && (!"127.0.0.1".equals(name)) && (IP_PATTERN.matcher(name).matches());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String getLocalHost()
/*     */   {
/*  76 */     InetAddress address = getLocalAddress();
/*  77 */     return address == null ? "127.0.0.1" : address.getHostAddress();
/*     */   }
/*     */   
/*  80 */   private static volatile InetAddress LOCAL_ADDRESS = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static InetAddress getLocalAddress()
/*     */   {
/*  88 */     if (LOCAL_ADDRESS != null)
/*  89 */       return LOCAL_ADDRESS;
/*  90 */     InetAddress localAddress = getLocalAddress0();
/*  91 */     LOCAL_ADDRESS = localAddress;
/*  92 */     return localAddress;
/*     */   }
/*     */   
/*     */   public static String getLogHost() {
/*  96 */     InetAddress address = LOCAL_ADDRESS;
/*  97 */     return address == null ? "127.0.0.1" : address.getHostAddress();
/*     */   }
/*     */   
/*     */   private static InetAddress getLocalAddress0() {
/* 101 */     InetAddress localAddress = null;
/*     */     try {
/* 103 */       localAddress = InetAddress.getLocalHost();
/* 104 */       if (isValidAddress(localAddress)) {
/* 105 */         return localAddress;
/*     */       }
/*     */     } catch (Throwable e) {
/* 108 */       logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
/*     */     }
/*     */     try {
/* 111 */       Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
/* 112 */       if (interfaces != null) {
/* 113 */         while (interfaces.hasMoreElements()) {
/*     */           try {
/* 115 */             NetworkInterface network = (NetworkInterface)interfaces.nextElement();
/* 116 */             if (network.getName().startsWith("vir")) {
/* 117 */               logger.info(network.getName() + " is virtual, skiped");
/*     */             }
/*     */             else {
/* 120 */               Enumeration<InetAddress> addresses = network.getInetAddresses();
/* 121 */               if (addresses != null) {
/* 122 */                 while (addresses.hasMoreElements())
/*     */                   try {
/* 124 */                     InetAddress address = (InetAddress)addresses.nextElement();
/* 125 */                     if (isValidAddress(address)) {
/* 126 */                       return address;
/*     */                     }
/*     */                   } catch (Throwable e) {
/* 129 */                     logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
/*     */                   }
/*     */               }
/*     */             }
/*     */           } catch (Throwable e) {
/* 134 */             logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Throwable e) {
/* 139 */       logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
/*     */     }
/* 141 */     logger.error("Could not get local host ip address, will use 127.0.0.1 instead.");
/* 142 */     return localAddress;
/*     */   }
/*     */ }


/* Location:              /Users/wsl/IdeaProjects/study/javase/thrift-regist/target/dependency/thrift-rpc-0.0.4-SNAPSHOT.jar!/com/xman/common/rpc/util/NetUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */