/*     */ package io.netty.channel.epoll;
/*     */ 
/*     */ import io.netty.channel.ChannelException;
/*     */ import io.netty.channel.DefaultFileRegion;
/*     */ import io.netty.util.internal.NativeLibraryLoader;
/*     */ import io.netty.util.internal.PlatformDependent;
/*     */ import io.netty.util.internal.SystemPropertyUtil;
/*     */ import java.io.IOException;
/*     */ import java.net.Inet6Address;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Native
/*     */ {
/*  38 */   private static final byte[] IPV4_MAPPED_IPV6_PREFIX = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1 }; public static final int EPOLLIN = 1; public static final int EPOLLOUT = 2; public static final int EPOLLACCEPT = 4;
/*     */   public static final int EPOLLRDHUP = 8;
/*     */   
/*     */   static {
/*  42 */     String name = SystemPropertyUtil.get("os.name").toLowerCase(Locale.UK).trim();
/*  43 */     if (!name.startsWith("linux")) {
/*  44 */       throw new IllegalStateException("Only supported on Linux");
/*     */     }
/*  46 */     NativeLibraryLoader.load("netty-transport-native-epoll", PlatformDependent.getClassLoader(Native.class));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final int IOV_MAX = iovMax();
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
/*     */   public static int sendTo(int fd, ByteBuffer buf, int pos, int limit, InetAddress addr, int port) throws IOException {
/*     */     byte[] address;
/*     */     int scopeId;
/*  87 */     if (addr instanceof Inet6Address) {
/*  88 */       address = addr.getAddress();
/*  89 */       scopeId = ((Inet6Address)addr).getScopeId();
/*     */     } else {
/*     */       
/*  92 */       scopeId = 0;
/*  93 */       address = ipv4MappedIpv6Address(addr.getAddress());
/*     */     } 
/*  95 */     return sendTo(fd, buf, pos, limit, address, scopeId, port);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int sendToAddress(int fd, long memoryAddress, int pos, int limit, InetAddress addr, int port) throws IOException {
/*     */     byte[] address;
/*     */     int scopeId;
/* 107 */     if (addr instanceof Inet6Address) {
/* 108 */       address = addr.getAddress();
/* 109 */       scopeId = ((Inet6Address)addr).getScopeId();
/*     */     } else {
/*     */       
/* 112 */       scopeId = 0;
/* 113 */       address = ipv4MappedIpv6Address(addr.getAddress());
/*     */     } 
/* 115 */     return sendToAddress(fd, memoryAddress, pos, limit, address, scopeId, port);
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
/*     */   public static int socketStreamFd() {
/*     */     try {
/* 130 */       return socketStream();
/* 131 */     } catch (IOException e) {
/* 132 */       throw new ChannelException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int socketDgramFd() {
/*     */     try {
/* 138 */       return socketDgram();
/* 139 */     } catch (IOException e) {
/* 140 */       throw new ChannelException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void bind(int fd, InetAddress addr, int port) throws IOException {
/* 147 */     NativeInetAddress address = toNativeInetAddress(addr);
/* 148 */     bind(fd, address.address, address.scopeId, port);
/*     */   }
/*     */   
/*     */   private static byte[] ipv4MappedIpv6Address(byte[] ipv4) {
/* 152 */     byte[] address = new byte[16];
/* 153 */     System.arraycopy(IPV4_MAPPED_IPV6_PREFIX, 0, address, 0, IPV4_MAPPED_IPV6_PREFIX.length);
/* 154 */     System.arraycopy(ipv4, 0, address, 12, ipv4.length);
/* 155 */     return address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean connect(int fd, InetAddress addr, int port) throws IOException {
/* 161 */     NativeInetAddress address = toNativeInetAddress(addr);
/* 162 */     return connect(fd, address.address, address.scopeId, port);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static NativeInetAddress toNativeInetAddress(InetAddress addr) {
/* 202 */     byte[] bytes = addr.getAddress();
/* 203 */     if (addr instanceof Inet6Address) {
/* 204 */       return new NativeInetAddress(bytes, ((Inet6Address)addr).getScopeId());
/*     */     }
/*     */     
/* 207 */     return new NativeInetAddress(ipv4MappedIpv6Address(bytes));
/*     */   } public static native int eventFd(); public static native void eventFdWrite(int paramInt, long paramLong); public static native void eventFdRead(int paramInt); public static native int epollCreate(); public static native int epollWait(int paramInt1, long[] paramArrayOflong, int paramInt2); public static native void epollCtlAdd(int paramInt1, int paramInt2, int paramInt3, int paramInt4); public static native void epollCtlMod(int paramInt1, int paramInt2, int paramInt3, int paramInt4); public static native void epollCtlDel(int paramInt1, int paramInt2); public static native void close(int paramInt) throws IOException; public static native int write(int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3) throws IOException; public static native int writeAddress(int paramInt1, long paramLong, int paramInt2, int paramInt3) throws IOException; public static native long writev(int paramInt1, ByteBuffer[] paramArrayOfByteBuffer, int paramInt2, int paramInt3) throws IOException; public static native long writevAddresses(int paramInt1, long paramLong, int paramInt2) throws IOException; public static native int read(int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3) throws IOException; public static native int readAddress(int paramInt1, long paramLong, int paramInt2, int paramInt3) throws IOException; public static native long sendfile(int paramInt, DefaultFileRegion paramDefaultFileRegion, long paramLong1, long paramLong2, long paramLong3) throws IOException; private static native int sendTo(int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3, byte[] paramArrayOfbyte, int paramInt4, int paramInt5) throws IOException; private static native int sendToAddress(int paramInt1, long paramLong, int paramInt2, int paramInt3, byte[] paramArrayOfbyte, int paramInt4, int paramInt5) throws IOException; public static native EpollDatagramChannel.DatagramSocketAddress recvFrom(int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3) throws IOException; public static native EpollDatagramChannel.DatagramSocketAddress recvFromAddress(int paramInt1, long paramLong, int paramInt2, int paramInt3) throws IOException; private static native int socketStream() throws IOException; private static native int socketDgram() throws IOException; public static native void bind(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) throws IOException; public static native void listen(int paramInt1, int paramInt2) throws IOException; public static native boolean connect(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) throws IOException; public static native boolean finishConnect(int paramInt) throws IOException; public static native InetSocketAddress remoteAddress(int paramInt); public static native InetSocketAddress localAddress(int paramInt); public static native int accept(int paramInt) throws IOException; public static native void shutdown(int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws IOException; public static native int getReceiveBufferSize(int paramInt); public static native int getSendBufferSize(int paramInt); public static native int isKeepAlive(int paramInt); public static native int isReuseAddress(int paramInt); public static native int isReusePort(int paramInt); public static native int isTcpNoDelay(int paramInt); public static native int isTcpCork(int paramInt); public static native int getSoLinger(int paramInt); public static native int getTrafficClass(int paramInt); public static native int isBroadcast(int paramInt); public static native int getTcpKeepIdle(int paramInt); public static native int getTcpKeepIntvl(int paramInt); public static native int getTcpKeepCnt(int paramInt); public static native void setKeepAlive(int paramInt1, int paramInt2); public static native void setReceiveBufferSize(int paramInt1, int paramInt2); public static native void setReuseAddress(int paramInt1, int paramInt2); public static native void setReusePort(int paramInt1, int paramInt2); public static native void setSendBufferSize(int paramInt1, int paramInt2); public static native void setTcpNoDelay(int paramInt1, int paramInt2); public static native void setTcpCork(int paramInt1, int paramInt2); public static native void setSoLinger(int paramInt1, int paramInt2); public static native void setTrafficClass(int paramInt1, int paramInt2); public static native void setBroadcast(int paramInt1, int paramInt2);
/*     */   public static native void setTcpKeepIdle(int paramInt1, int paramInt2);
/*     */   public static native void setTcpKeepIntvl(int paramInt1, int paramInt2);
/*     */   public static native void setTcpKeepCnt(int paramInt1, int paramInt2);
/*     */   public static native String kernelVersion();
/*     */   private static native int iovMax();
/*     */   private static class NativeInetAddress { final byte[] address;
/*     */     NativeInetAddress(byte[] address, int scopeId) {
/* 216 */       this.address = address;
/* 217 */       this.scopeId = scopeId;
/*     */     }
/*     */     final int scopeId;
/*     */     NativeInetAddress(byte[] address) {
/* 221 */       this(address, 0);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\channel\epoll\Native.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */