/*     */ package io.netty.channel.socket.nio;
/*     */ 
/*     */ import io.netty.channel.ChannelConfig;
/*     */ import io.netty.channel.ChannelException;
/*     */ import io.netty.channel.socket.DatagramChannelConfig;
/*     */ import io.netty.channel.socket.DefaultDatagramChannelConfig;
/*     */ import io.netty.util.internal.PlatformDependent;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.InetAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.SocketException;
/*     */ import java.nio.channels.DatagramChannel;
/*     */ import java.nio.channels.NetworkChannel;
/*     */ import java.util.Enumeration;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NioDatagramChannelConfig
/*     */   extends DefaultDatagramChannelConfig
/*     */ {
/*     */   private static final Object IP_MULTICAST_TTL;
/*     */   private static final Object IP_MULTICAST_IF;
/*     */   private static final Object IP_MULTICAST_LOOP;
/*     */   private static final Method GET_OPTION;
/*     */   private static final Method SET_OPTION;
/*     */   private final DatagramChannel javaChannel;
/*     */   
/*     */   static {
/*  43 */     ClassLoader classLoader = PlatformDependent.getClassLoader(DatagramChannel.class);
/*  44 */     Class<?> socketOptionType = null;
/*     */     try {
/*  46 */       socketOptionType = Class.forName("java.net.SocketOption", true, classLoader);
/*  47 */     } catch (Exception e) {}
/*     */ 
/*     */     
/*  50 */     Class<?> stdSocketOptionType = null;
/*     */     try {
/*  52 */       stdSocketOptionType = Class.forName("java.net.StandardSocketOptions", true, classLoader);
/*  53 */     } catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  57 */     Object ipMulticastTtl = null;
/*  58 */     Object ipMulticastIf = null;
/*  59 */     Object ipMulticastLoop = null;
/*  60 */     Method getOption = null;
/*  61 */     Method setOption = null;
/*  62 */     if (socketOptionType != null) {
/*     */       try {
/*  64 */         ipMulticastTtl = stdSocketOptionType.getDeclaredField("IP_MULTICAST_TTL").get(null);
/*  65 */       } catch (Exception e) {
/*  66 */         throw new Error("cannot locate the IP_MULTICAST_TTL field", e);
/*     */       } 
/*     */       
/*     */       try {
/*  70 */         ipMulticastIf = stdSocketOptionType.getDeclaredField("IP_MULTICAST_IF").get(null);
/*  71 */       } catch (Exception e) {
/*  72 */         throw new Error("cannot locate the IP_MULTICAST_IF field", e);
/*     */       } 
/*     */       
/*     */       try {
/*  76 */         ipMulticastLoop = stdSocketOptionType.getDeclaredField("IP_MULTICAST_LOOP").get(null);
/*  77 */       } catch (Exception e) {
/*  78 */         throw new Error("cannot locate the IP_MULTICAST_LOOP field", e);
/*     */       } 
/*     */       
/*     */       try {
/*  82 */         getOption = NetworkChannel.class.getDeclaredMethod("getOption", new Class[] { socketOptionType });
/*  83 */       } catch (Exception e) {
/*  84 */         throw new Error("cannot locate the getOption() method", e);
/*     */       } 
/*     */       
/*     */       try {
/*  88 */         setOption = NetworkChannel.class.getDeclaredMethod("setOption", new Class[] { socketOptionType, Object.class });
/*  89 */       } catch (Exception e) {
/*  90 */         throw new Error("cannot locate the setOption() method", e);
/*     */       } 
/*     */     } 
/*  93 */     IP_MULTICAST_TTL = ipMulticastTtl;
/*  94 */     IP_MULTICAST_IF = ipMulticastIf;
/*  95 */     IP_MULTICAST_LOOP = ipMulticastLoop;
/*  96 */     GET_OPTION = getOption;
/*  97 */     SET_OPTION = setOption;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   NioDatagramChannelConfig(NioDatagramChannel channel, DatagramChannel javaChannel) {
/* 103 */     super(channel, javaChannel.socket());
/* 104 */     this.javaChannel = javaChannel;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeToLive() {
/* 109 */     return ((Integer)getOption0(IP_MULTICAST_TTL)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public DatagramChannelConfig setTimeToLive(int ttl) {
/* 114 */     setOption0(IP_MULTICAST_TTL, Integer.valueOf(ttl));
/* 115 */     return (DatagramChannelConfig)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public InetAddress getInterface() {
/* 120 */     NetworkInterface inf = getNetworkInterface();
/* 121 */     if (inf == null) {
/* 122 */       return null;
/*     */     }
/* 124 */     Enumeration<InetAddress> addresses = inf.getInetAddresses();
/* 125 */     if (addresses.hasMoreElements()) {
/* 126 */       return addresses.nextElement();
/*     */     }
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DatagramChannelConfig setInterface(InetAddress interfaceAddress) {
/*     */     try {
/* 135 */       setNetworkInterface(NetworkInterface.getByInetAddress(interfaceAddress));
/* 136 */     } catch (SocketException e) {
/* 137 */       throw new ChannelException(e);
/*     */     } 
/* 139 */     return (DatagramChannelConfig)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public NetworkInterface getNetworkInterface() {
/* 144 */     return (NetworkInterface)getOption0(IP_MULTICAST_IF);
/*     */   }
/*     */ 
/*     */   
/*     */   public DatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface) {
/* 149 */     setOption0(IP_MULTICAST_IF, networkInterface);
/* 150 */     return (DatagramChannelConfig)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLoopbackModeDisabled() {
/* 155 */     return ((Boolean)getOption0(IP_MULTICAST_LOOP)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public DatagramChannelConfig setLoopbackModeDisabled(boolean loopbackModeDisabled) {
/* 160 */     setOption0(IP_MULTICAST_LOOP, Boolean.valueOf(loopbackModeDisabled));
/* 161 */     return (DatagramChannelConfig)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DatagramChannelConfig setAutoRead(boolean autoRead) {
/* 166 */     super.setAutoRead(autoRead);
/* 167 */     return (DatagramChannelConfig)this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void autoReadCleared() {
/* 172 */     ((NioDatagramChannel)this.channel).setReadPending(false);
/*     */   }
/*     */   
/*     */   private Object getOption0(Object option) {
/* 176 */     if (PlatformDependent.javaVersion() < 7) {
/* 177 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     try {
/* 180 */       return GET_OPTION.invoke(this.javaChannel, new Object[] { option });
/* 181 */     } catch (Exception e) {
/* 182 */       throw new ChannelException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setOption0(Object option, Object value) {
/* 188 */     if (PlatformDependent.javaVersion() < 7) {
/* 189 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     try {
/* 192 */       SET_OPTION.invoke(this.javaChannel, new Object[] { option, value });
/* 193 */     } catch (Exception e) {
/* 194 */       throw new ChannelException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\channel\socket\nio\NioDatagramChannelConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */