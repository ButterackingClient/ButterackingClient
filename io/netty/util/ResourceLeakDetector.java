/*     */ package io.netty.util;
/*     */ 
/*     */ import io.netty.util.internal.PlatformDependent;
/*     */ import io.netty.util.internal.StringUtil;
/*     */ import io.netty.util.internal.SystemPropertyUtil;
/*     */ import io.netty.util.internal.logging.InternalLogger;
/*     */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*     */ import java.lang.ref.PhantomReference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import java.util.EnumSet;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
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
/*     */ public final class ResourceLeakDetector<T>
/*     */ {
/*     */   private static final String PROP_LEVEL = "io.netty.leakDetectionLevel";
/*     */   
/*     */   static {
/*     */     boolean disabled;
/*     */   }
/*     */   
/*  37 */   private static final Level DEFAULT_LEVEL = Level.SIMPLE;
/*     */ 
/*     */   
/*     */   private static Level level;
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Level
/*     */   {
/*  46 */     DISABLED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     SIMPLE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     ADVANCED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     PARANOID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ResourceLeakDetector.class);
/*     */   private static final int DEFAULT_SAMPLING_INTERVAL = 113;
/*     */   
/*     */   static {
/*  70 */     if (SystemPropertyUtil.get("io.netty.noResourceLeakDetection") != null) {
/*  71 */       disabled = SystemPropertyUtil.getBoolean("io.netty.noResourceLeakDetection", false);
/*  72 */       logger.debug("-Dio.netty.noResourceLeakDetection: {}", Boolean.valueOf(disabled));
/*  73 */       logger.warn("-Dio.netty.noResourceLeakDetection is deprecated. Use '-D{}={}' instead.", "io.netty.leakDetectionLevel", DEFAULT_LEVEL.name().toLowerCase());
/*     */     }
/*     */     else {
/*     */       
/*  77 */       disabled = false;
/*     */     } 
/*     */     
/*  80 */     Level defaultLevel = disabled ? Level.DISABLED : DEFAULT_LEVEL;
/*  81 */     String levelStr = SystemPropertyUtil.get("io.netty.leakDetectionLevel", defaultLevel.name()).trim().toUpperCase();
/*  82 */     Level level = DEFAULT_LEVEL;
/*  83 */     for (Level l : EnumSet.<Level>allOf(Level.class)) {
/*  84 */       if (levelStr.equals(l.name()) || levelStr.equals(String.valueOf(l.ordinal()))) {
/*  85 */         level = l;
/*     */       }
/*     */     } 
/*     */     
/*  89 */     ResourceLeakDetector.level = level;
/*  90 */     if (logger.isDebugEnabled()) {
/*  91 */       logger.debug("-D{}: {}", "io.netty.leakDetectionLevel", level.name().toLowerCase());
/*     */     }
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
/*     */ 
/*     */ 
/*     */     
/* 355 */     STACK_TRACE_ELEMENT_EXCLUSIONS = new String[] { "io.netty.buffer.AbstractByteBufAllocator.toLeakAwareBuffer(" };
/*     */   }
/*     */ 
/*     */   
/*     */   static String newRecord(int recordsToSkip) {
/* 360 */     StringBuilder buf = new StringBuilder(4096);
/* 361 */     StackTraceElement[] array = (new Throwable()).getStackTrace();
/* 362 */     for (StackTraceElement e : array) {
/* 363 */       if (recordsToSkip > 0) {
/* 364 */         recordsToSkip--;
/*     */       } else {
/* 366 */         String estr = e.toString();
/*     */ 
/*     */         
/* 369 */         boolean excluded = false;
/* 370 */         for (String exclusion : STACK_TRACE_ELEMENT_EXCLUSIONS) {
/* 371 */           if (estr.startsWith(exclusion)) {
/* 372 */             excluded = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 377 */         if (!excluded) {
/* 378 */           buf.append('\t');
/* 379 */           buf.append(estr);
/* 380 */           buf.append(StringUtil.NEWLINE);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 385 */     return buf.toString();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void setEnabled(boolean enabled) {
/*     */     setLevel(enabled ? Level.SIMPLE : Level.DISABLED);
/*     */   }
/*     */   
/*     */   public static boolean isEnabled() {
/*     */     return (getLevel().ordinal() > Level.DISABLED.ordinal());
/*     */   }
/*     */   
/*     */   public static void setLevel(Level level) {
/*     */     if (level == null)
/*     */       throw new NullPointerException("level"); 
/*     */     ResourceLeakDetector.level = level;
/*     */   }
/*     */   
/*     */   public static Level getLevel() {
/*     */     return level;
/*     */   }
/*     */   
/*     */   private final DefaultResourceLeak head = new DefaultResourceLeak(null);
/*     */   private final DefaultResourceLeak tail = new DefaultResourceLeak(null);
/*     */   private final ReferenceQueue<Object> refQueue = new ReferenceQueue();
/*     */   private final ConcurrentMap<String, Boolean> reportedLeaks = PlatformDependent.newConcurrentHashMap();
/*     */   private final String resourceType;
/*     */   private final int samplingInterval;
/*     */   private final long maxActive;
/*     */   private long active;
/*     */   private final AtomicBoolean loggedTooManyActive = new AtomicBoolean();
/*     */   private long leakCheckCnt;
/*     */   private static final String[] STACK_TRACE_ELEMENT_EXCLUSIONS;
/*     */   
/*     */   public ResourceLeakDetector(Class<?> resourceType) {
/*     */     this(StringUtil.simpleClassName(resourceType));
/*     */   }
/*     */   
/*     */   public ResourceLeakDetector(String resourceType) {
/*     */     this(resourceType, 113, Long.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public ResourceLeakDetector(Class<?> resourceType, int samplingInterval, long maxActive) {
/*     */     this(StringUtil.simpleClassName(resourceType), samplingInterval, maxActive);
/*     */   }
/*     */   
/*     */   public ResourceLeakDetector(String resourceType, int samplingInterval, long maxActive) {
/*     */     if (resourceType == null)
/*     */       throw new NullPointerException("resourceType"); 
/*     */     if (samplingInterval <= 0)
/*     */       throw new IllegalArgumentException("samplingInterval: " + samplingInterval + " (expected: 1+)"); 
/*     */     if (maxActive <= 0L)
/*     */       throw new IllegalArgumentException("maxActive: " + maxActive + " (expected: 1+)"); 
/*     */     this.resourceType = resourceType;
/*     */     this.samplingInterval = samplingInterval;
/*     */     this.maxActive = maxActive;
/*     */     this.head.next = this.tail;
/*     */     this.tail.prev = this.head;
/*     */   }
/*     */   
/*     */   public ResourceLeak open(T obj) {
/*     */     Level level = ResourceLeakDetector.level;
/*     */     if (level == Level.DISABLED)
/*     */       return null; 
/*     */     if (level.ordinal() < Level.PARANOID.ordinal()) {
/*     */       if (this.leakCheckCnt++ % this.samplingInterval == 0L) {
/*     */         reportLeak(level);
/*     */         return new DefaultResourceLeak(obj);
/*     */       } 
/*     */       return null;
/*     */     } 
/*     */     reportLeak(level);
/*     */     return new DefaultResourceLeak(obj);
/*     */   }
/*     */   
/*     */   private void reportLeak(Level level) {
/*     */     if (!logger.isErrorEnabled()) {
/*     */       while (true) {
/*     */         DefaultResourceLeak ref = (DefaultResourceLeak)this.refQueue.poll();
/*     */         if (ref == null)
/*     */           break; 
/*     */         ref.close();
/*     */       } 
/*     */       return;
/*     */     } 
/*     */     int samplingInterval = (level == Level.PARANOID) ? 1 : this.samplingInterval;
/*     */     if (this.active * samplingInterval > this.maxActive && this.loggedTooManyActive.compareAndSet(false, true))
/*     */       logger.error("LEAK: You are creating too many " + this.resourceType + " instances.  " + this.resourceType + " is a shared resource that must be reused across the JVM," + "so that only a few instances are created."); 
/*     */     while (true) {
/*     */       DefaultResourceLeak ref = (DefaultResourceLeak)this.refQueue.poll();
/*     */       if (ref == null)
/*     */         break; 
/*     */       ref.clear();
/*     */       if (!ref.close())
/*     */         continue; 
/*     */       String records = ref.toString();
/*     */       if (this.reportedLeaks.putIfAbsent(records, Boolean.TRUE) == null) {
/*     */         if (records.isEmpty()) {
/*     */           logger.error("LEAK: {}.release() was not called before it's garbage-collected. Enable advanced leak reporting to find out where the leak occurred. To enable advanced leak reporting, specify the JVM option '-D{}={}' or call {}.setLevel()", new Object[] { this.resourceType, "io.netty.leakDetectionLevel", Level.ADVANCED.name().toLowerCase(), StringUtil.simpleClassName(this) });
/*     */           continue;
/*     */         } 
/*     */         logger.error("LEAK: {}.release() was not called before it's garbage-collected.{}", this.resourceType, records);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final class DefaultResourceLeak extends PhantomReference<Object> implements ResourceLeak {
/*     */     private static final int MAX_RECORDS = 4;
/*     */     private final String creationRecord;
/*     */     private final Deque<String> lastRecords = new ArrayDeque<String>();
/*     */     private final AtomicBoolean freed;
/*     */     private DefaultResourceLeak prev;
/*     */     private DefaultResourceLeak next;
/*     */     
/*     */     DefaultResourceLeak(Object referent) {
/*     */       super(referent, (referent != null) ? ResourceLeakDetector.this.refQueue : null);
/*     */       if (referent != null) {
/*     */         ResourceLeakDetector.Level level = ResourceLeakDetector.getLevel();
/*     */         if (level.ordinal() >= ResourceLeakDetector.Level.ADVANCED.ordinal()) {
/*     */           this.creationRecord = ResourceLeakDetector.newRecord(3);
/*     */         } else {
/*     */           this.creationRecord = null;
/*     */         } 
/*     */         synchronized (ResourceLeakDetector.this.head) {
/*     */           this.prev = ResourceLeakDetector.this.head;
/*     */           this.next = ResourceLeakDetector.this.head.next;
/*     */           ResourceLeakDetector.this.head.next.prev = this;
/*     */           ResourceLeakDetector.this.head.next = this;
/*     */           ResourceLeakDetector.this.active++;
/*     */         } 
/*     */         this.freed = new AtomicBoolean();
/*     */       } else {
/*     */         this.creationRecord = null;
/*     */         this.freed = new AtomicBoolean(true);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void record() {
/*     */       if (this.creationRecord != null) {
/*     */         String value = ResourceLeakDetector.newRecord(2);
/*     */         synchronized (this.lastRecords) {
/*     */           int size = this.lastRecords.size();
/*     */           if (size == 0 || !((String)this.lastRecords.getLast()).equals(value))
/*     */             this.lastRecords.add(value); 
/*     */           if (size > 4)
/*     */             this.lastRecords.removeFirst(); 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean close() {
/*     */       if (this.freed.compareAndSet(false, true)) {
/*     */         synchronized (ResourceLeakDetector.this.head) {
/*     */           ResourceLeakDetector.this.active--;
/*     */           this.prev.next = this.next;
/*     */           this.next.prev = this.prev;
/*     */           this.prev = null;
/*     */           this.next = null;
/*     */         } 
/*     */         return true;
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       Object[] array;
/*     */       if (this.creationRecord == null)
/*     */         return ""; 
/*     */       synchronized (this.lastRecords) {
/*     */         array = this.lastRecords.toArray();
/*     */       } 
/*     */       StringBuilder buf = new StringBuilder(16384);
/*     */       buf.append(StringUtil.NEWLINE);
/*     */       buf.append("Recent access records: ");
/*     */       buf.append(array.length);
/*     */       buf.append(StringUtil.NEWLINE);
/*     */       if (array.length > 0)
/*     */         for (int i = array.length - 1; i >= 0; i--) {
/*     */           buf.append('#');
/*     */           buf.append(i + 1);
/*     */           buf.append(':');
/*     */           buf.append(StringUtil.NEWLINE);
/*     */           buf.append(array[i]);
/*     */         }  
/*     */       buf.append("Created at:");
/*     */       buf.append(StringUtil.NEWLINE);
/*     */       buf.append(this.creationRecord);
/*     */       buf.setLength(buf.length() - StringUtil.NEWLINE.length());
/*     */       return buf.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\nett\\util\ResourceLeakDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */