/*     */ package io.netty.util.internal;
/*     */ 
/*     */ import io.netty.util.internal.logging.InternalLogger;
/*     */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/*     */ import java.util.concurrent.atomic.AtomicLongFieldUpdater;
/*     */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*     */ import sun.misc.Unsafe;
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
/*     */ final class PlatformDependent0
/*     */ {
/*  38 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(PlatformDependent0.class);
/*     */   private static final Unsafe UNSAFE;
/*  40 */   private static final boolean BIG_ENDIAN = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
/*     */ 
/*     */   
/*     */   private static final long ADDRESS_FIELD_OFFSET;
/*     */   
/*     */   private static final long UNSAFE_COPY_THRESHOLD = 1048576L;
/*     */   
/*     */   private static final boolean UNALIGNED;
/*     */ 
/*     */   
/*     */   static {
/*     */     Field field;
/*     */     Unsafe unsafe;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/*  57 */     ByteBuffer direct = ByteBuffer.allocateDirect(1);
/*     */     
/*     */     try {
/*  60 */       field = Buffer.class.getDeclaredField("address");
/*  61 */       field.setAccessible(true);
/*  62 */       if (field.getLong(ByteBuffer.allocate(1)) != 0L)
/*     */       {
/*  64 */         field = null;
/*     */       }
/*  66 */       else if (field.getLong(direct) == 0L)
/*     */       {
/*  68 */         field = null;
/*     */       }
/*     */     
/*  71 */     } catch (Throwable t) {
/*     */       
/*  73 */       field = null;
/*     */     } 
/*  75 */     logger.debug("java.nio.Buffer.address: {}", (field != null) ? "available" : "unavailable");
/*     */ 
/*     */     
/*  78 */     if (field != null) {
/*     */       try {
/*  80 */         Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
/*  81 */         unsafeField.setAccessible(true);
/*  82 */         unsafe = (Unsafe)unsafeField.get((Object)null);
/*  83 */         logger.debug("sun.misc.Unsafe.theUnsafe: {}", (unsafe != null) ? "available" : "unavailable");
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/*  89 */           if (unsafe != null) {
/*  90 */             unsafe.getClass().getDeclaredMethod("copyMemory", new Class[] { Object.class, long.class, Object.class, long.class, long.class });
/*     */             
/*  92 */             logger.debug("sun.misc.Unsafe.copyMemory: available");
/*     */           } 
/*  94 */         } catch (NoSuchMethodError t) {
/*  95 */           logger.debug("sun.misc.Unsafe.copyMemory: unavailable");
/*  96 */           throw t;
/*  97 */         } catch (NoSuchMethodException e) {
/*  98 */           logger.debug("sun.misc.Unsafe.copyMemory: unavailable");
/*  99 */           throw e;
/*     */         } 
/* 101 */       } catch (Throwable cause) {
/*     */         
/* 103 */         unsafe = null;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 108 */       unsafe = null;
/*     */     } 
/*     */     
/* 111 */     UNSAFE = unsafe;
/*     */     
/* 113 */     if (unsafe == null) {
/* 114 */       ADDRESS_FIELD_OFFSET = -1L;
/* 115 */       UNALIGNED = false;
/*     */     } else {
/* 117 */       boolean bool; ADDRESS_FIELD_OFFSET = objectFieldOffset(field);
/*     */       
/*     */       try {
/* 120 */         Class<?> bitsClass = Class.forName("java.nio.Bits", false, ClassLoader.getSystemClassLoader());
/* 121 */         Method unalignedMethod = bitsClass.getDeclaredMethod("unaligned", new Class[0]);
/* 122 */         unalignedMethod.setAccessible(true);
/* 123 */         bool = Boolean.TRUE.equals(unalignedMethod.invoke((Object)null, new Object[0]));
/* 124 */       } catch (Throwable t) {
/*     */         
/* 126 */         String arch = SystemPropertyUtil.get("os.arch", "");
/*     */         
/* 128 */         bool = arch.matches("^(i[3-6]86|x86(_64)?|x64|amd64)$");
/*     */       } 
/*     */       
/* 131 */       UNALIGNED = bool;
/* 132 */       logger.debug("java.nio.Bits.unaligned: {}", Boolean.valueOf(UNALIGNED));
/*     */     } 
/*     */   }
/*     */   
/*     */   static boolean hasUnsafe() {
/* 137 */     return (UNSAFE != null);
/*     */   }
/*     */   
/*     */   static void throwException(Throwable t) {
/* 141 */     UNSAFE.throwException(t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void freeDirectBuffer(ByteBuffer buffer) {
/* 147 */     Cleaner0.freeDirectBuffer(buffer);
/*     */   }
/*     */   
/*     */   static long directBufferAddress(ByteBuffer buffer) {
/* 151 */     return getLong(buffer, ADDRESS_FIELD_OFFSET);
/*     */   }
/*     */   
/*     */   static long arrayBaseOffset() {
/* 155 */     return UNSAFE.arrayBaseOffset(byte[].class);
/*     */   }
/*     */   
/*     */   static Object getObject(Object object, long fieldOffset) {
/* 159 */     return UNSAFE.getObject(object, fieldOffset);
/*     */   }
/*     */   
/*     */   static Object getObjectVolatile(Object object, long fieldOffset) {
/* 163 */     return UNSAFE.getObjectVolatile(object, fieldOffset);
/*     */   }
/*     */   
/*     */   static int getInt(Object object, long fieldOffset) {
/* 167 */     return UNSAFE.getInt(object, fieldOffset);
/*     */   }
/*     */   
/*     */   private static long getLong(Object object, long fieldOffset) {
/* 171 */     return UNSAFE.getLong(object, fieldOffset);
/*     */   }
/*     */   
/*     */   static long objectFieldOffset(Field field) {
/* 175 */     return UNSAFE.objectFieldOffset(field);
/*     */   }
/*     */   
/*     */   static byte getByte(long address) {
/* 179 */     return UNSAFE.getByte(address);
/*     */   }
/*     */   
/*     */   static short getShort(long address) {
/* 183 */     if (UNALIGNED)
/* 184 */       return UNSAFE.getShort(address); 
/* 185 */     if (BIG_ENDIAN) {
/* 186 */       return (short)(getByte(address) << 8 | getByte(address + 1L) & 0xFF);
/*     */     }
/* 188 */     return (short)(getByte(address + 1L) << 8 | getByte(address) & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   static int getInt(long address) {
/* 193 */     if (UNALIGNED)
/* 194 */       return UNSAFE.getInt(address); 
/* 195 */     if (BIG_ENDIAN) {
/* 196 */       return getByte(address) << 24 | (getByte(address + 1L) & 0xFF) << 16 | (getByte(address + 2L) & 0xFF) << 8 | getByte(address + 3L) & 0xFF;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 201 */     return getByte(address + 3L) << 24 | (getByte(address + 2L) & 0xFF) << 16 | (getByte(address + 1L) & 0xFF) << 8 | getByte(address) & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static long getLong(long address) {
/* 209 */     if (UNALIGNED)
/* 210 */       return UNSAFE.getLong(address); 
/* 211 */     if (BIG_ENDIAN) {
/* 212 */       return getByte(address) << 56L | (getByte(address + 1L) & 0xFFL) << 48L | (getByte(address + 2L) & 0xFFL) << 40L | (getByte(address + 3L) & 0xFFL) << 32L | (getByte(address + 4L) & 0xFFL) << 24L | (getByte(address + 5L) & 0xFFL) << 16L | (getByte(address + 6L) & 0xFFL) << 8L | getByte(address + 7L) & 0xFFL;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     return getByte(address + 7L) << 56L | (getByte(address + 6L) & 0xFFL) << 48L | (getByte(address + 5L) & 0xFFL) << 40L | (getByte(address + 4L) & 0xFFL) << 32L | (getByte(address + 3L) & 0xFFL) << 24L | (getByte(address + 2L) & 0xFFL) << 16L | (getByte(address + 1L) & 0xFFL) << 8L | getByte(address) & 0xFFL;
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
/*     */   static void putOrderedObject(Object object, long address, Object value) {
/* 233 */     UNSAFE.putOrderedObject(object, address, value);
/*     */   }
/*     */   
/*     */   static void putByte(long address, byte value) {
/* 237 */     UNSAFE.putByte(address, value);
/*     */   }
/*     */   
/*     */   static void putShort(long address, short value) {
/* 241 */     if (UNALIGNED) {
/* 242 */       UNSAFE.putShort(address, value);
/* 243 */     } else if (BIG_ENDIAN) {
/* 244 */       putByte(address, (byte)(value >>> 8));
/* 245 */       putByte(address + 1L, (byte)value);
/*     */     } else {
/* 247 */       putByte(address + 1L, (byte)(value >>> 8));
/* 248 */       putByte(address, (byte)value);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void putInt(long address, int value) {
/* 253 */     if (UNALIGNED) {
/* 254 */       UNSAFE.putInt(address, value);
/* 255 */     } else if (BIG_ENDIAN) {
/* 256 */       putByte(address, (byte)(value >>> 24));
/* 257 */       putByte(address + 1L, (byte)(value >>> 16));
/* 258 */       putByte(address + 2L, (byte)(value >>> 8));
/* 259 */       putByte(address + 3L, (byte)value);
/*     */     } else {
/* 261 */       putByte(address + 3L, (byte)(value >>> 24));
/* 262 */       putByte(address + 2L, (byte)(value >>> 16));
/* 263 */       putByte(address + 1L, (byte)(value >>> 8));
/* 264 */       putByte(address, (byte)value);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void putLong(long address, long value) {
/* 269 */     if (UNALIGNED) {
/* 270 */       UNSAFE.putLong(address, value);
/* 271 */     } else if (BIG_ENDIAN) {
/* 272 */       putByte(address, (byte)(int)(value >>> 56L));
/* 273 */       putByte(address + 1L, (byte)(int)(value >>> 48L));
/* 274 */       putByte(address + 2L, (byte)(int)(value >>> 40L));
/* 275 */       putByte(address + 3L, (byte)(int)(value >>> 32L));
/* 276 */       putByte(address + 4L, (byte)(int)(value >>> 24L));
/* 277 */       putByte(address + 5L, (byte)(int)(value >>> 16L));
/* 278 */       putByte(address + 6L, (byte)(int)(value >>> 8L));
/* 279 */       putByte(address + 7L, (byte)(int)value);
/*     */     } else {
/* 281 */       putByte(address + 7L, (byte)(int)(value >>> 56L));
/* 282 */       putByte(address + 6L, (byte)(int)(value >>> 48L));
/* 283 */       putByte(address + 5L, (byte)(int)(value >>> 40L));
/* 284 */       putByte(address + 4L, (byte)(int)(value >>> 32L));
/* 285 */       putByte(address + 3L, (byte)(int)(value >>> 24L));
/* 286 */       putByte(address + 2L, (byte)(int)(value >>> 16L));
/* 287 */       putByte(address + 1L, (byte)(int)(value >>> 8L));
/* 288 */       putByte(address, (byte)(int)value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static void copyMemory(long srcAddr, long dstAddr, long length) {
/* 294 */     while (length > 0L) {
/* 295 */       long size = Math.min(length, 1048576L);
/* 296 */       UNSAFE.copyMemory(srcAddr, dstAddr, size);
/* 297 */       length -= size;
/* 298 */       srcAddr += size;
/* 299 */       dstAddr += size;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static void copyMemory(Object src, long srcOffset, Object dst, long dstOffset, long length) {
/* 305 */     while (length > 0L) {
/* 306 */       long size = Math.min(length, 1048576L);
/* 307 */       UNSAFE.copyMemory(src, srcOffset, dst, dstOffset, size);
/* 308 */       length -= size;
/* 309 */       srcOffset += size;
/* 310 */       dstOffset += size;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static <U, W> AtomicReferenceFieldUpdater<U, W> newAtomicReferenceFieldUpdater(Class<U> tclass, String fieldName) throws Exception {
/* 316 */     return new UnsafeAtomicReferenceFieldUpdater<U, W>(UNSAFE, tclass, fieldName);
/*     */   }
/*     */ 
/*     */   
/*     */   static <T> AtomicIntegerFieldUpdater<T> newAtomicIntegerFieldUpdater(Class<?> tclass, String fieldName) throws Exception {
/* 321 */     return new UnsafeAtomicIntegerFieldUpdater<T>(UNSAFE, tclass, fieldName);
/*     */   }
/*     */ 
/*     */   
/*     */   static <T> AtomicLongFieldUpdater<T> newAtomicLongFieldUpdater(Class<?> tclass, String fieldName) throws Exception {
/* 326 */     return new UnsafeAtomicLongFieldUpdater<T>(UNSAFE, tclass, fieldName);
/*     */   }
/*     */   
/*     */   static ClassLoader getClassLoader(final Class<?> clazz) {
/* 330 */     if (System.getSecurityManager() == null) {
/* 331 */       return clazz.getClassLoader();
/*     */     }
/* 333 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction<ClassLoader>()
/*     */         {
/*     */           public ClassLoader run() {
/* 336 */             return clazz.getClassLoader();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   static ClassLoader getContextClassLoader() {
/* 343 */     if (System.getSecurityManager() == null) {
/* 344 */       return Thread.currentThread().getContextClassLoader();
/*     */     }
/* 346 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction<ClassLoader>()
/*     */         {
/*     */           public ClassLoader run() {
/* 349 */             return Thread.currentThread().getContextClassLoader();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   static ClassLoader getSystemClassLoader() {
/* 356 */     if (System.getSecurityManager() == null) {
/* 357 */       return ClassLoader.getSystemClassLoader();
/*     */     }
/* 359 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction<ClassLoader>()
/*     */         {
/*     */           public ClassLoader run() {
/* 362 */             return ClassLoader.getSystemClassLoader();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   static int addressSize() {
/* 369 */     return UNSAFE.addressSize();
/*     */   }
/*     */   
/*     */   static long allocateMemory(long size) {
/* 373 */     return UNSAFE.allocateMemory(size);
/*     */   }
/*     */   
/*     */   static void freeMemory(long address) {
/* 377 */     UNSAFE.freeMemory(address);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\nett\\util\internal\PlatformDependent0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */