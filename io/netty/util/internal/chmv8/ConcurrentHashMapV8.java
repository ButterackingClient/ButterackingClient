/*      */ package io.netty.util.internal.chmv8;
/*      */ 
/*      */ import io.netty.util.internal.IntegerHolder;
/*      */ import io.netty.util.internal.InternalThreadLocalMap;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamField;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.ParameterizedType;
/*      */ import java.lang.reflect.Type;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedActionException;
/*      */ import java.security.PrivilegedExceptionAction;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ConcurrentMap;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.concurrent.atomic.AtomicReference;
/*      */ import java.util.concurrent.locks.LockSupport;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import sun.misc.Unsafe;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConcurrentHashMapV8<K, V>
/*      */   implements ConcurrentMap<K, V>, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 7249069246763182397L;
/*      */   private static final int MAXIMUM_CAPACITY = 1073741824;
/*      */   private static final int DEFAULT_CAPACITY = 16;
/*      */   static final int MAX_ARRAY_SIZE = 2147483639;
/*      */   private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
/*      */   private static final float LOAD_FACTOR = 0.75F;
/*      */   static final int TREEIFY_THRESHOLD = 8;
/*      */   static final int UNTREEIFY_THRESHOLD = 6;
/*      */   static final int MIN_TREEIFY_CAPACITY = 64;
/*      */   private static final int MIN_TRANSFER_STRIDE = 16;
/*      */   static final int MOVED = -1;
/*      */   static final int TREEBIN = -2;
/*      */   static final int RESERVED = -3;
/*      */   static final int HASH_BITS = 2147483647;
/*  594 */   static final int NCPU = Runtime.getRuntime().availableProcessors();
/*      */ 
/*      */   
/*  597 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("segments", Segment[].class), new ObjectStreamField("segmentMask", int.class), new ObjectStreamField("segmentShift", int.class) };
/*      */   
/*      */   volatile transient Node<K, V>[] table;
/*      */   private volatile transient Node<K, V>[] nextTable;
/*      */   private volatile transient long baseCount;
/*      */   private volatile transient int sizeCtl;
/*      */   private volatile transient int transferIndex;
/*      */   private volatile transient int transferOrigin;
/*      */   private volatile transient int cellsBusy;
/*      */   private volatile transient CounterCell[] counterCells;
/*      */   private transient KeySetView<K, V> keySet;
/*      */   private transient ValuesView<K, V> values;
/*      */   private transient EntrySetView<K, V> entrySet;
/*      */   
/*      */   static class Node<K, V>
/*      */     implements Map.Entry<K, V>
/*      */   {
/*      */     final int hash;
/*      */     final K key;
/*      */     volatile V val;
/*      */     volatile Node<K, V> next;
/*      */     
/*      */     Node(int hash, K key, V val, Node<K, V> next) {
/*  620 */       this.hash = hash;
/*  621 */       this.key = key;
/*  622 */       this.val = val;
/*  623 */       this.next = next;
/*      */     }
/*      */     
/*  626 */     public final K getKey() { return this.key; }
/*  627 */     public final V getValue() { return this.val; }
/*  628 */     public final int hashCode() { return this.key.hashCode() ^ this.val.hashCode(); } public final String toString() {
/*  629 */       return (new StringBuilder()).append(this.key).append("=").append(this.val).toString();
/*      */     }
/*  631 */     public final V setValue(V value) { throw new UnsupportedOperationException(); } public final boolean equals(Object o) {
/*      */       Object k;
/*      */       Object v;
/*      */       Object u;
/*      */       Map.Entry<?, ?> e;
/*  636 */       return (o instanceof Map.Entry && (k = (e = (Map.Entry<?, ?>)o).getKey()) != null && (v = e.getValue()) != null && (k == this.key || k.equals(this.key)) && (v == (u = this.val) || v.equals(u)));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Node<K, V> find(int h, Object k) {
/*  647 */       Node<K, V> e = this;
/*  648 */       if (k != null) {
/*      */         do {
/*      */           K ek;
/*  651 */           if (e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek))))
/*      */           {
/*  653 */             return e; } 
/*  654 */         } while ((e = e.next) != null);
/*      */       }
/*  656 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final int spread(int h) {
/*  679 */     return (h ^ h >>> 16) & Integer.MAX_VALUE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int tableSizeFor(int c) {
/*  687 */     int n = c - 1;
/*  688 */     n |= n >>> 1;
/*  689 */     n |= n >>> 2;
/*  690 */     n |= n >>> 4;
/*  691 */     n |= n >>> 8;
/*  692 */     n |= n >>> 16;
/*  693 */     return (n < 0) ? 1 : ((n >= 1073741824) ? 1073741824 : (n + 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Class<?> comparableClassFor(Object x) {
/*  701 */     if (x instanceof Comparable) {
/*      */       Class<?> c;
/*  703 */       if ((c = x.getClass()) == String.class)
/*  704 */         return c;  Type[] ts;
/*  705 */       if ((ts = c.getGenericInterfaces()) != null)
/*  706 */         for (int i = 0; i < ts.length; i++) {
/*  707 */           Type[] as; Type t; ParameterizedType p; if (t = ts[i] instanceof ParameterizedType && (p = (ParameterizedType)t).getRawType() == Comparable.class && (as = p.getActualTypeArguments()) != null && as.length == 1 && as[0] == c)
/*      */           {
/*      */ 
/*      */ 
/*      */             
/*  712 */             return c;
/*      */           }
/*      */         }  
/*      */     } 
/*  716 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int compareComparables(Class<?> kc, Object k, Object x) {
/*  725 */     return (x == null || x.getClass() != kc) ? 0 : ((Comparable<Object>)k).compareTo(x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final <K, V> Node<K, V> tabAt(Node<K, V>[] tab, int i) {
/*  749 */     return (Node<K, V>)U.getObjectVolatile(tab, (i << ASHIFT) + ABASE);
/*      */   }
/*      */ 
/*      */   
/*      */   static final <K, V> boolean casTabAt(Node<K, V>[] tab, int i, Node<K, V> c, Node<K, V> v) {
/*  754 */     return U.compareAndSwapObject(tab, (i << ASHIFT) + ABASE, c, v);
/*      */   }
/*      */   
/*      */   static final <K, V> void setTabAt(Node<K, V>[] tab, int i, Node<K, V> v) {
/*  758 */     U.putObjectVolatile(tab, (i << ASHIFT) + ABASE, v);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConcurrentHashMapV8() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConcurrentHashMapV8(int initialCapacity) {
/*  836 */     if (initialCapacity < 0)
/*  837 */       throw new IllegalArgumentException(); 
/*  838 */     int cap = (initialCapacity >= 536870912) ? 1073741824 : tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1);
/*      */ 
/*      */     
/*  841 */     this.sizeCtl = cap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConcurrentHashMapV8(Map<? extends K, ? extends V> m) {
/*  850 */     this.sizeCtl = 16;
/*  851 */     putAll(m);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConcurrentHashMapV8(int initialCapacity, float loadFactor) {
/*  870 */     this(initialCapacity, loadFactor, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConcurrentHashMapV8(int initialCapacity, float loadFactor, int concurrencyLevel) {
/*  893 */     if (loadFactor <= 0.0F || initialCapacity < 0 || concurrencyLevel <= 0)
/*  894 */       throw new IllegalArgumentException(); 
/*  895 */     if (initialCapacity < concurrencyLevel)
/*  896 */       initialCapacity = concurrencyLevel; 
/*  897 */     long size = (long)(1.0D + ((float)initialCapacity / loadFactor));
/*  898 */     int cap = (size >= 1073741824L) ? 1073741824 : tableSizeFor((int)size);
/*      */     
/*  900 */     this.sizeCtl = cap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  909 */     long n = sumCount();
/*  910 */     return (n < 0L) ? 0 : ((n > 2147483647L) ? Integer.MAX_VALUE : (int)n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  919 */     return (sumCount() <= 0L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V get(Object key) {
/*  935 */     int h = spread(key.hashCode()); Node<K, V> tab[], e; int n;
/*  936 */     if ((tab = this.table) != null && (n = tab.length) > 0 && (e = tabAt(tab, n - 1 & h)) != null) {
/*      */       int eh;
/*  938 */       if ((eh = e.hash) == h) {
/*  939 */         K ek; if ((ek = e.key) == key || (ek != null && key.equals(ek))) {
/*  940 */           return e.val;
/*      */         }
/*  942 */       } else if (eh < 0) {
/*  943 */         Node<K, V> p; return ((p = e.find(h, key)) != null) ? p.val : null;
/*  944 */       }  while ((e = e.next) != null) {
/*  945 */         K ek; if (e.hash == h && ((ek = e.key) == key || (ek != null && key.equals(ek))))
/*      */         {
/*  947 */           return e.val; } 
/*      */       } 
/*      */     } 
/*  950 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  963 */     return (get(key) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  977 */     if (value == null)
/*  978 */       throw new NullPointerException(); 
/*      */     Node<K, V>[] t;
/*  980 */     if ((t = this.table) != null) {
/*  981 */       Traverser<K, V> it = new Traverser<K, V>(t, t.length, 0, t.length); Node<K, V> p;
/*  982 */       while ((p = it.advance()) != null) {
/*      */         V v;
/*  984 */         if ((v = p.val) == value || (v != null && value.equals(v)))
/*  985 */           return true; 
/*      */       } 
/*      */     } 
/*  988 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V put(K key, V value) {
/* 1005 */     return putVal(key, value, false);
/*      */   }
/*      */ 
/*      */   
/*      */   final V putVal(K key, V value, boolean onlyIfAbsent) {
/* 1010 */     if (key == null || value == null) throw new NullPointerException(); 
/* 1011 */     int hash = spread(key.hashCode());
/* 1012 */     int binCount = 0;
/* 1013 */     Node<K, V>[] tab = this.table; while (true) {
/*      */       Node[] arrayOfNode; int n;
/* 1015 */       if (tab == null || (n = tab.length) == 0) {
/* 1016 */         arrayOfNode = (Node[])initTable(); continue;
/* 1017 */       }  Node<K, V> f; int i; if ((f = tabAt((Node<?, ?>[])arrayOfNode, i = n - 1 & hash)) == null) {
/* 1018 */         if (casTabAt((Node<?, ?>[])arrayOfNode, i, null, new Node<Object, Object>(hash, key, value, null)))
/*      */           break;  continue;
/*      */       } 
/*      */       int fh;
/* 1022 */       if ((fh = f.hash) == -1) {
/* 1023 */         arrayOfNode = (Node[])helpTransfer((Node<K, V>[])arrayOfNode, f); continue;
/*      */       } 
/* 1025 */       V oldVal = null;
/* 1026 */       synchronized (f) {
/* 1027 */         if (tabAt((Node<K, V>[])arrayOfNode, i) == f)
/* 1028 */           if (fh >= 0) {
/* 1029 */             binCount = 1;
/* 1030 */             for (Node<K, V> e = f;; binCount++) {
/*      */               K ek;
/* 1032 */               if (e.hash == hash && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
/*      */ 
/*      */                 
/* 1035 */                 oldVal = e.val;
/* 1036 */                 if (!onlyIfAbsent)
/* 1037 */                   e.val = value; 
/*      */                 break;
/*      */               } 
/* 1040 */               Node<K, V> pred = e;
/* 1041 */               if ((e = e.next) == null) {
/* 1042 */                 pred.next = new Node<K, V>(hash, key, value, null);
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1048 */           } else if (f instanceof TreeBin) {
/*      */             
/* 1050 */             binCount = 2; Node<K, V> p;
/* 1051 */             if ((p = ((TreeBin<K, V>)f).putTreeVal(hash, key, value)) != null) {
/*      */               
/* 1053 */               oldVal = p.val;
/* 1054 */               if (!onlyIfAbsent) {
/* 1055 */                 p.val = value;
/*      */               }
/*      */             } 
/*      */           }  
/*      */       } 
/* 1060 */       if (binCount != 0) {
/* 1061 */         if (binCount >= 8)
/* 1062 */           treeifyBin((Node<K, V>[])arrayOfNode, i); 
/* 1063 */         if (oldVal != null) {
/* 1064 */           return oldVal;
/*      */         }
/*      */         break;
/*      */       } 
/*      */     } 
/* 1069 */     addCount(1L, binCount);
/* 1070 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map<? extends K, ? extends V> m) {
/* 1081 */     tryPresize(m.size());
/* 1082 */     for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
/* 1083 */       putVal(e.getKey(), e.getValue(), false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V remove(Object key) {
/* 1096 */     return replaceNode(key, null, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final V replaceNode(Object key, V value, Object cv) {
/* 1105 */     int hash = spread(key.hashCode());
/* 1106 */     Node<K, V>[] tab = this.table; Node<K, V> f;
/*      */     int n, i;
/* 1108 */     while (tab != null && (n = tab.length) != 0 && (f = tabAt(tab, i = n - 1 & hash)) != null) {
/*      */       Node[] arrayOfNode;
/*      */       int fh;
/* 1111 */       if ((fh = f.hash) == -1) {
/* 1112 */         arrayOfNode = (Node[])helpTransfer(tab, f); continue;
/*      */       } 
/* 1114 */       V oldVal = null;
/* 1115 */       boolean validated = false;
/* 1116 */       synchronized (f) {
/* 1117 */         if (tabAt((Node<K, V>[])arrayOfNode, i) == f)
/* 1118 */           if (fh >= 0) {
/* 1119 */             validated = true;
/* 1120 */             Node<K, V> e = f, pred = null; do {
/*      */               K ek;
/* 1122 */               if (e.hash == hash && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
/*      */ 
/*      */                 
/* 1125 */                 V ev = e.val;
/* 1126 */                 if (cv == null || cv == ev || (ev != null && cv.equals(ev))) {
/*      */                   
/* 1128 */                   oldVal = ev;
/* 1129 */                   if (value != null) {
/* 1130 */                     e.val = value; break;
/* 1131 */                   }  if (pred != null) {
/* 1132 */                     pred.next = e.next; break;
/*      */                   } 
/* 1134 */                   setTabAt((Node<K, V>[])arrayOfNode, i, e.next);
/*      */                 } 
/*      */                 break;
/*      */               } 
/* 1138 */               pred = e;
/* 1139 */             } while ((e = e.next) != null);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 1144 */             validated = true;
/* 1145 */             TreeBin<K, V> t = (TreeBin<K, V>)f;
/*      */             TreeNode<K, V> r, p;
/* 1147 */             if (f instanceof TreeBin && (r = t.root) != null && (p = r.findTreeNode(hash, key, (Class<?>)null)) != null) {
/*      */               
/* 1149 */               V pv = p.val;
/* 1150 */               if (cv == null || cv == pv || (pv != null && cv.equals(pv))) {
/*      */                 
/* 1152 */                 oldVal = pv;
/* 1153 */                 if (value != null) {
/* 1154 */                   p.val = value;
/* 1155 */                 } else if (t.removeTreeNode(p)) {
/* 1156 */                   setTabAt((Node<?, ?>[])arrayOfNode, i, untreeify(t.first));
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           }  
/*      */       } 
/* 1162 */       if (validated) {
/* 1163 */         if (oldVal != null) {
/* 1164 */           if (value == null)
/* 1165 */             addCount(-1L, -1); 
/* 1166 */           return oldVal;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1172 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/* 1179 */     long delta = 0L;
/* 1180 */     int i = 0;
/* 1181 */     Node<K, V>[] tab = this.table;
/* 1182 */     while (tab != null && i < tab.length) {
/*      */       Node[] arrayOfNode;
/* 1184 */       Node<K, V> f = tabAt(tab, i);
/* 1185 */       if (f == null) {
/* 1186 */         i++; continue;
/* 1187 */       }  int fh; if ((fh = f.hash) == -1) {
/* 1188 */         arrayOfNode = (Node[])helpTransfer(tab, f);
/* 1189 */         i = 0;
/*      */         continue;
/*      */       } 
/* 1192 */       synchronized (f) {
/* 1193 */         if (tabAt((Node<K, V>[])arrayOfNode, i) == f) {
/* 1194 */           Node<K, V> p = (fh >= 0) ? f : ((f instanceof TreeBin) ? ((TreeBin)f).first : null);
/*      */ 
/*      */           
/* 1197 */           while (p != null) {
/* 1198 */             delta--;
/* 1199 */             p = p.next;
/*      */           } 
/* 1201 */           setTabAt((Node<?, ?>[])arrayOfNode, i++, null);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1206 */     if (delta != 0L) {
/* 1207 */       addCount(delta, -1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public KeySetView<K, V> keySet() {
/*      */     KeySetView<K, V> ks;
/* 1230 */     return ((ks = this.keySet) != null) ? ks : (this.keySet = new KeySetView<K, V>(this, null));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<V> values() {
/*      */     ValuesView<K, V> vs;
/* 1253 */     return ((vs = this.values) != null) ? vs : (this.values = new ValuesView<K, V>(this));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Map.Entry<K, V>> entrySet() {
/*      */     EntrySetView<K, V> es;
/* 1275 */     return ((es = this.entrySet) != null) ? es : (this.entrySet = new EntrySetView<K, V>(this));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1286 */     int h = 0;
/*      */     Node<K, V>[] t;
/* 1288 */     if ((t = this.table) != null) {
/* 1289 */       Traverser<K, V> it = new Traverser<K, V>(t, t.length, 0, t.length); Node<K, V> p;
/* 1290 */       while ((p = it.advance()) != null)
/* 1291 */         h += p.key.hashCode() ^ p.val.hashCode(); 
/*      */     } 
/* 1293 */     return h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*      */     Node<K, V>[] t;
/* 1309 */     int f = ((t = this.table) == null) ? 0 : t.length;
/* 1310 */     Traverser<K, V> it = new Traverser<K, V>(t, f, 0, f);
/* 1311 */     StringBuilder sb = new StringBuilder();
/* 1312 */     sb.append('{');
/*      */     Node<K, V> p;
/* 1314 */     if ((p = it.advance()) != null) {
/*      */       while (true) {
/* 1316 */         K k = p.key;
/* 1317 */         V v = p.val;
/* 1318 */         sb.append((k == this) ? "(this Map)" : k);
/* 1319 */         sb.append('=');
/* 1320 */         sb.append((v == this) ? "(this Map)" : v);
/* 1321 */         if ((p = it.advance()) == null)
/*      */           break; 
/* 1323 */         sb.append(',').append(' ');
/*      */       } 
/*      */     }
/* 1326 */     return sb.append('}').toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/* 1340 */     if (o != this) {
/* 1341 */       if (!(o instanceof Map))
/* 1342 */         return false; 
/* 1343 */       Map<?, ?> m = (Map<?, ?>)o;
/*      */       Node<K, V>[] t;
/* 1345 */       int f = ((t = this.table) == null) ? 0 : t.length;
/* 1346 */       Traverser<K, V> it = new Traverser<K, V>(t, f, 0, f); Node<K, V> p;
/* 1347 */       while ((p = it.advance()) != null) {
/* 1348 */         V val = p.val;
/* 1349 */         Object v = m.get(p.key);
/* 1350 */         if (v == null || (v != val && !v.equals(val)))
/* 1351 */           return false; 
/*      */       } 
/* 1353 */       for (Map.Entry<?, ?> e : m.entrySet()) {
/*      */         Object mk; Object mv; Object v;
/* 1355 */         if ((mk = e.getKey()) == null || (mv = e.getValue()) == null || (v = get(mk)) == null || (mv != v && !mv.equals(v)))
/*      */         {
/*      */ 
/*      */           
/* 1359 */           return false; } 
/*      */       } 
/*      */     } 
/* 1362 */     return true;
/*      */   }
/*      */   
/*      */   static class Segment<K, V>
/*      */     extends ReentrantLock
/*      */     implements Serializable {
/*      */     private static final long serialVersionUID = 2249069246763182397L;
/*      */     final float loadFactor;
/*      */     
/*      */     Segment(float lf) {
/* 1372 */       this.loadFactor = lf;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1388 */     int sshift = 0;
/* 1389 */     int ssize = 1;
/* 1390 */     while (ssize < 16) {
/* 1391 */       sshift++;
/* 1392 */       ssize <<= 1;
/*      */     } 
/* 1394 */     int segmentShift = 32 - sshift;
/* 1395 */     int segmentMask = ssize - 1;
/* 1396 */     Segment[] arrayOfSegment = new Segment[16];
/*      */     
/* 1398 */     for (int i = 0; i < arrayOfSegment.length; i++)
/* 1399 */       arrayOfSegment[i] = new Segment<Object, Object>(0.75F); 
/* 1400 */     s.putFields().put("segments", arrayOfSegment);
/* 1401 */     s.putFields().put("segmentShift", segmentShift);
/* 1402 */     s.putFields().put("segmentMask", segmentMask);
/* 1403 */     s.writeFields();
/*      */     
/*      */     Node<K, V>[] t;
/* 1406 */     if ((t = this.table) != null) {
/* 1407 */       Traverser<K, V> it = new Traverser<K, V>(t, t.length, 0, t.length); Node<K, V> p;
/* 1408 */       while ((p = it.advance()) != null) {
/* 1409 */         s.writeObject(p.key);
/* 1410 */         s.writeObject(p.val);
/*      */       } 
/*      */     } 
/* 1413 */     s.writeObject(null);
/* 1414 */     s.writeObject(null);
/* 1415 */     arrayOfSegment = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1431 */     this.sizeCtl = -1;
/* 1432 */     s.defaultReadObject();
/* 1433 */     long size = 0L;
/* 1434 */     Node<K, V> p = null;
/*      */     while (true) {
/* 1436 */       K k = (K)s.readObject();
/* 1437 */       V v = (V)s.readObject();
/* 1438 */       if (k != null && v != null) {
/* 1439 */         p = new Node<K, V>(spread(k.hashCode()), k, v, p);
/* 1440 */         size++;
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 1445 */     if (size == 0L) {
/* 1446 */       this.sizeCtl = 0;
/*      */     } else {
/*      */       int n;
/* 1449 */       if (size >= 536870912L) {
/* 1450 */         n = 1073741824;
/*      */       } else {
/* 1452 */         int sz = (int)size;
/* 1453 */         n = tableSizeFor(sz + (sz >>> 1) + 1);
/*      */       } 
/*      */       
/* 1456 */       Node[] arrayOfNode = new Node[n];
/* 1457 */       int mask = n - 1;
/* 1458 */       long added = 0L;
/* 1459 */       while (p != null) {
/*      */         boolean insertAtFront;
/* 1461 */         Node<K, V> next = p.next;
/* 1462 */         int h = p.hash, j = h & mask; Node<K, V> first;
/* 1463 */         if ((first = tabAt((Node<?, ?>[])arrayOfNode, j)) == null) {
/* 1464 */           insertAtFront = true;
/*      */         } else {
/* 1466 */           K k = p.key;
/* 1467 */           if (first.hash < 0) {
/* 1468 */             TreeBin<K, V> t = (TreeBin<K, V>)first;
/* 1469 */             if (t.putTreeVal(h, k, p.val) == null)
/* 1470 */               added++; 
/* 1471 */             insertAtFront = false;
/*      */           } else {
/*      */             
/* 1474 */             int binCount = 0;
/* 1475 */             insertAtFront = true;
/*      */             Node<K, V> q;
/* 1477 */             for (q = first; q != null; q = q.next) {
/* 1478 */               K qk; if (q.hash == h && ((qk = q.key) == k || (qk != null && k.equals(qk)))) {
/*      */ 
/*      */                 
/* 1481 */                 insertAtFront = false;
/*      */                 break;
/*      */               } 
/* 1484 */               binCount++;
/*      */             } 
/* 1486 */             if (insertAtFront && binCount >= 8) {
/* 1487 */               insertAtFront = false;
/* 1488 */               added++;
/* 1489 */               p.next = first;
/* 1490 */               TreeNode<K, V> hd = null, tl = null;
/* 1491 */               for (q = p; q != null; q = q.next) {
/* 1492 */                 TreeNode<K, V> t = new TreeNode<K, V>(q.hash, q.key, q.val, null, null);
/*      */                 
/* 1494 */                 if ((t.prev = tl) == null) {
/* 1495 */                   hd = t;
/*      */                 } else {
/* 1497 */                   tl.next = t;
/* 1498 */                 }  tl = t;
/*      */               } 
/* 1500 */               setTabAt((Node<?, ?>[])arrayOfNode, j, new TreeBin<Object, Object>(hd));
/*      */             } 
/*      */           } 
/*      */         } 
/* 1504 */         if (insertAtFront) {
/* 1505 */           added++;
/* 1506 */           p.next = first;
/* 1507 */           setTabAt((Node<K, V>[])arrayOfNode, j, p);
/*      */         } 
/* 1509 */         p = next;
/*      */       } 
/* 1511 */       this.table = (Node<K, V>[])arrayOfNode;
/* 1512 */       this.sizeCtl = n - (n >>> 2);
/* 1513 */       this.baseCount = added;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V putIfAbsent(K key, V value) {
/* 1527 */     return putVal(key, value, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(Object key, Object value) {
/* 1536 */     if (key == null)
/* 1537 */       throw new NullPointerException(); 
/* 1538 */     return (value != null && replaceNode(key, null, value) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean replace(K key, V oldValue, V newValue) {
/* 1547 */     if (key == null || oldValue == null || newValue == null)
/* 1548 */       throw new NullPointerException(); 
/* 1549 */     return (replaceNode(key, newValue, oldValue) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V replace(K key, V value) {
/* 1560 */     if (key == null || value == null)
/* 1561 */       throw new NullPointerException(); 
/* 1562 */     return replaceNode(key, value, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V getOrDefault(Object key, V defaultValue) {
/*      */     V v;
/* 1580 */     return ((v = get(key)) == null) ? defaultValue : v;
/*      */   }
/*      */   
/*      */   public void forEach(BiAction<? super K, ? super V> action) {
/* 1584 */     if (action == null) throw new NullPointerException(); 
/*      */     Node<K, V>[] t;
/* 1586 */     if ((t = this.table) != null) {
/* 1587 */       Traverser<K, V> it = new Traverser<K, V>(t, t.length, 0, t.length); Node<K, V> p;
/* 1588 */       while ((p = it.advance()) != null) {
/* 1589 */         action.apply(p.key, p.val);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void replaceAll(BiFun<? super K, ? super V, ? extends V> function) {
/* 1595 */     if (function == null) throw new NullPointerException(); 
/*      */     Node<K, V>[] t;
/* 1597 */     if ((t = this.table) != null) {
/* 1598 */       Traverser<K, V> it = new Traverser<K, V>(t, t.length, 0, t.length); Node<K, V> p;
/* 1599 */       label22: while ((p = it.advance()) != null) {
/* 1600 */         V oldValue = p.val;
/* 1601 */         K key = p.key; while (true) {
/* 1602 */           V newValue = function.apply(key, oldValue);
/* 1603 */           if (newValue == null)
/* 1604 */             throw new NullPointerException(); 
/* 1605 */           if (replaceNode(key, newValue, oldValue) == null) { if ((oldValue = get(key)) == null) {
/*      */               continue label22;
/*      */             }
/*      */             continue; }
/*      */           
/*      */           continue label22;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V computeIfAbsent(K key, Fun<? super K, ? extends V> mappingFunction) {
/* 1636 */     if (key == null || mappingFunction == null)
/* 1637 */       throw new NullPointerException(); 
/* 1638 */     int h = spread(key.hashCode());
/* 1639 */     V val = null;
/* 1640 */     int binCount = 0;
/* 1641 */     Node<K, V>[] tab = this.table; while (true) {
/*      */       Node[] arrayOfNode; int n;
/* 1643 */       if (tab == null || (n = tab.length) == 0) {
/* 1644 */         arrayOfNode = (Node[])initTable(); continue;
/* 1645 */       }  Node<K, V> f; int i; if ((f = tabAt((Node<?, ?>[])arrayOfNode, i = n - 1 & h)) == null) {
/* 1646 */         Node<K, V> r = new ReservationNode<K, V>();
/* 1647 */         synchronized (r) {
/* 1648 */           if (casTabAt((Node<K, V>[])arrayOfNode, i, null, r)) {
/* 1649 */             binCount = 1;
/* 1650 */             Node<K, V> node = null;
/*      */             try {
/* 1652 */               if ((val = mappingFunction.apply(key)) != null)
/* 1653 */                 node = new Node<K, V>(h, key, val, null); 
/*      */             } finally {
/* 1655 */               setTabAt((Node<K, V>[])arrayOfNode, i, node);
/*      */             } 
/*      */           } 
/*      */         } 
/* 1659 */         if (binCount != 0)
/*      */           break;  continue;
/*      */       }  int fh;
/* 1662 */       if ((fh = f.hash) == -1) {
/* 1663 */         arrayOfNode = (Node[])helpTransfer((Node<K, V>[])arrayOfNode, f); continue;
/*      */       } 
/* 1665 */       boolean added = false;
/* 1666 */       synchronized (f) {
/* 1667 */         if (tabAt((Node<K, V>[])arrayOfNode, i) == f) {
/* 1668 */           if (fh >= 0) {
/* 1669 */             binCount = 1;
/* 1670 */             for (Node<K, V> e = f;; binCount++) {
/*      */               K ek;
/* 1672 */               if (e.hash == h && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
/*      */ 
/*      */                 
/* 1675 */                 val = e.val;
/*      */                 break;
/*      */               } 
/* 1678 */               Node<K, V> pred = e;
/* 1679 */               if ((e = e.next) == null) {
/* 1680 */                 if ((val = mappingFunction.apply(key)) != null) {
/* 1681 */                   added = true;
/* 1682 */                   pred.next = new Node<K, V>(h, key, val, null);
/*      */                 } 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1688 */           } else if (f instanceof TreeBin) {
/* 1689 */             binCount = 2;
/* 1690 */             TreeBin<K, V> t = (TreeBin<K, V>)f;
/*      */             TreeNode<K, V> r, p;
/* 1692 */             if ((r = t.root) != null && (p = r.findTreeNode(h, key, (Class<?>)null)) != null) {
/*      */               
/* 1694 */               val = p.val;
/* 1695 */             } else if ((val = mappingFunction.apply(key)) != null) {
/* 1696 */               added = true;
/* 1697 */               t.putTreeVal(h, key, val);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/* 1702 */       if (binCount != 0) {
/* 1703 */         if (binCount >= 8)
/* 1704 */           treeifyBin((Node<K, V>[])arrayOfNode, i); 
/* 1705 */         if (!added) {
/* 1706 */           return val;
/*      */         }
/*      */         break;
/*      */       } 
/*      */     } 
/* 1711 */     if (val != null)
/* 1712 */       addCount(1L, binCount); 
/* 1713 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V computeIfPresent(K key, BiFun<? super K, ? super V, ? extends V> remappingFunction) {
/* 1737 */     if (key == null || remappingFunction == null)
/* 1738 */       throw new NullPointerException(); 
/* 1739 */     int h = spread(key.hashCode());
/* 1740 */     V val = null;
/* 1741 */     int delta = 0;
/* 1742 */     int binCount = 0;
/* 1743 */     Node<K, V>[] tab = this.table; while (true) {
/*      */       Node[] arrayOfNode; int n;
/* 1745 */       if (tab == null || (n = tab.length) == 0) {
/* 1746 */         arrayOfNode = (Node[])initTable(); continue;
/* 1747 */       }  Node<K, V> f; int i; if ((f = tabAt((Node<?, ?>[])arrayOfNode, i = n - 1 & h)) == null)
/*      */         break;  int fh;
/* 1749 */       if ((fh = f.hash) == -1) {
/* 1750 */         arrayOfNode = (Node[])helpTransfer((Node<K, V>[])arrayOfNode, f); continue;
/*      */       } 
/* 1752 */       synchronized (f) {
/* 1753 */         if (tabAt((Node<K, V>[])arrayOfNode, i) == f)
/* 1754 */           if (fh >= 0) {
/* 1755 */             binCount = 1;
/* 1756 */             for (Node<K, V> e = f, pred = null;; binCount++) {
/*      */               K ek;
/* 1758 */               if (e.hash == h && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
/*      */ 
/*      */                 
/* 1761 */                 val = remappingFunction.apply(key, e.val);
/* 1762 */                 if (val != null) {
/* 1763 */                   e.val = val; break;
/*      */                 } 
/* 1765 */                 delta = -1;
/* 1766 */                 Node<K, V> en = e.next;
/* 1767 */                 if (pred != null) {
/* 1768 */                   pred.next = en; break;
/*      */                 } 
/* 1770 */                 setTabAt((Node<K, V>[])arrayOfNode, i, en);
/*      */                 
/*      */                 break;
/*      */               } 
/* 1774 */               pred = e;
/* 1775 */               if ((e = e.next) == null) {
/*      */                 break;
/*      */               }
/*      */             } 
/*      */           } else {
/* 1780 */             binCount = 2;
/* 1781 */             TreeBin<K, V> t = (TreeBin<K, V>)f;
/*      */             TreeNode<K, V> r, p;
/* 1783 */             if (f instanceof TreeBin && (r = t.root) != null && (p = r.findTreeNode(h, key, (Class<?>)null)) != null) {
/*      */               
/* 1785 */               val = remappingFunction.apply(key, p.val);
/* 1786 */               if (val != null) {
/* 1787 */                 p.val = val;
/*      */               } else {
/* 1789 */                 delta = -1;
/* 1790 */                 if (t.removeTreeNode(p)) {
/* 1791 */                   setTabAt((Node<?, ?>[])arrayOfNode, i, untreeify(t.first));
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           }  
/*      */       } 
/* 1797 */       if (binCount != 0) {
/*      */         break;
/*      */       }
/*      */     } 
/* 1801 */     if (delta != 0)
/* 1802 */       addCount(delta, binCount); 
/* 1803 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V compute(K key, BiFun<? super K, ? super V, ? extends V> remappingFunction) {
/* 1828 */     if (key == null || remappingFunction == null)
/* 1829 */       throw new NullPointerException(); 
/* 1830 */     int h = spread(key.hashCode());
/* 1831 */     V val = null;
/* 1832 */     int delta = 0;
/* 1833 */     int binCount = 0;
/* 1834 */     Node<K, V>[] tab = this.table; while (true) {
/*      */       Node[] arrayOfNode; int n;
/* 1836 */       if (tab == null || (n = tab.length) == 0) {
/* 1837 */         arrayOfNode = (Node[])initTable(); continue;
/* 1838 */       }  Node<K, V> f; int i; if ((f = tabAt((Node<?, ?>[])arrayOfNode, i = n - 1 & h)) == null) {
/* 1839 */         Node<K, V> r = new ReservationNode<K, V>();
/* 1840 */         synchronized (r) {
/* 1841 */           if (casTabAt((Node<K, V>[])arrayOfNode, i, null, r)) {
/* 1842 */             binCount = 1;
/* 1843 */             Node<K, V> node = null;
/*      */             try {
/* 1845 */               if ((val = remappingFunction.apply(key, null)) != null) {
/* 1846 */                 delta = 1;
/* 1847 */                 node = new Node<K, V>(h, key, val, null);
/*      */               } 
/*      */             } finally {
/* 1850 */               setTabAt((Node<K, V>[])arrayOfNode, i, node);
/*      */             } 
/*      */           } 
/*      */         } 
/* 1854 */         if (binCount != 0)
/*      */           break;  continue;
/*      */       }  int fh;
/* 1857 */       if ((fh = f.hash) == -1) {
/* 1858 */         arrayOfNode = (Node[])helpTransfer((Node<K, V>[])arrayOfNode, f); continue;
/*      */       } 
/* 1860 */       synchronized (f) {
/* 1861 */         if (tabAt((Node<K, V>[])arrayOfNode, i) == f)
/* 1862 */           if (fh >= 0) {
/* 1863 */             binCount = 1;
/* 1864 */             for (Node<K, V> e = f, pred = null;; binCount++) {
/*      */               K ek;
/* 1866 */               if (e.hash == h && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
/*      */ 
/*      */                 
/* 1869 */                 val = remappingFunction.apply(key, e.val);
/* 1870 */                 if (val != null) {
/* 1871 */                   e.val = val; break;
/*      */                 } 
/* 1873 */                 delta = -1;
/* 1874 */                 Node<K, V> en = e.next;
/* 1875 */                 if (pred != null) {
/* 1876 */                   pred.next = en; break;
/*      */                 } 
/* 1878 */                 setTabAt((Node<K, V>[])arrayOfNode, i, en);
/*      */                 
/*      */                 break;
/*      */               } 
/* 1882 */               pred = e;
/* 1883 */               if ((e = e.next) == null) {
/* 1884 */                 val = remappingFunction.apply(key, null);
/* 1885 */                 if (val != null) {
/* 1886 */                   delta = 1;
/* 1887 */                   pred.next = new Node<K, V>(h, key, val, null);
/*      */                 } 
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1894 */           } else if (f instanceof TreeBin) {
/* 1895 */             TreeNode<K, V> p; binCount = 1;
/* 1896 */             TreeBin<K, V> t = (TreeBin<K, V>)f;
/*      */             TreeNode<K, V> r;
/* 1898 */             if ((r = t.root) != null) {
/* 1899 */               p = r.findTreeNode(h, key, (Class<?>)null);
/*      */             } else {
/* 1901 */               p = null;
/* 1902 */             }  V pv = (p == null) ? null : p.val;
/* 1903 */             val = remappingFunction.apply(key, pv);
/* 1904 */             if (val != null) {
/* 1905 */               if (p != null) {
/* 1906 */                 p.val = val;
/*      */               } else {
/* 1908 */                 delta = 1;
/* 1909 */                 t.putTreeVal(h, key, val);
/*      */               }
/*      */             
/* 1912 */             } else if (p != null) {
/* 1913 */               delta = -1;
/* 1914 */               if (t.removeTreeNode(p)) {
/* 1915 */                 setTabAt((Node<?, ?>[])arrayOfNode, i, untreeify(t.first));
/*      */               }
/*      */             } 
/*      */           }  
/*      */       } 
/* 1920 */       if (binCount != 0) {
/* 1921 */         if (binCount >= 8) {
/* 1922 */           treeifyBin((Node<K, V>[])arrayOfNode, i);
/*      */         }
/*      */         break;
/*      */       } 
/*      */     } 
/* 1927 */     if (delta != 0)
/* 1928 */       addCount(delta, binCount); 
/* 1929 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V merge(K key, V value, BiFun<? super V, ? super V, ? extends V> remappingFunction) {
/* 1953 */     if (key == null || value == null || remappingFunction == null)
/* 1954 */       throw new NullPointerException(); 
/* 1955 */     int h = spread(key.hashCode());
/* 1956 */     V val = null;
/* 1957 */     int delta = 0;
/* 1958 */     int binCount = 0;
/* 1959 */     Node<K, V>[] tab = this.table; while (true) {
/*      */       Node[] arrayOfNode; int n;
/* 1961 */       if (tab == null || (n = tab.length) == 0) {
/* 1962 */         arrayOfNode = (Node[])initTable(); continue;
/* 1963 */       }  Node<K, V> f; int i; if ((f = tabAt((Node<?, ?>[])arrayOfNode, i = n - 1 & h)) == null) {
/* 1964 */         if (casTabAt((Node<?, ?>[])arrayOfNode, i, null, new Node<Object, Object>(h, key, value, null))) {
/* 1965 */           delta = 1;
/* 1966 */           val = value; break;
/*      */         }  continue;
/*      */       } 
/*      */       int fh;
/* 1970 */       if ((fh = f.hash) == -1) {
/* 1971 */         arrayOfNode = (Node[])helpTransfer((Node<K, V>[])arrayOfNode, f); continue;
/*      */       } 
/* 1973 */       synchronized (f) {
/* 1974 */         if (tabAt((Node<K, V>[])arrayOfNode, i) == f)
/* 1975 */           if (fh >= 0) {
/* 1976 */             binCount = 1;
/* 1977 */             for (Node<K, V> e = f, pred = null;; binCount++) {
/*      */               K ek;
/* 1979 */               if (e.hash == h && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
/*      */ 
/*      */                 
/* 1982 */                 val = remappingFunction.apply(e.val, value);
/* 1983 */                 if (val != null) {
/* 1984 */                   e.val = val; break;
/*      */                 } 
/* 1986 */                 delta = -1;
/* 1987 */                 Node<K, V> en = e.next;
/* 1988 */                 if (pred != null) {
/* 1989 */                   pred.next = en; break;
/*      */                 } 
/* 1991 */                 setTabAt((Node<K, V>[])arrayOfNode, i, en);
/*      */                 
/*      */                 break;
/*      */               } 
/* 1995 */               pred = e;
/* 1996 */               if ((e = e.next) == null) {
/* 1997 */                 delta = 1;
/* 1998 */                 val = value;
/* 1999 */                 pred.next = new Node<K, V>(h, key, val, null);
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 2005 */           } else if (f instanceof TreeBin) {
/* 2006 */             binCount = 2;
/* 2007 */             TreeBin<K, V> t = (TreeBin<K, V>)f;
/* 2008 */             TreeNode<K, V> r = t.root;
/* 2009 */             TreeNode<K, V> p = (r == null) ? null : r.findTreeNode(h, key, (Class<?>)null);
/*      */             
/* 2011 */             val = (p == null) ? value : remappingFunction.apply(p.val, value);
/*      */             
/* 2013 */             if (val != null) {
/* 2014 */               if (p != null) {
/* 2015 */                 p.val = val;
/*      */               } else {
/* 2017 */                 delta = 1;
/* 2018 */                 t.putTreeVal(h, key, val);
/*      */               }
/*      */             
/* 2021 */             } else if (p != null) {
/* 2022 */               delta = -1;
/* 2023 */               if (t.removeTreeNode(p)) {
/* 2024 */                 setTabAt((Node<?, ?>[])arrayOfNode, i, untreeify(t.first));
/*      */               }
/*      */             } 
/*      */           }  
/*      */       } 
/* 2029 */       if (binCount != 0) {
/* 2030 */         if (binCount >= 8) {
/* 2031 */           treeifyBin((Node<K, V>[])arrayOfNode, i);
/*      */         }
/*      */         break;
/*      */       } 
/*      */     } 
/* 2036 */     if (delta != 0)
/* 2037 */       addCount(delta, binCount); 
/* 2038 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean contains(Object value) {
/* 2059 */     return containsValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<K> keys() {
/*      */     Node<K, V>[] t;
/* 2070 */     int f = ((t = this.table) == null) ? 0 : t.length;
/* 2071 */     return new KeyIterator<K, V>(t, f, 0, f, this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<V> elements() {
/*      */     Node<K, V>[] t;
/* 2082 */     int f = ((t = this.table) == null) ? 0 : t.length;
/* 2083 */     return new ValueIterator<K, V>(t, f, 0, f, this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long mappingCount() {
/* 2099 */     long n = sumCount();
/* 2100 */     return (n < 0L) ? 0L : n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <K> KeySetView<K, Boolean> newKeySet() {
/* 2111 */     return new KeySetView<K, Boolean>(new ConcurrentHashMapV8<K, Boolean>(), Boolean.TRUE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <K> KeySetView<K, Boolean> newKeySet(int initialCapacity) {
/* 2127 */     return new KeySetView<K, Boolean>(new ConcurrentHashMapV8<K, Boolean>(initialCapacity), Boolean.TRUE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public KeySetView<K, V> keySet(V mappedValue) {
/* 2143 */     if (mappedValue == null)
/* 2144 */       throw new NullPointerException(); 
/* 2145 */     return new KeySetView<K, V>(this, mappedValue);
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForwardingNode<K, V>
/*      */     extends Node<K, V>
/*      */   {
/*      */     final ConcurrentHashMapV8.Node<K, V>[] nextTable;
/*      */ 
/*      */     
/*      */     ForwardingNode(ConcurrentHashMapV8.Node<K, V>[] tab) {
/* 2156 */       super(-1, null, null, null);
/* 2157 */       this.nextTable = tab;
/*      */     }
/*      */ 
/*      */     
/*      */     ConcurrentHashMapV8.Node<K, V> find(int h, Object k) {
/* 2162 */       ConcurrentHashMapV8.Node<K, V>[] tab = this.nextTable; label27: while (true) {
/*      */         ConcurrentHashMapV8.Node<K, V> e; int n;
/* 2164 */         if (k == null || tab == null || (n = tab.length) == 0 || (e = ConcurrentHashMapV8.<K, V>tabAt(tab, n - 1 & h)) == null)
/*      */         {
/* 2166 */           return null; }  while (true) {
/*      */           int eh;
/*      */           K ek;
/* 2169 */           if ((eh = e.hash) == h && ((ek = e.key) == k || (ek != null && k.equals(ek))))
/*      */           {
/* 2171 */             return e; } 
/* 2172 */           if (eh < 0) {
/* 2173 */             if (e instanceof ForwardingNode) {
/* 2174 */               tab = ((ForwardingNode)e).nextTable;
/*      */               
/*      */               continue label27;
/*      */             } 
/* 2178 */             return e.find(h, k);
/*      */           } 
/* 2180 */           if ((e = e.next) == null)
/* 2181 */             return null; 
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static final class ReservationNode<K, V>
/*      */     extends Node<K, V>
/*      */   {
/*      */     ReservationNode() {
/* 2192 */       super(-3, null, null, null);
/*      */     }
/*      */     
/*      */     ConcurrentHashMapV8.Node<K, V> find(int h, Object k) {
/* 2196 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Node<K, V>[] initTable() {
/*      */     Node[] arrayOfNode;
/*      */     Node<K, V>[] tab;
/* 2207 */     while ((tab = this.table) == null || tab.length == 0) {
/* 2208 */       int sc; if ((sc = this.sizeCtl) < 0) {
/* 2209 */         Thread.yield(); continue;
/* 2210 */       }  if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
/*      */         try {
/* 2212 */           if ((tab = this.table) == null || tab.length == 0) {
/* 2213 */             int n = (sc > 0) ? sc : 16;
/*      */             
/* 2215 */             Node[] arrayOfNode1 = new Node[n];
/* 2216 */             this.table = (Node<K, V>[])(arrayOfNode = arrayOfNode1);
/* 2217 */             sc = n - (n >>> 2);
/*      */           } 
/*      */         } finally {
/* 2220 */           this.sizeCtl = sc;
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     } 
/* 2225 */     return (Node<K, V>[])arrayOfNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void addCount(long x, int check) {
/*      */     CounterCell[] as;
/*      */     long b;
/*      */     long s;
/* 2240 */     if ((as = this.counterCells) != null || !U.compareAndSwapLong(this, BASECOUNT, b = this.baseCount, s = b + x)) {
/*      */ 
/*      */       
/* 2243 */       boolean uncontended = true;
/* 2244 */       InternalThreadLocalMap threadLocals = InternalThreadLocalMap.get(); IntegerHolder hc; CounterCell a; long v; int m;
/* 2245 */       if ((hc = threadLocals.counterHashCode()) == null || as == null || (m = as.length - 1) < 0 || (a = as[m & hc.value]) == null || !(uncontended = U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2250 */         fullAddCount(threadLocals, x, hc, uncontended);
/*      */         return;
/*      */       } 
/* 2253 */       if (check <= 1)
/*      */         return; 
/* 2255 */       s = sumCount();
/*      */     } 
/* 2257 */     if (check >= 0) {
/*      */       Node<K, V>[] tab; int sc;
/* 2259 */       while (s >= (sc = this.sizeCtl) && (tab = this.table) != null && tab.length < 1073741824) {
/*      */         
/* 2261 */         if (sc < 0) {
/* 2262 */           Node<K, V>[] nt; if (sc == -1 || this.transferIndex <= this.transferOrigin || (nt = this.nextTable) == null) {
/*      */             break;
/*      */           }
/* 2265 */           if (U.compareAndSwapInt(this, SIZECTL, sc, sc - 1)) {
/* 2266 */             transfer(tab, nt);
/*      */           }
/* 2268 */         } else if (U.compareAndSwapInt(this, SIZECTL, sc, -2)) {
/* 2269 */           transfer(tab, null);
/* 2270 */         }  s = sumCount();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final Node<K, V>[] helpTransfer(Node<K, V>[] tab, Node<K, V> f) {
/*      */     Node[] arrayOfNode;
/* 2280 */     if (f instanceof ForwardingNode && (arrayOfNode = ((ForwardingNode)f).nextTable) != null) {
/*      */       int sc;
/* 2282 */       if (arrayOfNode == this.nextTable && tab == this.table && this.transferIndex > this.transferOrigin && (sc = this.sizeCtl) < -1 && U.compareAndSwapInt(this, SIZECTL, sc, sc - 1))
/*      */       {
/*      */         
/* 2285 */         transfer(tab, (Node<K, V>[])arrayOfNode); } 
/* 2286 */       return (Node<K, V>[])arrayOfNode;
/*      */     } 
/* 2288 */     return this.table;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void tryPresize(int size) {
/* 2297 */     int c = (size >= 536870912) ? 1073741824 : tableSizeFor(size + (size >>> 1) + 1);
/*      */     
/*      */     int sc;
/* 2300 */     while ((sc = this.sizeCtl) >= 0) {
/* 2301 */       Node<K, V>[] tab = this.table; int n;
/* 2302 */       if (tab == null || (n = tab.length) == 0) {
/* 2303 */         n = (sc > c) ? sc : c;
/* 2304 */         if (U.compareAndSwapInt(this, SIZECTL, sc, -1))
/*      */           try {
/* 2306 */             if (this.table == tab) {
/*      */               
/* 2308 */               Node[] arrayOfNode = new Node[n];
/* 2309 */               this.table = (Node<K, V>[])arrayOfNode;
/* 2310 */               sc = n - (n >>> 2);
/*      */             } 
/*      */           } finally {
/* 2313 */             this.sizeCtl = sc;
/*      */           }  
/*      */         continue;
/*      */       } 
/* 2317 */       if (c <= sc || n >= 1073741824)
/*      */         break; 
/* 2319 */       if (tab == this.table && U.compareAndSwapInt(this, SIZECTL, sc, -2))
/*      */       {
/* 2321 */         transfer(tab, null);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final void transfer(Node<K, V>[] tab, Node<K, V>[] nextTab) {
/*      */     Node[] arrayOfNode;
/* 2330 */     int n = tab.length; int stride;
/* 2331 */     if ((stride = (NCPU > 1) ? ((n >>> 3) / NCPU) : n) < 16)
/* 2332 */       stride = 16; 
/* 2333 */     if (nextTab == null) {
/*      */       
/*      */       try {
/* 2336 */         Node[] arrayOfNode1 = new Node[n << 1];
/* 2337 */         arrayOfNode = arrayOfNode1;
/* 2338 */       } catch (Throwable ex) {
/* 2339 */         this.sizeCtl = Integer.MAX_VALUE;
/*      */         return;
/*      */       } 
/* 2342 */       this.nextTable = (Node<K, V>[])arrayOfNode;
/* 2343 */       this.transferOrigin = n;
/* 2344 */       this.transferIndex = n;
/* 2345 */       ForwardingNode<K, V> rev = new ForwardingNode<K, V>(tab);
/* 2346 */       for (int k = n; k > 0; ) {
/* 2347 */         int nextk = (k > stride) ? (k - stride) : 0; int m;
/* 2348 */         for (m = nextk; m < k; m++)
/* 2349 */           arrayOfNode[m] = rev; 
/* 2350 */         for (m = n + nextk; m < n + k; m++)
/* 2351 */           arrayOfNode[m] = rev; 
/* 2352 */         U.putOrderedInt(this, TRANSFERORIGIN, k = nextk);
/*      */       } 
/*      */     } 
/* 2355 */     int nextn = arrayOfNode.length;
/* 2356 */     ForwardingNode<K, V> fwd = new ForwardingNode<K, V>((Node<K, V>[])arrayOfNode);
/* 2357 */     boolean advance = true;
/* 2358 */     boolean finishing = false;
/* 2359 */     int i = 0, bound = 0;
/*      */     while (true) {
/* 2361 */       while (advance) {
/* 2362 */         if (--i >= bound || finishing) {
/* 2363 */           advance = false; continue;
/* 2364 */         }  int nextIndex; if ((nextIndex = this.transferIndex) <= this.transferOrigin) {
/* 2365 */           i = -1;
/* 2366 */           advance = false; continue;
/*      */         }  int nextBound;
/* 2368 */         if (U.compareAndSwapInt(this, TRANSFERINDEX, nextIndex, nextBound = (nextIndex > stride) ? (nextIndex - stride) : 0)) {
/*      */ 
/*      */ 
/*      */           
/* 2372 */           bound = nextBound;
/* 2373 */           i = nextIndex - 1;
/* 2374 */           advance = false;
/*      */         } 
/*      */       } 
/* 2377 */       if (i < 0 || i >= n || i + n >= nextn) {
/* 2378 */         if (finishing) {
/* 2379 */           this.nextTable = null;
/* 2380 */           this.table = (Node<K, V>[])arrayOfNode;
/* 2381 */           this.sizeCtl = (n << 1) - (n >>> 1); return;
/*      */         }  int sc;
/*      */         do {
/*      */         
/* 2385 */         } while (!U.compareAndSwapInt(this, SIZECTL, sc = this.sizeCtl, ++sc));
/* 2386 */         if (sc != -1)
/*      */           return; 
/* 2388 */         finishing = advance = true;
/* 2389 */         i = n;
/*      */         
/*      */         continue;
/*      */       } 
/*      */       Node<K, V> f;
/* 2394 */       if ((f = tabAt(tab, i)) == null) {
/* 2395 */         if (casTabAt(tab, i, null, fwd)) {
/* 2396 */           setTabAt((Node<?, ?>[])arrayOfNode, i, null);
/* 2397 */           setTabAt((Node<?, ?>[])arrayOfNode, i + n, null);
/* 2398 */           advance = true;
/*      */         }  continue;
/*      */       }  int fh;
/* 2401 */       if ((fh = f.hash) == -1) {
/* 2402 */         advance = true; continue;
/*      */       } 
/* 2404 */       synchronized (f) {
/* 2405 */         if (tabAt(tab, i) == f)
/*      */         {
/* 2407 */           if (fh >= 0) {
/* 2408 */             Node<K, V> ln, hn; int runBit = fh & n;
/* 2409 */             Node<K, V> lastRun = f; Node<K, V> p;
/* 2410 */             for (p = f.next; p != null; p = p.next) {
/* 2411 */               int b = p.hash & n;
/* 2412 */               if (b != runBit) {
/* 2413 */                 runBit = b;
/* 2414 */                 lastRun = p;
/*      */               } 
/*      */             } 
/* 2417 */             if (runBit == 0) {
/* 2418 */               ln = lastRun;
/* 2419 */               hn = null;
/*      */             } else {
/*      */               
/* 2422 */               hn = lastRun;
/* 2423 */               ln = null;
/*      */             } 
/* 2425 */             for (p = f; p != lastRun; p = p.next) {
/* 2426 */               int ph = p.hash; K pk = p.key; V pv = p.val;
/* 2427 */               if ((ph & n) == 0) {
/* 2428 */                 ln = new Node<K, V>(ph, pk, pv, ln);
/*      */               } else {
/* 2430 */                 hn = new Node<K, V>(ph, pk, pv, hn);
/*      */               } 
/* 2432 */             }  setTabAt((Node<K, V>[])arrayOfNode, i, ln);
/* 2433 */             setTabAt((Node<K, V>[])arrayOfNode, i + n, hn);
/* 2434 */             setTabAt(tab, i, fwd);
/* 2435 */             advance = true;
/*      */           }
/* 2437 */           else if (f instanceof TreeBin) {
/* 2438 */             TreeBin<K, V> t = (TreeBin<K, V>)f;
/* 2439 */             TreeNode<K, V> lo = null, loTail = null;
/* 2440 */             TreeNode<K, V> hi = null, hiTail = null;
/* 2441 */             int lc = 0, hc = 0;
/* 2442 */             for (Node<K, V> e = t.first; e != null; e = e.next) {
/* 2443 */               int h = e.hash;
/* 2444 */               TreeNode<K, V> p = new TreeNode<K, V>(h, e.key, e.val, null, null);
/*      */               
/* 2446 */               if ((h & n) == 0) {
/* 2447 */                 if ((p.prev = loTail) == null) {
/* 2448 */                   lo = p;
/*      */                 } else {
/* 2450 */                   loTail.next = p;
/* 2451 */                 }  loTail = p;
/* 2452 */                 lc++;
/*      */               } else {
/*      */                 
/* 2455 */                 if ((p.prev = hiTail) == null) {
/* 2456 */                   hi = p;
/*      */                 } else {
/* 2458 */                   hiTail.next = p;
/* 2459 */                 }  hiTail = p;
/* 2460 */                 hc++;
/*      */               } 
/*      */             } 
/* 2463 */             Node<K, V> ln = (lc <= 6) ? untreeify(lo) : ((hc != 0) ? new TreeBin<K, V>(lo) : t);
/*      */             
/* 2465 */             Node<K, V> hn = (hc <= 6) ? untreeify(hi) : ((lc != 0) ? new TreeBin<K, V>(hi) : t);
/*      */             
/* 2467 */             setTabAt((Node<K, V>[])arrayOfNode, i, ln);
/* 2468 */             setTabAt((Node<K, V>[])arrayOfNode, i + n, hn);
/* 2469 */             setTabAt(tab, i, fwd);
/* 2470 */             advance = true;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void treeifyBin(Node<K, V>[] tab, int index) {
/* 2486 */     if (tab != null) {
/* 2487 */       int n; if ((n = tab.length) < 64) {
/* 2488 */         int sc; if (tab == this.table && (sc = this.sizeCtl) >= 0 && U.compareAndSwapInt(this, SIZECTL, sc, -2))
/*      */         {
/* 2490 */           transfer(tab, null); } 
/*      */       } else {
/* 2492 */         Node<K, V> b; if ((b = tabAt(tab, index)) != null && b.hash >= 0) {
/* 2493 */           synchronized (b) {
/* 2494 */             if (tabAt(tab, index) == b) {
/* 2495 */               TreeNode<K, V> hd = null, tl = null;
/* 2496 */               for (Node<K, V> e = b; e != null; e = e.next) {
/* 2497 */                 TreeNode<K, V> p = new TreeNode<K, V>(e.hash, e.key, e.val, null, null);
/*      */ 
/*      */                 
/* 2500 */                 if ((p.prev = tl) == null) {
/* 2501 */                   hd = p;
/*      */                 } else {
/* 2503 */                   tl.next = p;
/* 2504 */                 }  tl = p;
/*      */               } 
/* 2506 */               setTabAt(tab, index, new TreeBin<K, V>(hd));
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static <K, V> Node<K, V> untreeify(Node<K, V> b) {
/* 2517 */     Node<K, V> hd = null, tl = null;
/* 2518 */     for (Node<K, V> q = b; q != null; q = q.next) {
/* 2519 */       Node<K, V> p = new Node<K, V>(q.hash, q.key, q.val, null);
/* 2520 */       if (tl == null) {
/* 2521 */         hd = p;
/*      */       } else {
/* 2523 */         tl.next = p;
/* 2524 */       }  tl = p;
/*      */     } 
/* 2526 */     return hd;
/*      */   }
/*      */ 
/*      */   
/*      */   static final class TreeNode<K, V>
/*      */     extends Node<K, V>
/*      */   {
/*      */     TreeNode<K, V> parent;
/*      */     
/*      */     TreeNode<K, V> left;
/*      */     
/*      */     TreeNode<K, V> right;
/*      */     
/*      */     TreeNode<K, V> prev;
/*      */     boolean red;
/*      */     
/*      */     TreeNode(int hash, K key, V val, ConcurrentHashMapV8.Node<K, V> next, TreeNode<K, V> parent) {
/* 2543 */       super(hash, key, val, next);
/* 2544 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     ConcurrentHashMapV8.Node<K, V> find(int h, Object k) {
/* 2548 */       return findTreeNode(h, k, (Class<?>)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final TreeNode<K, V> findTreeNode(int h, Object k, Class<?> kc) {
/* 2556 */       if (k != null) {
/* 2557 */         TreeNode<K, V> p = this;
/*      */         
/*      */         do {
/* 2560 */           TreeNode<K, V> pl = p.left, pr = p.right; int ph;
/* 2561 */           if ((ph = p.hash) > h)
/* 2562 */           { p = pl; }
/* 2563 */           else if (ph < h)
/* 2564 */           { p = pr; }
/* 2565 */           else { K pk; if ((pk = p.key) == k || (pk != null && k.equals(pk)))
/* 2566 */               return p; 
/* 2567 */             if (pl == null && pr == null)
/*      */               break;  int dir;
/* 2569 */             if ((kc != null || (kc = ConcurrentHashMapV8.comparableClassFor(k)) != null) && (dir = ConcurrentHashMapV8.compareComparables(kc, k, pk)) != 0)
/*      */             
/*      */             { 
/* 2572 */               p = (dir < 0) ? pl : pr; }
/* 2573 */             else if (pl == null)
/* 2574 */             { p = pr; }
/* 2575 */             else { TreeNode<K, V> q; if (pr == null || (q = pr.findTreeNode(h, k, kc)) == null)
/*      */               
/* 2577 */               { p = pl; }
/*      */               else
/* 2579 */               { return q; }  }  } 
/* 2580 */         } while (p != null);
/*      */       } 
/* 2582 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class TreeBin<K, V>
/*      */     extends Node<K, V>
/*      */   {
/*      */     ConcurrentHashMapV8.TreeNode<K, V> root;
/*      */     
/*      */     volatile ConcurrentHashMapV8.TreeNode<K, V> first;
/*      */     
/*      */     volatile Thread waiter;
/*      */     
/*      */     volatile int lockState;
/*      */     
/*      */     static final int WRITER = 1;
/*      */     
/*      */     static final int WAITER = 2;
/*      */     
/*      */     static final int READER = 4;
/*      */     
/*      */     private static final Unsafe U;
/*      */     
/*      */     private static final long LOCKSTATE;
/*      */     
/*      */     TreeBin(ConcurrentHashMapV8.TreeNode<K, V> b) {
/* 2609 */       super(-2, null, null, null);
/* 2610 */       this.first = b;
/* 2611 */       ConcurrentHashMapV8.TreeNode<K, V> r = null;
/* 2612 */       for (ConcurrentHashMapV8.TreeNode<K, V> x = b; x != null; x = next) {
/* 2613 */         ConcurrentHashMapV8.TreeNode<K, V> next = (ConcurrentHashMapV8.TreeNode<K, V>)x.next;
/* 2614 */         x.left = x.right = null;
/* 2615 */         if (r == null) {
/* 2616 */           x.parent = null;
/* 2617 */           x.red = false;
/* 2618 */           r = x;
/*      */         } else {
/*      */           
/* 2621 */           Object key = x.key;
/* 2622 */           int hash = x.hash;
/* 2623 */           Class<?> kc = null;
/* 2624 */           ConcurrentHashMapV8.TreeNode<K, V> p = r; while (true) {
/*      */             int dir, ph;
/* 2626 */             if ((ph = p.hash) > hash) {
/* 2627 */               dir = -1;
/* 2628 */             } else if (ph < hash) {
/* 2629 */               dir = 1;
/* 2630 */             } else if (kc != null || (kc = ConcurrentHashMapV8.comparableClassFor(key)) != null) {
/*      */               
/* 2632 */               dir = ConcurrentHashMapV8.compareComparables(kc, key, p.key);
/*      */             } else {
/* 2634 */               dir = 0;
/* 2635 */             }  ConcurrentHashMapV8.TreeNode<K, V> xp = p;
/* 2636 */             if ((p = (ConcurrentHashMapV8.TreeNode<K, V>)((dir <= 0) ? p.left : p.right)) == null) {
/* 2637 */               x.parent = xp;
/* 2638 */               if (dir <= 0) {
/* 2639 */                 xp.left = x;
/*      */               } else {
/* 2641 */                 xp.right = x;
/* 2642 */               }  r = balanceInsertion(r, x);
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 2648 */       this.root = r;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final void lockRoot() {
/* 2655 */       if (!U.compareAndSwapInt(this, LOCKSTATE, 0, 1)) {
/* 2656 */         contendedLock();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private final void unlockRoot() {
/* 2663 */       this.lockState = 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final void contendedLock() {
/* 2670 */       boolean waiting = false; while (true) {
/*      */         int s;
/* 2672 */         while (((s = this.lockState) & 0x1) == 0) {
/* 2673 */           if (U.compareAndSwapInt(this, LOCKSTATE, s, 1)) {
/* 2674 */             if (waiting)
/* 2675 */               this.waiter = null; 
/*      */             return;
/*      */           } 
/*      */         } 
/* 2679 */         if ((s & 0x2) == 0) {
/* 2680 */           if (U.compareAndSwapInt(this, LOCKSTATE, s, s | 0x2)) {
/* 2681 */             waiting = true;
/* 2682 */             this.waiter = Thread.currentThread();
/*      */           }  continue;
/*      */         } 
/* 2685 */         if (waiting) {
/* 2686 */           LockSupport.park(this);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final ConcurrentHashMapV8.Node<K, V> find(int h, Object k) {
/* 2696 */       if (k != null) {
/* 2697 */         for (ConcurrentHashMapV8.Node<K, V> e = this.first; e != null; e = e.next) {
/*      */           int s;
/* 2699 */           if (((s = this.lockState) & 0x3) != 0) {
/* 2700 */             K ek; if (e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek))))
/*      */             {
/* 2702 */               return e;
/*      */             }
/* 2704 */           } else if (U.compareAndSwapInt(this, LOCKSTATE, s, s + 4)) {
/*      */             ConcurrentHashMapV8.TreeNode<K, V> p;
/*      */             try {
/*      */               ConcurrentHashMapV8.TreeNode<K, V> r;
/* 2708 */               p = ((r = this.root) == null) ? null : r.findTreeNode(h, k, (Class<?>)null);
/*      */             } finally {
/*      */               int ls;
/*      */               do {
/*      */               
/* 2713 */               } while (!U.compareAndSwapInt(this, LOCKSTATE, ls = this.lockState, ls - 4));
/*      */               
/*      */               Thread w;
/* 2716 */               if (ls == 6 && (w = this.waiter) != null)
/* 2717 */                 LockSupport.unpark(w); 
/*      */             } 
/* 2719 */             return p;
/*      */           } 
/*      */         } 
/*      */       }
/* 2723 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final ConcurrentHashMapV8.TreeNode<K, V> putTreeVal(int h, K k, V v) {
/* 2731 */       Class<?> kc = null;
/* 2732 */       ConcurrentHashMapV8.TreeNode<K, V> p = this.root; while (true) {
/*      */         int dir;
/* 2734 */         if (p == null) {
/* 2735 */           this.first = this.root = new ConcurrentHashMapV8.TreeNode<K, V>(h, k, v, null, null); break;
/*      */         } 
/*      */         int ph;
/* 2738 */         if ((ph = p.hash) > h)
/* 2739 */         { dir = -1; }
/* 2740 */         else if (ph < h)
/* 2741 */         { dir = 1; }
/* 2742 */         else { K pk; if ((pk = p.key) == k || (pk != null && k.equals(pk)))
/* 2743 */             return p; 
/* 2744 */           if ((kc == null && (kc = ConcurrentHashMapV8.comparableClassFor(k)) == null) || (dir = ConcurrentHashMapV8.compareComparables(kc, k, pk)) == 0)
/*      */           {
/*      */             
/* 2747 */             if (p.left == null)
/* 2748 */             { dir = 1; }
/* 2749 */             else { ConcurrentHashMapV8.TreeNode<K, V> q; ConcurrentHashMapV8.TreeNode<K, V> pr; if ((pr = p.right) == null || (q = pr.findTreeNode(h, k, kc)) == null)
/*      */               
/* 2751 */               { dir = -1; }
/*      */               else
/* 2753 */               { return q; }  }
/*      */              }  }
/* 2755 */          ConcurrentHashMapV8.TreeNode<K, V> xp = p;
/* 2756 */         if ((p = (ConcurrentHashMapV8.TreeNode<K, V>)((dir < 0) ? p.left : p.right)) == null) {
/* 2757 */           ConcurrentHashMapV8.TreeNode<K, V> f = this.first;
/* 2758 */           ConcurrentHashMapV8.TreeNode<K, V> x = new ConcurrentHashMapV8.TreeNode<K, V>(h, k, v, f, xp);
/* 2759 */           if (f != null)
/* 2760 */             f.prev = x; 
/* 2761 */           if (dir < 0) {
/* 2762 */             xp.left = x;
/*      */           } else {
/* 2764 */             xp.right = x;
/* 2765 */           }  if (!xp.red) {
/* 2766 */             x.red = true; break;
/*      */           } 
/* 2768 */           lockRoot();
/*      */           try {
/* 2770 */             this.root = balanceInsertion(this.root, x);
/*      */           } finally {
/* 2772 */             unlockRoot();
/*      */           } 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 2778 */       assert checkInvariants(this.root);
/* 2779 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final boolean removeTreeNode(ConcurrentHashMapV8.TreeNode<K, V> p) {
/* 2793 */       ConcurrentHashMapV8.TreeNode<K, V> next = (ConcurrentHashMapV8.TreeNode<K, V>)p.next;
/* 2794 */       ConcurrentHashMapV8.TreeNode<K, V> pred = p.prev;
/*      */       
/* 2796 */       if (pred == null) {
/* 2797 */         this.first = next;
/*      */       } else {
/* 2799 */         pred.next = next;
/* 2800 */       }  if (next != null)
/* 2801 */         next.prev = pred; 
/* 2802 */       if (this.first == null) {
/* 2803 */         this.root = null;
/* 2804 */         return true;
/*      */       }  ConcurrentHashMapV8.TreeNode<K, V> r, rl;
/* 2806 */       if ((r = this.root) == null || r.right == null || (rl = r.left) == null || rl.left == null)
/*      */       {
/* 2808 */         return true; } 
/* 2809 */       lockRoot();
/*      */       
/*      */       try {
/* 2812 */         ConcurrentHashMapV8.TreeNode<K, V> replacement, pl = p.left;
/* 2813 */         ConcurrentHashMapV8.TreeNode<K, V> pr = p.right;
/* 2814 */         if (pl != null && pr != null) {
/* 2815 */           ConcurrentHashMapV8.TreeNode<K, V> s = pr; ConcurrentHashMapV8.TreeNode<K, V> sl;
/* 2816 */           while ((sl = s.left) != null)
/* 2817 */             s = sl; 
/* 2818 */           boolean c = s.red; s.red = p.red; p.red = c;
/* 2819 */           ConcurrentHashMapV8.TreeNode<K, V> sr = s.right;
/* 2820 */           ConcurrentHashMapV8.TreeNode<K, V> pp = p.parent;
/* 2821 */           if (s == pr) {
/* 2822 */             p.parent = s;
/* 2823 */             s.right = p;
/*      */           } else {
/*      */             
/* 2826 */             ConcurrentHashMapV8.TreeNode<K, V> sp = s.parent;
/* 2827 */             if ((p.parent = sp) != null)
/* 2828 */               if (s == sp.left) {
/* 2829 */                 sp.left = p;
/*      */               } else {
/* 2831 */                 sp.right = p;
/*      */               }  
/* 2833 */             s.right = pr;
/* 2834 */             pr.parent = s;
/*      */           } 
/* 2836 */           p.left = null;
/* 2837 */           s.left = pl;
/* 2838 */           pl.parent = s;
/* 2839 */           if ((p.right = sr) != null)
/* 2840 */             sr.parent = p; 
/* 2841 */           if ((s.parent = pp) == null) {
/* 2842 */             r = s;
/* 2843 */           } else if (p == pp.left) {
/* 2844 */             pp.left = s;
/*      */           } else {
/* 2846 */             pp.right = s;
/* 2847 */           }  if (sr != null) {
/* 2848 */             replacement = sr;
/*      */           } else {
/* 2850 */             replacement = p;
/*      */           } 
/* 2852 */         } else if (pl != null) {
/* 2853 */           replacement = pl;
/* 2854 */         } else if (pr != null) {
/* 2855 */           replacement = pr;
/*      */         } else {
/* 2857 */           replacement = p;
/* 2858 */         }  if (replacement != p) {
/* 2859 */           ConcurrentHashMapV8.TreeNode<K, V> pp = replacement.parent = p.parent;
/* 2860 */           if (pp == null) {
/* 2861 */             r = replacement;
/* 2862 */           } else if (p == pp.left) {
/* 2863 */             pp.left = replacement;
/*      */           } else {
/* 2865 */             pp.right = replacement;
/* 2866 */           }  p.left = p.right = p.parent = null;
/*      */         } 
/*      */         
/* 2869 */         this.root = p.red ? r : balanceDeletion(r, replacement);
/*      */         
/* 2871 */         if (p == replacement) {
/*      */           ConcurrentHashMapV8.TreeNode<K, V> pp;
/* 2873 */           if ((pp = p.parent) != null) {
/* 2874 */             if (p == pp.left) {
/* 2875 */               pp.left = null;
/* 2876 */             } else if (p == pp.right) {
/* 2877 */               pp.right = null;
/* 2878 */             }  p.parent = null;
/*      */           } 
/*      */         } 
/*      */       } finally {
/* 2882 */         unlockRoot();
/*      */       } 
/* 2884 */       assert checkInvariants(this.root);
/* 2885 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static <K, V> ConcurrentHashMapV8.TreeNode<K, V> rotateLeft(ConcurrentHashMapV8.TreeNode<K, V> root, ConcurrentHashMapV8.TreeNode<K, V> p) {
/*      */       ConcurrentHashMapV8.TreeNode<K, V> r;
/* 2894 */       if (p != null && (r = p.right) != null) {
/* 2895 */         ConcurrentHashMapV8.TreeNode<K, V> rl; if ((rl = p.right = r.left) != null)
/* 2896 */           rl.parent = p;  ConcurrentHashMapV8.TreeNode<K, V> pp;
/* 2897 */         if ((pp = r.parent = p.parent) == null) {
/* 2898 */           (root = r).red = false;
/* 2899 */         } else if (pp.left == p) {
/* 2900 */           pp.left = r;
/*      */         } else {
/* 2902 */           pp.right = r;
/* 2903 */         }  r.left = p;
/* 2904 */         p.parent = r;
/*      */       } 
/* 2906 */       return root;
/*      */     }
/*      */ 
/*      */     
/*      */     static <K, V> ConcurrentHashMapV8.TreeNode<K, V> rotateRight(ConcurrentHashMapV8.TreeNode<K, V> root, ConcurrentHashMapV8.TreeNode<K, V> p) {
/*      */       ConcurrentHashMapV8.TreeNode<K, V> l;
/* 2912 */       if (p != null && (l = p.left) != null) {
/* 2913 */         ConcurrentHashMapV8.TreeNode<K, V> lr; if ((lr = p.left = l.right) != null)
/* 2914 */           lr.parent = p;  ConcurrentHashMapV8.TreeNode<K, V> pp;
/* 2915 */         if ((pp = l.parent = p.parent) == null) {
/* 2916 */           (root = l).red = false;
/* 2917 */         } else if (pp.right == p) {
/* 2918 */           pp.right = l;
/*      */         } else {
/* 2920 */           pp.left = l;
/* 2921 */         }  l.right = p;
/* 2922 */         p.parent = l;
/*      */       } 
/* 2924 */       return root;
/*      */     }
/*      */ 
/*      */     
/*      */     static <K, V> ConcurrentHashMapV8.TreeNode<K, V> balanceInsertion(ConcurrentHashMapV8.TreeNode<K, V> root, ConcurrentHashMapV8.TreeNode<K, V> x) {
/* 2929 */       x.red = true; while (true) {
/*      */         ConcurrentHashMapV8.TreeNode<K, V> xp;
/* 2931 */         if ((xp = x.parent) == null) {
/* 2932 */           x.red = false;
/* 2933 */           return x;
/*      */         }  ConcurrentHashMapV8.TreeNode<K, V> xpp;
/* 2935 */         if (!xp.red || (xpp = xp.parent) == null)
/* 2936 */           return root;  ConcurrentHashMapV8.TreeNode<K, V> xppl;
/* 2937 */         if (xp == (xppl = xpp.left)) {
/* 2938 */           ConcurrentHashMapV8.TreeNode<K, V> xppr; if ((xppr = xpp.right) != null && xppr.red) {
/* 2939 */             xppr.red = false;
/* 2940 */             xp.red = false;
/* 2941 */             xpp.red = true;
/* 2942 */             x = xpp;
/*      */             continue;
/*      */           } 
/* 2945 */           if (x == xp.right) {
/* 2946 */             root = rotateLeft(root, x = xp);
/* 2947 */             xpp = ((xp = x.parent) == null) ? null : xp.parent;
/*      */           } 
/* 2949 */           if (xp != null) {
/* 2950 */             xp.red = false;
/* 2951 */             if (xpp != null) {
/* 2952 */               xpp.red = true;
/* 2953 */               root = rotateRight(root, xpp);
/*      */             } 
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/* 2959 */         if (xppl != null && xppl.red) {
/* 2960 */           xppl.red = false;
/* 2961 */           xp.red = false;
/* 2962 */           xpp.red = true;
/* 2963 */           x = xpp;
/*      */           continue;
/*      */         } 
/* 2966 */         if (x == xp.left) {
/* 2967 */           root = rotateRight(root, x = xp);
/* 2968 */           xpp = ((xp = x.parent) == null) ? null : xp.parent;
/*      */         } 
/* 2970 */         if (xp != null) {
/* 2971 */           xp.red = false;
/* 2972 */           if (xpp != null) {
/* 2973 */             xpp.red = true;
/* 2974 */             root = rotateLeft(root, xpp);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static <K, V> ConcurrentHashMapV8.TreeNode<K, V> balanceDeletion(ConcurrentHashMapV8.TreeNode<K, V> root, ConcurrentHashMapV8.TreeNode<K, V> x) {
/*      */       while (true) {
/* 2985 */         if (x == null || x == root)
/* 2986 */           return root;  ConcurrentHashMapV8.TreeNode<K, V> xp;
/* 2987 */         if ((xp = x.parent) == null) {
/* 2988 */           x.red = false;
/* 2989 */           return x;
/*      */         } 
/* 2991 */         if (x.red) {
/* 2992 */           x.red = false;
/* 2993 */           return root;
/*      */         }  ConcurrentHashMapV8.TreeNode<K, V> xpl;
/* 2995 */         if ((xpl = xp.left) == x) {
/* 2996 */           ConcurrentHashMapV8.TreeNode<K, V> xpr; if ((xpr = xp.right) != null && xpr.red) {
/* 2997 */             xpr.red = false;
/* 2998 */             xp.red = true;
/* 2999 */             root = rotateLeft(root, xp);
/* 3000 */             xpr = ((xp = x.parent) == null) ? null : xp.right;
/*      */           } 
/* 3002 */           if (xpr == null) {
/* 3003 */             x = xp; continue;
/*      */           } 
/* 3005 */           ConcurrentHashMapV8.TreeNode<K, V> treeNode1 = xpr.left, treeNode2 = xpr.right;
/* 3006 */           if ((treeNode2 == null || !treeNode2.red) && (treeNode1 == null || !treeNode1.red)) {
/*      */             
/* 3008 */             xpr.red = true;
/* 3009 */             x = xp;
/*      */             continue;
/*      */           } 
/* 3012 */           if (treeNode2 == null || !treeNode2.red) {
/* 3013 */             if (treeNode1 != null)
/* 3014 */               treeNode1.red = false; 
/* 3015 */             xpr.red = true;
/* 3016 */             root = rotateRight(root, xpr);
/* 3017 */             xpr = ((xp = x.parent) == null) ? null : xp.right;
/*      */           } 
/*      */           
/* 3020 */           if (xpr != null) {
/* 3021 */             xpr.red = (xp == null) ? false : xp.red;
/* 3022 */             if ((treeNode2 = xpr.right) != null)
/* 3023 */               treeNode2.red = false; 
/*      */           } 
/* 3025 */           if (xp != null) {
/* 3026 */             xp.red = false;
/* 3027 */             root = rotateLeft(root, xp);
/*      */           } 
/* 3029 */           x = root;
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 3034 */         if (xpl != null && xpl.red) {
/* 3035 */           xpl.red = false;
/* 3036 */           xp.red = true;
/* 3037 */           root = rotateRight(root, xp);
/* 3038 */           xpl = ((xp = x.parent) == null) ? null : xp.left;
/*      */         } 
/* 3040 */         if (xpl == null) {
/* 3041 */           x = xp; continue;
/*      */         } 
/* 3043 */         ConcurrentHashMapV8.TreeNode<K, V> sl = xpl.left, sr = xpl.right;
/* 3044 */         if ((sl == null || !sl.red) && (sr == null || !sr.red)) {
/*      */           
/* 3046 */           xpl.red = true;
/* 3047 */           x = xp;
/*      */           continue;
/*      */         } 
/* 3050 */         if (sl == null || !sl.red) {
/* 3051 */           if (sr != null)
/* 3052 */             sr.red = false; 
/* 3053 */           xpl.red = true;
/* 3054 */           root = rotateLeft(root, xpl);
/* 3055 */           xpl = ((xp = x.parent) == null) ? null : xp.left;
/*      */         } 
/*      */         
/* 3058 */         if (xpl != null) {
/* 3059 */           xpl.red = (xp == null) ? false : xp.red;
/* 3060 */           if ((sl = xpl.left) != null)
/* 3061 */             sl.red = false; 
/*      */         } 
/* 3063 */         if (xp != null) {
/* 3064 */           xp.red = false;
/* 3065 */           root = rotateRight(root, xp);
/*      */         } 
/* 3067 */         x = root;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static <K, V> boolean checkInvariants(ConcurrentHashMapV8.TreeNode<K, V> t) {
/* 3078 */       ConcurrentHashMapV8.TreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right;
/* 3079 */       ConcurrentHashMapV8.TreeNode<K, V> tb = t.prev, tn = (ConcurrentHashMapV8.TreeNode<K, V>)t.next;
/* 3080 */       if (tb != null && tb.next != t)
/* 3081 */         return false; 
/* 3082 */       if (tn != null && tn.prev != t)
/* 3083 */         return false; 
/* 3084 */       if (tp != null && t != tp.left && t != tp.right)
/* 3085 */         return false; 
/* 3086 */       if (tl != null && (tl.parent != t || tl.hash > t.hash))
/* 3087 */         return false; 
/* 3088 */       if (tr != null && (tr.parent != t || tr.hash < t.hash))
/* 3089 */         return false; 
/* 3090 */       if (t.red && tl != null && tl.red && tr != null && tr.red)
/* 3091 */         return false; 
/* 3092 */       if (tl != null && !checkInvariants(tl))
/* 3093 */         return false; 
/* 3094 */       if (tr != null && !checkInvariants(tr))
/* 3095 */         return false; 
/* 3096 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 3103 */         U = ConcurrentHashMapV8.getUnsafe();
/* 3104 */         Class<?> k = TreeBin.class;
/* 3105 */         LOCKSTATE = U.objectFieldOffset(k.getDeclaredField("lockState"));
/*      */       }
/* 3107 */       catch (Exception e) {
/* 3108 */         throw new Error(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class Traverser<K, V>
/*      */   {
/*      */     ConcurrentHashMapV8.Node<K, V>[] tab;
/*      */ 
/*      */ 
/*      */     
/*      */     ConcurrentHashMapV8.Node<K, V> next;
/*      */ 
/*      */ 
/*      */     
/*      */     int index;
/*      */ 
/*      */ 
/*      */     
/*      */     int baseIndex;
/*      */ 
/*      */ 
/*      */     
/*      */     int baseLimit;
/*      */ 
/*      */ 
/*      */     
/*      */     final int baseSize;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Traverser(ConcurrentHashMapV8.Node<K, V>[] tab, int size, int index, int limit) {
/* 3145 */       this.tab = tab;
/* 3146 */       this.baseSize = size;
/* 3147 */       this.baseIndex = this.index = index;
/* 3148 */       this.baseLimit = limit;
/* 3149 */       this.next = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final ConcurrentHashMapV8.Node<K, V> advance() {
/*      */       ConcurrentHashMapV8.Node<K, V> e;
/* 3157 */       if ((e = this.next) != null) {
/* 3158 */         e = e.next;
/*      */       }
/*      */       while (true) {
/* 3161 */         if (e != null)
/* 3162 */           return this.next = e;  ConcurrentHashMapV8.Node<K, V>[] t; int i; int n;
/* 3163 */         if (this.baseIndex >= this.baseLimit || (t = this.tab) == null || (n = t.length) <= (i = this.index) || i < 0)
/*      */         {
/* 3165 */           return this.next = null; } 
/* 3166 */         if ((e = ConcurrentHashMapV8.<K, V>tabAt(t, this.index)) != null && e.hash < 0) {
/* 3167 */           if (e instanceof ConcurrentHashMapV8.ForwardingNode) {
/* 3168 */             this.tab = (ConcurrentHashMapV8.Node<K, V>[])((ConcurrentHashMapV8.ForwardingNode)e).nextTable;
/* 3169 */             e = null;
/*      */             continue;
/*      */           } 
/* 3172 */           if (e instanceof ConcurrentHashMapV8.TreeBin) {
/* 3173 */             e = ((ConcurrentHashMapV8.TreeBin)e).first;
/*      */           } else {
/* 3175 */             e = null;
/*      */           } 
/* 3177 */         }  if ((this.index += this.baseSize) >= n) {
/* 3178 */           this.index = ++this.baseIndex;
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class BaseIterator<K, V>
/*      */     extends Traverser<K, V>
/*      */   {
/*      */     final ConcurrentHashMapV8<K, V> map;
/*      */     ConcurrentHashMapV8.Node<K, V> lastReturned;
/*      */     
/*      */     BaseIterator(ConcurrentHashMapV8.Node<K, V>[] tab, int size, int index, int limit, ConcurrentHashMapV8<K, V> map) {
/* 3192 */       super(tab, size, index, limit);
/* 3193 */       this.map = map;
/* 3194 */       advance();
/*      */     }
/*      */     
/* 3197 */     public final boolean hasNext() { return (this.next != null); } public final boolean hasMoreElements() {
/* 3198 */       return (this.next != null);
/*      */     }
/*      */     public final void remove() {
/*      */       ConcurrentHashMapV8.Node<K, V> p;
/* 3202 */       if ((p = this.lastReturned) == null)
/* 3203 */         throw new IllegalStateException(); 
/* 3204 */       this.lastReturned = null;
/* 3205 */       this.map.replaceNode(p.key, null, null);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class KeyIterator<K, V>
/*      */     extends BaseIterator<K, V>
/*      */     implements Iterator<K>, Enumeration<K> {
/*      */     KeyIterator(ConcurrentHashMapV8.Node<K, V>[] tab, int index, int size, int limit, ConcurrentHashMapV8<K, V> map) {
/* 3213 */       super(tab, index, size, limit, map);
/*      */     }
/*      */     
/*      */     public final K next() {
/*      */       ConcurrentHashMapV8.Node<K, V> p;
/* 3218 */       if ((p = this.next) == null)
/* 3219 */         throw new NoSuchElementException(); 
/* 3220 */       K k = p.key;
/* 3221 */       this.lastReturned = p;
/* 3222 */       advance();
/* 3223 */       return k;
/*      */     }
/*      */     public final K nextElement() {
/* 3226 */       return next();
/*      */     }
/*      */   }
/*      */   
/*      */   static final class ValueIterator<K, V>
/*      */     extends BaseIterator<K, V> implements Iterator<V>, Enumeration<V> {
/*      */     ValueIterator(ConcurrentHashMapV8.Node<K, V>[] tab, int index, int size, int limit, ConcurrentHashMapV8<K, V> map) {
/* 3233 */       super(tab, index, size, limit, map);
/*      */     }
/*      */     
/*      */     public final V next() {
/*      */       ConcurrentHashMapV8.Node<K, V> p;
/* 3238 */       if ((p = this.next) == null)
/* 3239 */         throw new NoSuchElementException(); 
/* 3240 */       V v = p.val;
/* 3241 */       this.lastReturned = p;
/* 3242 */       advance();
/* 3243 */       return v;
/*      */     }
/*      */     public final V nextElement() {
/* 3246 */       return next();
/*      */     }
/*      */   }
/*      */   
/*      */   static final class EntryIterator<K, V>
/*      */     extends BaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {
/*      */     EntryIterator(ConcurrentHashMapV8.Node<K, V>[] tab, int index, int size, int limit, ConcurrentHashMapV8<K, V> map) {
/* 3253 */       super(tab, index, size, limit, map);
/*      */     }
/*      */     
/*      */     public final Map.Entry<K, V> next() {
/*      */       ConcurrentHashMapV8.Node<K, V> p;
/* 3258 */       if ((p = this.next) == null)
/* 3259 */         throw new NoSuchElementException(); 
/* 3260 */       K k = p.key;
/* 3261 */       V v = p.val;
/* 3262 */       this.lastReturned = p;
/* 3263 */       advance();
/* 3264 */       return new ConcurrentHashMapV8.MapEntry<K, V>(k, v, this.map);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class MapEntry<K, V>
/*      */     implements Map.Entry<K, V>
/*      */   {
/*      */     final K key;
/*      */     V val;
/*      */     final ConcurrentHashMapV8<K, V> map;
/*      */     
/*      */     MapEntry(K key, V val, ConcurrentHashMapV8<K, V> map) {
/* 3276 */       this.key = key;
/* 3277 */       this.val = val;
/* 3278 */       this.map = map;
/*      */     }
/* 3280 */     public K getKey() { return this.key; }
/* 3281 */     public V getValue() { return this.val; }
/* 3282 */     public int hashCode() { return this.key.hashCode() ^ this.val.hashCode(); }
/* 3283 */     public String toString() { return (new StringBuilder()).append(this.key).append("=").append(this.val).toString(); } public boolean equals(Object o) {
/*      */       Object k;
/*      */       Object v;
/*      */       Map.Entry<?, ?> e;
/* 3287 */       return (o instanceof Map.Entry && (k = (e = (Map.Entry<?, ?>)o).getKey()) != null && (v = e.getValue()) != null && (k == this.key || k.equals(this.key)) && (v == this.val || v.equals(this.val)));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public V setValue(V value) {
/* 3303 */       if (value == null) throw new NullPointerException(); 
/* 3304 */       V v = this.val;
/* 3305 */       this.val = value;
/* 3306 */       this.map.put(this.key, value);
/* 3307 */       return v;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class KeySpliterator<K, V>
/*      */     extends Traverser<K, V> implements ConcurrentHashMapSpliterator<K> {
/*      */     long est;
/*      */     
/*      */     KeySpliterator(ConcurrentHashMapV8.Node<K, V>[] tab, int size, int index, int limit, long est) {
/* 3316 */       super(tab, size, index, limit);
/* 3317 */       this.est = est;
/*      */     } public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<K> trySplit() {
/*      */       int i;
/*      */       int f;
/*      */       int h;
/* 3322 */       return ((h = (i = this.baseIndex) + (f = this.baseLimit) >>> 1) <= i) ? null : new KeySpliterator(this.tab, this.baseSize, this.baseLimit = h, f, this.est >>>= 1L);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void forEachRemaining(ConcurrentHashMapV8.Action<? super K> action) {
/* 3328 */       if (action == null) throw new NullPointerException();  ConcurrentHashMapV8.Node<K, V> p;
/* 3329 */       while ((p = advance()) != null)
/* 3330 */         action.apply(p.key); 
/*      */     }
/*      */     
/*      */     public boolean tryAdvance(ConcurrentHashMapV8.Action<? super K> action) {
/* 3334 */       if (action == null) throw new NullPointerException(); 
/*      */       ConcurrentHashMapV8.Node<K, V> p;
/* 3336 */       if ((p = advance()) == null)
/* 3337 */         return false; 
/* 3338 */       action.apply(p.key);
/* 3339 */       return true;
/*      */     }
/*      */     public long estimateSize() {
/* 3342 */       return this.est;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class ValueSpliterator<K, V>
/*      */     extends Traverser<K, V> implements ConcurrentHashMapSpliterator<V> {
/*      */     long est;
/*      */     
/*      */     ValueSpliterator(ConcurrentHashMapV8.Node<K, V>[] tab, int size, int index, int limit, long est) {
/* 3351 */       super(tab, size, index, limit);
/* 3352 */       this.est = est;
/*      */     } public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<V> trySplit() {
/*      */       int i;
/*      */       int f;
/*      */       int h;
/* 3357 */       return ((h = (i = this.baseIndex) + (f = this.baseLimit) >>> 1) <= i) ? null : new ValueSpliterator(this.tab, this.baseSize, this.baseLimit = h, f, this.est >>>= 1L);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void forEachRemaining(ConcurrentHashMapV8.Action<? super V> action) {
/* 3363 */       if (action == null) throw new NullPointerException();  ConcurrentHashMapV8.Node<K, V> p;
/* 3364 */       while ((p = advance()) != null)
/* 3365 */         action.apply(p.val); 
/*      */     }
/*      */     
/*      */     public boolean tryAdvance(ConcurrentHashMapV8.Action<? super V> action) {
/* 3369 */       if (action == null) throw new NullPointerException(); 
/*      */       ConcurrentHashMapV8.Node<K, V> p;
/* 3371 */       if ((p = advance()) == null)
/* 3372 */         return false; 
/* 3373 */       action.apply(p.val);
/* 3374 */       return true;
/*      */     }
/*      */     public long estimateSize() {
/* 3377 */       return this.est;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class EntrySpliterator<K, V>
/*      */     extends Traverser<K, V> implements ConcurrentHashMapSpliterator<Map.Entry<K, V>> {
/*      */     final ConcurrentHashMapV8<K, V> map;
/*      */     long est;
/*      */     
/*      */     EntrySpliterator(ConcurrentHashMapV8.Node<K, V>[] tab, int size, int index, int limit, long est, ConcurrentHashMapV8<K, V> map) {
/* 3387 */       super(tab, size, index, limit);
/* 3388 */       this.map = map;
/* 3389 */       this.est = est;
/*      */     } public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<Map.Entry<K, V>> trySplit() {
/*      */       int i;
/*      */       int f;
/*      */       int h;
/* 3394 */       return ((h = (i = this.baseIndex) + (f = this.baseLimit) >>> 1) <= i) ? null : new EntrySpliterator(this.tab, this.baseSize, this.baseLimit = h, f, this.est >>>= 1L, this.map);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void forEachRemaining(ConcurrentHashMapV8.Action<? super Map.Entry<K, V>> action) {
/* 3400 */       if (action == null) throw new NullPointerException();  ConcurrentHashMapV8.Node<K, V> p;
/* 3401 */       while ((p = advance()) != null)
/* 3402 */         action.apply(new ConcurrentHashMapV8.MapEntry<K, V>(p.key, p.val, this.map)); 
/*      */     }
/*      */     
/*      */     public boolean tryAdvance(ConcurrentHashMapV8.Action<? super Map.Entry<K, V>> action) {
/* 3406 */       if (action == null) throw new NullPointerException(); 
/*      */       ConcurrentHashMapV8.Node<K, V> p;
/* 3408 */       if ((p = advance()) == null)
/* 3409 */         return false; 
/* 3410 */       action.apply(new ConcurrentHashMapV8.MapEntry<K, V>(p.key, p.val, this.map));
/* 3411 */       return true;
/*      */     }
/*      */     public long estimateSize() {
/* 3414 */       return this.est;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final int batchFor(long b) {
/*      */     long n;
/* 3430 */     if (b == Long.MAX_VALUE || (n = sumCount()) <= 1L || n < b)
/* 3431 */       return 0; 
/* 3432 */     int sp = ForkJoinPool.getCommonPoolParallelism() << 2;
/* 3433 */     return (b <= 0L || (n /= b) >= sp) ? sp : (int)n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void forEach(long parallelismThreshold, BiAction<? super K, ? super V> action) {
/* 3446 */     if (action == null) throw new NullPointerException(); 
/* 3447 */     (new ForEachMappingTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> void forEach(long parallelismThreshold, BiFun<? super K, ? super V, ? extends U> transformer, Action<? super U> action) {
/* 3467 */     if (transformer == null || action == null)
/* 3468 */       throw new NullPointerException(); 
/* 3469 */     (new ForEachTransformedMappingTask<Object, Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, transformer, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U search(long parallelismThreshold, BiFun<? super K, ? super V, ? extends U> searchFunction) {
/* 3491 */     if (searchFunction == null) throw new NullPointerException(); 
/* 3492 */     return (new SearchMappingsTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, searchFunction, new AtomicReference<U>())).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U reduce(long parallelismThreshold, BiFun<? super K, ? super V, ? extends U> transformer, BiFun<? super U, ? super U, ? extends U> reducer) {
/* 3515 */     if (transformer == null || reducer == null)
/* 3516 */       throw new NullPointerException(); 
/* 3517 */     return (new MapReduceMappingsTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, reducer)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double reduceToDouble(long parallelismThreshold, ObjectByObjectToDouble<? super K, ? super V> transformer, double basis, DoubleByDoubleToDouble reducer) {
/* 3541 */     if (transformer == null || reducer == null)
/* 3542 */       throw new NullPointerException(); 
/* 3543 */     return (new MapReduceMappingsToDoubleTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long reduceToLong(long parallelismThreshold, ObjectByObjectToLong<? super K, ? super V> transformer, long basis, LongByLongToLong reducer) {
/* 3567 */     if (transformer == null || reducer == null)
/* 3568 */       throw new NullPointerException(); 
/* 3569 */     return (new MapReduceMappingsToLongTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().longValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int reduceToInt(long parallelismThreshold, ObjectByObjectToInt<? super K, ? super V> transformer, int basis, IntByIntToInt reducer) {
/* 3593 */     if (transformer == null || reducer == null)
/* 3594 */       throw new NullPointerException(); 
/* 3595 */     return (new MapReduceMappingsToIntTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void forEachKey(long parallelismThreshold, Action<? super K> action) {
/* 3610 */     if (action == null) throw new NullPointerException(); 
/* 3611 */     (new ForEachKeyTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> void forEachKey(long parallelismThreshold, Fun<? super K, ? extends U> transformer, Action<? super U> action) {
/* 3631 */     if (transformer == null || action == null)
/* 3632 */       throw new NullPointerException(); 
/* 3633 */     (new ForEachTransformedKeyTask<Object, Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, transformer, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U searchKeys(long parallelismThreshold, Fun<? super K, ? extends U> searchFunction) {
/* 3655 */     if (searchFunction == null) throw new NullPointerException(); 
/* 3656 */     return (new SearchKeysTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, searchFunction, new AtomicReference<U>())).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public K reduceKeys(long parallelismThreshold, BiFun<? super K, ? super K, ? extends K> reducer) {
/* 3674 */     if (reducer == null) throw new NullPointerException(); 
/* 3675 */     return (new ReduceKeysTask<K, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<K, ?>[])this.table, null, reducer)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U reduceKeys(long parallelismThreshold, Fun<? super K, ? extends U> transformer, BiFun<? super U, ? super U, ? extends U> reducer) {
/* 3698 */     if (transformer == null || reducer == null)
/* 3699 */       throw new NullPointerException(); 
/* 3700 */     return (new MapReduceKeysTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, reducer)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double reduceKeysToDouble(long parallelismThreshold, ObjectToDouble<? super K> transformer, double basis, DoubleByDoubleToDouble reducer) {
/* 3724 */     if (transformer == null || reducer == null)
/* 3725 */       throw new NullPointerException(); 
/* 3726 */     return (new MapReduceKeysToDoubleTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long reduceKeysToLong(long parallelismThreshold, ObjectToLong<? super K> transformer, long basis, LongByLongToLong reducer) {
/* 3750 */     if (transformer == null || reducer == null)
/* 3751 */       throw new NullPointerException(); 
/* 3752 */     return (new MapReduceKeysToLongTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().longValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int reduceKeysToInt(long parallelismThreshold, ObjectToInt<? super K> transformer, int basis, IntByIntToInt reducer) {
/* 3776 */     if (transformer == null || reducer == null)
/* 3777 */       throw new NullPointerException(); 
/* 3778 */     return (new MapReduceKeysToIntTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void forEachValue(long parallelismThreshold, Action<? super V> action) {
/* 3793 */     if (action == null)
/* 3794 */       throw new NullPointerException(); 
/* 3795 */     (new ForEachValueTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> void forEachValue(long parallelismThreshold, Fun<? super V, ? extends U> transformer, Action<? super U> action) {
/* 3815 */     if (transformer == null || action == null)
/* 3816 */       throw new NullPointerException(); 
/* 3817 */     (new ForEachTransformedValueTask<Object, Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, transformer, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U searchValues(long parallelismThreshold, Fun<? super V, ? extends U> searchFunction) {
/* 3839 */     if (searchFunction == null) throw new NullPointerException(); 
/* 3840 */     return (new SearchValuesTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, searchFunction, new AtomicReference<U>())).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public V reduceValues(long parallelismThreshold, BiFun<? super V, ? super V, ? extends V> reducer) {
/* 3857 */     if (reducer == null) throw new NullPointerException(); 
/* 3858 */     return (new ReduceValuesTask<Object, V>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, V>[])this.table, null, reducer)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U reduceValues(long parallelismThreshold, Fun<? super V, ? extends U> transformer, BiFun<? super U, ? super U, ? extends U> reducer) {
/* 3881 */     if (transformer == null || reducer == null)
/* 3882 */       throw new NullPointerException(); 
/* 3883 */     return (new MapReduceValuesTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, reducer)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double reduceValuesToDouble(long parallelismThreshold, ObjectToDouble<? super V> transformer, double basis, DoubleByDoubleToDouble reducer) {
/* 3907 */     if (transformer == null || reducer == null)
/* 3908 */       throw new NullPointerException(); 
/* 3909 */     return (new MapReduceValuesToDoubleTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long reduceValuesToLong(long parallelismThreshold, ObjectToLong<? super V> transformer, long basis, LongByLongToLong reducer) {
/* 3933 */     if (transformer == null || reducer == null)
/* 3934 */       throw new NullPointerException(); 
/* 3935 */     return (new MapReduceValuesToLongTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().longValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int reduceValuesToInt(long parallelismThreshold, ObjectToInt<? super V> transformer, int basis, IntByIntToInt reducer) {
/* 3959 */     if (transformer == null || reducer == null)
/* 3960 */       throw new NullPointerException(); 
/* 3961 */     return (new MapReduceValuesToIntTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void forEachEntry(long parallelismThreshold, Action<? super Map.Entry<K, V>> action) {
/* 3976 */     if (action == null) throw new NullPointerException(); 
/* 3977 */     (new ForEachEntryTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> void forEachEntry(long parallelismThreshold, Fun<Map.Entry<K, V>, ? extends U> transformer, Action<? super U> action) {
/* 3996 */     if (transformer == null || action == null)
/* 3997 */       throw new NullPointerException(); 
/* 3998 */     (new ForEachTransformedEntryTask<Object, Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, transformer, action)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U searchEntries(long parallelismThreshold, Fun<Map.Entry<K, V>, ? extends U> searchFunction) {
/* 4020 */     if (searchFunction == null) throw new NullPointerException(); 
/* 4021 */     return (new SearchEntriesTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, searchFunction, new AtomicReference<U>())).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map.Entry<K, V> reduceEntries(long parallelismThreshold, BiFun<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer) {
/* 4038 */     if (reducer == null) throw new NullPointerException(); 
/* 4039 */     return (new ReduceEntriesTask<K, V>(null, batchFor(parallelismThreshold), 0, 0, this.table, null, reducer)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <U> U reduceEntries(long parallelismThreshold, Fun<Map.Entry<K, V>, ? extends U> transformer, BiFun<? super U, ? super U, ? extends U> reducer) {
/* 4062 */     if (transformer == null || reducer == null)
/* 4063 */       throw new NullPointerException(); 
/* 4064 */     return (new MapReduceEntriesTask<Object, Object, U>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, reducer)).invoke();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double reduceEntriesToDouble(long parallelismThreshold, ObjectToDouble<Map.Entry<K, V>> transformer, double basis, DoubleByDoubleToDouble reducer) {
/* 4088 */     if (transformer == null || reducer == null)
/* 4089 */       throw new NullPointerException(); 
/* 4090 */     return (new MapReduceEntriesToDoubleTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long reduceEntriesToLong(long parallelismThreshold, ObjectToLong<Map.Entry<K, V>> transformer, long basis, LongByLongToLong reducer) {
/* 4114 */     if (transformer == null || reducer == null)
/* 4115 */       throw new NullPointerException(); 
/* 4116 */     return (new MapReduceEntriesToLongTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().longValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int reduceEntriesToInt(long parallelismThreshold, ObjectToInt<Map.Entry<K, V>> transformer, int basis, IntByIntToInt reducer) {
/* 4140 */     if (transformer == null || reducer == null)
/* 4141 */       throw new NullPointerException(); 
/* 4142 */     return (new MapReduceEntriesToIntTask<Object, Object>(null, batchFor(parallelismThreshold), 0, 0, (Node<?, ?>[])this.table, null, transformer, basis, reducer)).invoke().intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   static abstract class CollectionView<K, V, E>
/*      */     implements Collection<E>, Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 7249069246763182397L;
/*      */     
/*      */     final ConcurrentHashMapV8<K, V> map;
/*      */     
/*      */     private static final String oomeMsg = "Required array size too large";
/*      */ 
/*      */     
/*      */     CollectionView(ConcurrentHashMapV8<K, V> map) {
/* 4157 */       this.map = map;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ConcurrentHashMapV8<K, V> getMap() {
/* 4164 */       return this.map;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final void clear() {
/* 4170 */       this.map.clear();
/* 4171 */     } public final int size() { return this.map.size(); } public final boolean isEmpty() {
/* 4172 */       return this.map.isEmpty();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract Iterator<E> iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract boolean contains(Object param1Object);
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract boolean remove(Object param1Object);
/*      */ 
/*      */ 
/*      */     
/*      */     public final Object[] toArray() {
/* 4191 */       long sz = this.map.mappingCount();
/* 4192 */       if (sz > 2147483639L)
/* 4193 */         throw new OutOfMemoryError("Required array size too large"); 
/* 4194 */       int n = (int)sz;
/* 4195 */       Object[] r = new Object[n];
/* 4196 */       int i = 0;
/* 4197 */       for (E e : this) {
/* 4198 */         if (i == n) {
/* 4199 */           if (n >= 2147483639)
/* 4200 */             throw new OutOfMemoryError("Required array size too large"); 
/* 4201 */           if (n >= 1073741819) {
/* 4202 */             n = 2147483639;
/*      */           } else {
/* 4204 */             n += (n >>> 1) + 1;
/* 4205 */           }  r = Arrays.copyOf(r, n);
/*      */         } 
/* 4207 */         r[i++] = e;
/*      */       } 
/* 4209 */       return (i == n) ? r : Arrays.<Object>copyOf(r, i);
/*      */     }
/*      */ 
/*      */     
/*      */     public final <T> T[] toArray(T[] a) {
/* 4214 */       long sz = this.map.mappingCount();
/* 4215 */       if (sz > 2147483639L)
/* 4216 */         throw new OutOfMemoryError("Required array size too large"); 
/* 4217 */       int m = (int)sz;
/* 4218 */       T[] r = (a.length >= m) ? a : (T[])Array.newInstance(a.getClass().getComponentType(), m);
/*      */ 
/*      */       
/* 4221 */       int n = r.length;
/* 4222 */       int i = 0;
/* 4223 */       for (E e : this) {
/* 4224 */         if (i == n) {
/* 4225 */           if (n >= 2147483639)
/* 4226 */             throw new OutOfMemoryError("Required array size too large"); 
/* 4227 */           if (n >= 1073741819) {
/* 4228 */             n = 2147483639;
/*      */           } else {
/* 4230 */             n += (n >>> 1) + 1;
/* 4231 */           }  r = Arrays.copyOf(r, n);
/*      */         } 
/* 4233 */         r[i++] = (T)e;
/*      */       } 
/* 4235 */       if (a == r && i < n) {
/* 4236 */         r[i] = null;
/* 4237 */         return r;
/*      */       } 
/* 4239 */       return (i == n) ? r : Arrays.<T>copyOf(r, i);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final String toString() {
/* 4254 */       StringBuilder sb = new StringBuilder();
/* 4255 */       sb.append('[');
/* 4256 */       Iterator<E> it = iterator();
/* 4257 */       if (it.hasNext()) {
/*      */         while (true) {
/* 4259 */           Object e = it.next();
/* 4260 */           sb.append((e == this) ? "(this Collection)" : e);
/* 4261 */           if (!it.hasNext())
/*      */             break; 
/* 4263 */           sb.append(',').append(' ');
/*      */         } 
/*      */       }
/* 4266 */       return sb.append(']').toString();
/*      */     }
/*      */     
/*      */     public final boolean containsAll(Collection<?> c) {
/* 4270 */       if (c != this)
/* 4271 */         for (Object e : c) {
/* 4272 */           if (e == null || !contains(e)) {
/* 4273 */             return false;
/*      */           }
/*      */         }  
/* 4276 */       return true;
/*      */     }
/*      */     
/*      */     public final boolean removeAll(Collection<?> c) {
/* 4280 */       boolean modified = false;
/* 4281 */       for (Iterator<E> it = iterator(); it.hasNext();) {
/* 4282 */         if (c.contains(it.next())) {
/* 4283 */           it.remove();
/* 4284 */           modified = true;
/*      */         } 
/*      */       } 
/* 4287 */       return modified;
/*      */     }
/*      */     
/*      */     public final boolean retainAll(Collection<?> c) {
/* 4291 */       boolean modified = false;
/* 4292 */       for (Iterator<E> it = iterator(); it.hasNext();) {
/* 4293 */         if (!c.contains(it.next())) {
/* 4294 */           it.remove();
/* 4295 */           modified = true;
/*      */         } 
/*      */       } 
/* 4298 */       return modified;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class KeySetView<K, V>
/*      */     extends CollectionView<K, V, K>
/*      */     implements Set<K>, Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 7249069246763182397L;
/*      */ 
/*      */ 
/*      */     
/*      */     private final V value;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     KeySetView(ConcurrentHashMapV8<K, V> map, V value) {
/* 4319 */       super(map);
/* 4320 */       this.value = value;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public V getMappedValue() {
/* 4330 */       return this.value;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean contains(Object o) {
/* 4336 */       return this.map.containsKey(o);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean remove(Object o) {
/* 4347 */       return (this.map.remove(o) != null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<K> iterator() {
/* 4354 */       ConcurrentHashMapV8<K, V> m = this.map; ConcurrentHashMapV8.Node<K, V>[] t;
/* 4355 */       int f = ((t = m.table) == null) ? 0 : t.length;
/* 4356 */       return new ConcurrentHashMapV8.KeyIterator<K, V>(t, f, 0, f, m);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean add(K e) {
/*      */       V v;
/* 4371 */       if ((v = this.value) == null)
/* 4372 */         throw new UnsupportedOperationException(); 
/* 4373 */       return (this.map.putVal(e, v, true) == null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(Collection<? extends K> c) {
/* 4388 */       boolean added = false;
/*      */       V v;
/* 4390 */       if ((v = this.value) == null)
/* 4391 */         throw new UnsupportedOperationException(); 
/* 4392 */       for (K e : c) {
/* 4393 */         if (this.map.putVal(e, v, true) == null)
/* 4394 */           added = true; 
/*      */       } 
/* 4396 */       return added;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 4400 */       int h = 0;
/* 4401 */       for (K e : this)
/* 4402 */         h += e.hashCode(); 
/* 4403 */       return h;
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*      */       Set<?> c;
/* 4408 */       return (o instanceof Set && ((c = (Set)o) == this || (containsAll(c) && c.containsAll(this))));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<K> spliterator166() {
/* 4415 */       ConcurrentHashMapV8<K, V> m = this.map;
/* 4416 */       long n = m.sumCount(); ConcurrentHashMapV8.Node<K, V>[] t;
/* 4417 */       int f = ((t = m.table) == null) ? 0 : t.length;
/* 4418 */       return new ConcurrentHashMapV8.KeySpliterator<K, V>(t, f, 0, f, (n < 0L) ? 0L : n);
/*      */     }
/*      */     
/*      */     public void forEach(ConcurrentHashMapV8.Action<? super K> action) {
/* 4422 */       if (action == null) throw new NullPointerException(); 
/*      */       ConcurrentHashMapV8.Node<K, V>[] t;
/* 4424 */       if ((t = this.map.table) != null) {
/* 4425 */         ConcurrentHashMapV8.Traverser<K, V> it = new ConcurrentHashMapV8.Traverser<K, V>(t, t.length, 0, t.length); ConcurrentHashMapV8.Node<K, V> p;
/* 4426 */         while ((p = it.advance()) != null) {
/* 4427 */           action.apply(p.key);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static final class ValuesView<K, V>
/*      */     extends CollectionView<K, V, V>
/*      */     implements Collection<V>, Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 2249069246763182397L;
/*      */     
/*      */     ValuesView(ConcurrentHashMapV8<K, V> map) {
/* 4440 */       super(map);
/*      */     } public final boolean contains(Object o) {
/* 4442 */       return this.map.containsValue(o);
/*      */     }
/*      */     
/*      */     public final boolean remove(Object o) {
/* 4446 */       if (o != null) {
/* 4447 */         for (Iterator<V> it = iterator(); it.hasNext();) {
/* 4448 */           if (o.equals(it.next())) {
/* 4449 */             it.remove();
/* 4450 */             return true;
/*      */           } 
/*      */         } 
/*      */       }
/* 4454 */       return false;
/*      */     }
/*      */     
/*      */     public final Iterator<V> iterator() {
/* 4458 */       ConcurrentHashMapV8<K, V> m = this.map;
/*      */       ConcurrentHashMapV8.Node<K, V>[] t;
/* 4460 */       int f = ((t = m.table) == null) ? 0 : t.length;
/* 4461 */       return new ConcurrentHashMapV8.ValueIterator<K, V>(t, f, 0, f, m);
/*      */     }
/*      */     
/*      */     public final boolean add(V e) {
/* 4465 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     public final boolean addAll(Collection<? extends V> c) {
/* 4468 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<V> spliterator166() {
/* 4473 */       ConcurrentHashMapV8<K, V> m = this.map;
/* 4474 */       long n = m.sumCount(); ConcurrentHashMapV8.Node<K, V>[] t;
/* 4475 */       int f = ((t = m.table) == null) ? 0 : t.length;
/* 4476 */       return new ConcurrentHashMapV8.ValueSpliterator<K, V>(t, f, 0, f, (n < 0L) ? 0L : n);
/*      */     }
/*      */     
/*      */     public void forEach(ConcurrentHashMapV8.Action<? super V> action) {
/* 4480 */       if (action == null) throw new NullPointerException(); 
/*      */       ConcurrentHashMapV8.Node<K, V>[] t;
/* 4482 */       if ((t = this.map.table) != null) {
/* 4483 */         ConcurrentHashMapV8.Traverser<K, V> it = new ConcurrentHashMapV8.Traverser<K, V>(t, t.length, 0, t.length); ConcurrentHashMapV8.Node<K, V> p;
/* 4484 */         while ((p = it.advance()) != null) {
/* 4485 */           action.apply(p.val);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static final class EntrySetView<K, V>
/*      */     extends CollectionView<K, V, Map.Entry<K, V>>
/*      */     implements Set<Map.Entry<K, V>>, Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 2249069246763182397L;
/*      */     
/*      */     EntrySetView(ConcurrentHashMapV8<K, V> map) {
/* 4498 */       super(map); } public boolean contains(Object o) { Object k;
/*      */       Object v;
/*      */       Object r;
/*      */       Map.Entry<?, ?> e;
/* 4502 */       return (o instanceof Map.Entry && (k = (e = (Map.Entry<?, ?>)o).getKey()) != null && (r = this.map.get(k)) != null && (v = e.getValue()) != null && (v == r || v.equals(r))); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean remove(Object o) {
/*      */       Object k;
/*      */       Object v;
/*      */       Map.Entry<?, ?> e;
/* 4511 */       return (o instanceof Map.Entry && (k = (e = (Map.Entry<?, ?>)o).getKey()) != null && (v = e.getValue()) != null && this.map.remove(k, v));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<Map.Entry<K, V>> iterator() {
/* 4521 */       ConcurrentHashMapV8<K, V> m = this.map;
/*      */       ConcurrentHashMapV8.Node<K, V>[] t;
/* 4523 */       int f = ((t = m.table) == null) ? 0 : t.length;
/* 4524 */       return new ConcurrentHashMapV8.EntryIterator<K, V>(t, f, 0, f, m);
/*      */     }
/*      */     
/*      */     public boolean add(Map.Entry<K, V> e) {
/* 4528 */       return (this.map.putVal(e.getKey(), e.getValue(), false) == null);
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
/* 4532 */       boolean added = false;
/* 4533 */       for (Map.Entry<K, V> e : c) {
/* 4534 */         if (add(e))
/* 4535 */           added = true; 
/*      */       } 
/* 4537 */       return added;
/*      */     }
/*      */     
/*      */     public final int hashCode() {
/* 4541 */       int h = 0;
/*      */       ConcurrentHashMapV8.Node<K, V>[] t;
/* 4543 */       if ((t = this.map.table) != null) {
/* 4544 */         ConcurrentHashMapV8.Traverser<K, V> it = new ConcurrentHashMapV8.Traverser<K, V>(t, t.length, 0, t.length); ConcurrentHashMapV8.Node<K, V> p;
/* 4545 */         while ((p = it.advance()) != null) {
/* 4546 */           h += p.hashCode();
/*      */         }
/*      */       } 
/* 4549 */       return h;
/*      */     }
/*      */     
/*      */     public final boolean equals(Object o) {
/*      */       Set<?> c;
/* 4554 */       return (o instanceof Set && ((c = (Set)o) == this || (containsAll(c) && c.containsAll(this))));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<Map.Entry<K, V>> spliterator166() {
/* 4561 */       ConcurrentHashMapV8<K, V> m = this.map;
/* 4562 */       long n = m.sumCount(); ConcurrentHashMapV8.Node<K, V>[] t;
/* 4563 */       int f = ((t = m.table) == null) ? 0 : t.length;
/* 4564 */       return new ConcurrentHashMapV8.EntrySpliterator<K, V>(t, f, 0, f, (n < 0L) ? 0L : n, m);
/*      */     }
/*      */     
/*      */     public void forEach(ConcurrentHashMapV8.Action<? super Map.Entry<K, V>> action) {
/* 4568 */       if (action == null) throw new NullPointerException(); 
/*      */       ConcurrentHashMapV8.Node<K, V>[] t;
/* 4570 */       if ((t = this.map.table) != null) {
/* 4571 */         ConcurrentHashMapV8.Traverser<K, V> it = new ConcurrentHashMapV8.Traverser<K, V>(t, t.length, 0, t.length); ConcurrentHashMapV8.Node<K, V> p;
/* 4572 */         while ((p = it.advance()) != null) {
/* 4573 */           action.apply(new ConcurrentHashMapV8.MapEntry<K, V>(p.key, p.val, this.map));
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static abstract class BulkTask<K, V, R>
/*      */     extends CountedCompleter<R>
/*      */   {
/*      */     ConcurrentHashMapV8.Node<K, V>[] tab;
/*      */     
/*      */     ConcurrentHashMapV8.Node<K, V> next;
/*      */     
/*      */     int index;
/*      */     
/*      */     int baseIndex;
/*      */     int baseLimit;
/*      */     final int baseSize;
/*      */     int batch;
/*      */     
/*      */     BulkTask(BulkTask<K, V, ?> par, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t) {
/* 4595 */       super(par);
/* 4596 */       this.batch = b;
/* 4597 */       this.index = this.baseIndex = i;
/* 4598 */       if ((this.tab = t) == null) {
/* 4599 */         this.baseSize = this.baseLimit = 0;
/* 4600 */       } else if (par == null) {
/* 4601 */         this.baseSize = this.baseLimit = t.length;
/*      */       } else {
/* 4603 */         this.baseLimit = f;
/* 4604 */         this.baseSize = par.baseSize;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final ConcurrentHashMapV8.Node<K, V> advance() {
/*      */       ConcurrentHashMapV8.Node<K, V> e;
/* 4613 */       if ((e = this.next) != null) {
/* 4614 */         e = e.next;
/*      */       }
/*      */       while (true) {
/* 4617 */         if (e != null)
/* 4618 */           return this.next = e;  ConcurrentHashMapV8.Node<K, V>[] t; int i; int n;
/* 4619 */         if (this.baseIndex >= this.baseLimit || (t = this.tab) == null || (n = t.length) <= (i = this.index) || i < 0)
/*      */         {
/* 4621 */           return this.next = null; } 
/* 4622 */         if ((e = ConcurrentHashMapV8.<K, V>tabAt(t, this.index)) != null && e.hash < 0) {
/* 4623 */           if (e instanceof ConcurrentHashMapV8.ForwardingNode) {
/* 4624 */             this.tab = (ConcurrentHashMapV8.Node<K, V>[])((ConcurrentHashMapV8.ForwardingNode)e).nextTable;
/* 4625 */             e = null;
/*      */             continue;
/*      */           } 
/* 4628 */           if (e instanceof ConcurrentHashMapV8.TreeBin) {
/* 4629 */             e = ((ConcurrentHashMapV8.TreeBin)e).first;
/*      */           } else {
/* 4631 */             e = null;
/*      */           } 
/* 4633 */         }  if ((this.index += this.baseSize) >= n) {
/* 4634 */           this.index = ++this.baseIndex;
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final class ForEachKeyTask<K, V>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.Action<? super K> action;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ForEachKeyTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Action<? super K> action) {
/* 4653 */       super(p, b, i, f, t);
/* 4654 */       this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.Action<? super K> action;
/* 4658 */       if ((action = this.action) != null) {
/* 4659 */         int f; int h; for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4661 */           addToPendingCount(1);
/* 4662 */           (new ForEachKeyTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4666 */         while ((p = advance()) != null)
/* 4667 */           action.apply(p.key); 
/* 4668 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForEachValueTask<K, V>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.Action<? super V> action;
/*      */     
/*      */     ForEachValueTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Action<? super V> action) {
/* 4680 */       super(p, b, i, f, t);
/* 4681 */       this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.Action<? super V> action;
/* 4685 */       if ((action = this.action) != null) {
/* 4686 */         int f; int h; for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4688 */           addToPendingCount(1);
/* 4689 */           (new ForEachValueTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4693 */         while ((p = advance()) != null)
/* 4694 */           action.apply(p.val); 
/* 4695 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForEachEntryTask<K, V>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.Action<? super Map.Entry<K, V>> action;
/*      */     
/*      */     ForEachEntryTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Action<? super Map.Entry<K, V>> action) {
/* 4707 */       super(p, b, i, f, t);
/* 4708 */       this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.Action<? super Map.Entry<K, V>> action;
/* 4712 */       if ((action = this.action) != null) {
/* 4713 */         int f; int h; for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4715 */           addToPendingCount(1);
/* 4716 */           (new ForEachEntryTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4720 */         while ((p = advance()) != null)
/* 4721 */           action.apply(p); 
/* 4722 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForEachMappingTask<K, V>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.BiAction<? super K, ? super V> action;
/*      */     
/*      */     ForEachMappingTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.BiAction<? super K, ? super V> action) {
/* 4734 */       super(p, b, i, f, t);
/* 4735 */       this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.BiAction<? super K, ? super V> action;
/* 4739 */       if ((action = this.action) != null) {
/* 4740 */         int f; int h; for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4742 */           addToPendingCount(1);
/* 4743 */           (new ForEachMappingTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4747 */         while ((p = advance()) != null)
/* 4748 */           action.apply(p.key, p.val); 
/* 4749 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForEachTransformedKeyTask<K, V, U>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer;
/*      */     final ConcurrentHashMapV8.Action<? super U> action;
/*      */     
/*      */     ForEachTransformedKeyTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer, ConcurrentHashMapV8.Action<? super U> action) {
/* 4762 */       super(p, b, i, f, t);
/* 4763 */       this.transformer = transformer; this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.Action<? super U> action;
/* 4768 */       if ((transformer = this.transformer) != null && (action = this.action) != null) {
/*      */         int f; int h;
/* 4770 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4772 */           addToPendingCount(1);
/* 4773 */           (new ForEachTransformedKeyTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, transformer, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4777 */         while ((p = advance()) != null) {
/*      */           U u;
/* 4779 */           if ((u = transformer.apply(p.key)) != null)
/* 4780 */             action.apply(u); 
/*      */         } 
/* 4782 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForEachTransformedValueTask<K, V, U>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer;
/*      */     final ConcurrentHashMapV8.Action<? super U> action;
/*      */     
/*      */     ForEachTransformedValueTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer, ConcurrentHashMapV8.Action<? super U> action) {
/* 4795 */       super(p, b, i, f, t);
/* 4796 */       this.transformer = transformer; this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.Action<? super U> action;
/* 4801 */       if ((transformer = this.transformer) != null && (action = this.action) != null) {
/*      */         int f; int h;
/* 4803 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4805 */           addToPendingCount(1);
/* 4806 */           (new ForEachTransformedValueTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, transformer, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4810 */         while ((p = advance()) != null) {
/*      */           U u;
/* 4812 */           if ((u = transformer.apply(p.val)) != null)
/* 4813 */             action.apply(u); 
/*      */         } 
/* 4815 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForEachTransformedEntryTask<K, V, U>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> transformer;
/*      */     final ConcurrentHashMapV8.Action<? super U> action;
/*      */     
/*      */     ForEachTransformedEntryTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> transformer, ConcurrentHashMapV8.Action<? super U> action) {
/* 4828 */       super(p, b, i, f, t);
/* 4829 */       this.transformer = transformer; this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.Action<? super U> action;
/* 4834 */       if ((transformer = this.transformer) != null && (action = this.action) != null) {
/*      */         int f; int h;
/* 4836 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4838 */           addToPendingCount(1);
/* 4839 */           (new ForEachTransformedEntryTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, transformer, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4843 */         while ((p = advance()) != null) {
/*      */           U u;
/* 4845 */           if ((u = transformer.apply(p)) != null)
/* 4846 */             action.apply(u); 
/*      */         } 
/* 4848 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ForEachTransformedMappingTask<K, V, U>
/*      */     extends BulkTask<K, V, Void>
/*      */   {
/*      */     final ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.Action<? super U> action;
/*      */     
/*      */     ForEachTransformedMappingTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer, ConcurrentHashMapV8.Action<? super U> action) {
/* 4862 */       super(p, b, i, f, t);
/* 4863 */       this.transformer = transformer; this.action = action;
/*      */     }
/*      */     public final void compute() {
/*      */       ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.Action<? super U> action;
/* 4868 */       if ((transformer = this.transformer) != null && (action = this.action) != null) {
/*      */         int f; int h;
/* 4870 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4872 */           addToPendingCount(1);
/* 4873 */           (new ForEachTransformedMappingTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, transformer, action)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 4877 */         while ((p = advance()) != null) {
/*      */           U u;
/* 4879 */           if ((u = transformer.apply(p.key, p.val)) != null)
/* 4880 */             action.apply(u); 
/*      */         } 
/* 4882 */         propagateCompletion();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class SearchKeysTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<? super K, ? extends U> searchFunction;
/*      */     
/*      */     final AtomicReference<U> result;
/*      */     
/*      */     SearchKeysTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Fun<? super K, ? extends U> searchFunction, AtomicReference<U> result) {
/* 4896 */       super(p, b, i, f, t);
/* 4897 */       this.searchFunction = searchFunction; this.result = result;
/*      */     } public final U getRawResult() {
/* 4899 */       return this.result.get();
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<? super K, ? extends U> searchFunction;
/*      */       AtomicReference<U> result;
/* 4903 */       if ((searchFunction = this.searchFunction) != null && (result = this.result) != null) {
/*      */         int f; int h;
/* 4905 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4907 */           if (result.get() != null)
/*      */             return; 
/* 4909 */           addToPendingCount(1);
/* 4910 */           (new SearchKeysTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, searchFunction, result)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 4914 */         while (result.get() == null) {
/*      */           ConcurrentHashMapV8.Node<K, V> p;
/*      */           
/* 4917 */           if ((p = advance()) == null) {
/* 4918 */             propagateCompletion(); break;
/*      */           } 
/*      */           U u;
/* 4921 */           if ((u = searchFunction.apply(p.key)) != null) {
/* 4922 */             if (result.compareAndSet(null, u)) {
/* 4923 */               quietlyCompleteRoot();
/*      */             }
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class SearchValuesTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<? super V, ? extends U> searchFunction;
/*      */     final AtomicReference<U> result;
/*      */     
/*      */     SearchValuesTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Fun<? super V, ? extends U> searchFunction, AtomicReference<U> result) {
/* 4940 */       super(p, b, i, f, t);
/* 4941 */       this.searchFunction = searchFunction; this.result = result;
/*      */     } public final U getRawResult() {
/* 4943 */       return this.result.get();
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<? super V, ? extends U> searchFunction;
/*      */       AtomicReference<U> result;
/* 4947 */       if ((searchFunction = this.searchFunction) != null && (result = this.result) != null) {
/*      */         int f; int h;
/* 4949 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4951 */           if (result.get() != null)
/*      */             return; 
/* 4953 */           addToPendingCount(1);
/* 4954 */           (new SearchValuesTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, searchFunction, result)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 4958 */         while (result.get() == null) {
/*      */           ConcurrentHashMapV8.Node<K, V> p;
/*      */           
/* 4961 */           if ((p = advance()) == null) {
/* 4962 */             propagateCompletion(); break;
/*      */           } 
/*      */           U u;
/* 4965 */           if ((u = searchFunction.apply(p.val)) != null) {
/* 4966 */             if (result.compareAndSet(null, u)) {
/* 4967 */               quietlyCompleteRoot();
/*      */             }
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class SearchEntriesTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> searchFunction;
/*      */     final AtomicReference<U> result;
/*      */     
/*      */     SearchEntriesTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> searchFunction, AtomicReference<U> result) {
/* 4984 */       super(p, b, i, f, t);
/* 4985 */       this.searchFunction = searchFunction; this.result = result;
/*      */     } public final U getRawResult() {
/* 4987 */       return this.result.get();
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> searchFunction;
/*      */       AtomicReference<U> result;
/* 4991 */       if ((searchFunction = this.searchFunction) != null && (result = this.result) != null) {
/*      */         int f; int h;
/* 4993 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 4995 */           if (result.get() != null)
/*      */             return; 
/* 4997 */           addToPendingCount(1);
/* 4998 */           (new SearchEntriesTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, searchFunction, result)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5002 */         while (result.get() == null) {
/*      */           ConcurrentHashMapV8.Node<K, V> p;
/*      */           
/* 5005 */           if ((p = advance()) == null) {
/* 5006 */             propagateCompletion(); break;
/*      */           } 
/*      */           U u;
/* 5009 */           if ((u = searchFunction.apply(p)) != null) {
/* 5010 */             if (result.compareAndSet(null, u)) {
/* 5011 */               quietlyCompleteRoot();
/*      */             }
/*      */             return;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class SearchMappingsTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> searchFunction;
/*      */     final AtomicReference<U> result;
/*      */     
/*      */     SearchMappingsTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> searchFunction, AtomicReference<U> result) {
/* 5028 */       super(p, b, i, f, t);
/* 5029 */       this.searchFunction = searchFunction; this.result = result;
/*      */     } public final U getRawResult() {
/* 5031 */       return this.result.get();
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> searchFunction;
/*      */       AtomicReference<U> result;
/* 5035 */       if ((searchFunction = this.searchFunction) != null && (result = this.result) != null) {
/*      */         int f; int h;
/* 5037 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5039 */           if (result.get() != null)
/*      */             return; 
/* 5041 */           addToPendingCount(1);
/* 5042 */           (new SearchMappingsTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, searchFunction, result)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5046 */         while (result.get() == null) {
/*      */           ConcurrentHashMapV8.Node<K, V> p;
/*      */           
/* 5049 */           if ((p = advance()) == null) {
/* 5050 */             propagateCompletion(); break;
/*      */           } 
/*      */           U u;
/* 5053 */           if ((u = searchFunction.apply(p.key, p.val)) != null) {
/* 5054 */             if (result.compareAndSet(null, u)) {
/* 5055 */               quietlyCompleteRoot();
/*      */             }
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static final class ReduceKeysTask<K, V>
/*      */     extends BulkTask<K, V, K>
/*      */   {
/*      */     final ConcurrentHashMapV8.BiFun<? super K, ? super K, ? extends K> reducer;
/*      */     K result;
/*      */     ReduceKeysTask<K, V> rights;
/*      */     ReduceKeysTask<K, V> nextRight;
/*      */     
/*      */     ReduceKeysTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ReduceKeysTask<K, V> nextRight, ConcurrentHashMapV8.BiFun<? super K, ? super K, ? extends K> reducer) {
/* 5073 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5074 */       this.reducer = reducer;
/*      */     } public final K getRawResult() {
/* 5076 */       return this.result;
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.BiFun<? super K, ? super K, ? extends K> reducer;
/* 5079 */       if ((reducer = this.reducer) != null) {
/* 5080 */         int f, h; for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5082 */           addToPendingCount(1);
/* 5083 */           (this.rights = new ReduceKeysTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, reducer)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5087 */         K r = null; ConcurrentHashMapV8.Node<K, V> p;
/* 5088 */         while ((p = advance()) != null) {
/* 5089 */           K u = p.key;
/* 5090 */           r = (r == null) ? u : ((u == null) ? r : reducer.apply(r, u));
/*      */         } 
/* 5092 */         this.result = r;
/*      */         
/* 5094 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5096 */           ReduceKeysTask<K, V> t = (ReduceKeysTask)c;
/* 5097 */           ReduceKeysTask<K, V> s = t.rights;
/* 5098 */           while (s != null) {
/*      */             K sr;
/* 5100 */             if ((sr = s.result) != null) {
/* 5101 */               K tr; t.result = ((tr = t.result) == null) ? sr : reducer.apply(tr, sr);
/*      */             } 
/* 5103 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ReduceValuesTask<K, V>
/*      */     extends BulkTask<K, V, V>
/*      */   {
/*      */     final ConcurrentHashMapV8.BiFun<? super V, ? super V, ? extends V> reducer;
/*      */     V result;
/*      */     ReduceValuesTask<K, V> rights;
/*      */     ReduceValuesTask<K, V> nextRight;
/*      */     
/*      */     ReduceValuesTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ReduceValuesTask<K, V> nextRight, ConcurrentHashMapV8.BiFun<? super V, ? super V, ? extends V> reducer) {
/* 5120 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5121 */       this.reducer = reducer;
/*      */     } public final V getRawResult() {
/* 5123 */       return this.result;
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.BiFun<? super V, ? super V, ? extends V> reducer;
/* 5126 */       if ((reducer = this.reducer) != null) {
/* 5127 */         int f, h; for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5129 */           addToPendingCount(1);
/* 5130 */           (this.rights = new ReduceValuesTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, reducer)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5134 */         V r = null; ConcurrentHashMapV8.Node<K, V> p;
/* 5135 */         while ((p = advance()) != null) {
/* 5136 */           V v = p.val;
/* 5137 */           r = (r == null) ? v : reducer.apply(r, v);
/*      */         } 
/* 5139 */         this.result = r;
/*      */         
/* 5141 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5143 */           ReduceValuesTask<K, V> t = (ReduceValuesTask)c;
/* 5144 */           ReduceValuesTask<K, V> s = t.rights;
/* 5145 */           while (s != null) {
/*      */             V sr;
/* 5147 */             if ((sr = s.result) != null) {
/* 5148 */               V tr; t.result = ((tr = t.result) == null) ? sr : reducer.apply(tr, sr);
/*      */             } 
/* 5150 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ReduceEntriesTask<K, V>
/*      */     extends BulkTask<K, V, Map.Entry<K, V>>
/*      */   {
/*      */     final ConcurrentHashMapV8.BiFun<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer;
/*      */     Map.Entry<K, V> result;
/*      */     ReduceEntriesTask<K, V> rights;
/*      */     ReduceEntriesTask<K, V> nextRight;
/*      */     
/*      */     ReduceEntriesTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, ReduceEntriesTask<K, V> nextRight, ConcurrentHashMapV8.BiFun<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer) {
/* 5167 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5168 */       this.reducer = reducer;
/*      */     } public final Map.Entry<K, V> getRawResult() {
/* 5170 */       return this.result;
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.BiFun<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer;
/* 5173 */       if ((reducer = this.reducer) != null) {
/* 5174 */         int f, h; for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5176 */           addToPendingCount(1);
/* 5177 */           (this.rights = new ReduceEntriesTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, reducer)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5181 */         Map.Entry<K, V> r = null; ConcurrentHashMapV8.Node<K, V> p;
/* 5182 */         while ((p = advance()) != null)
/* 5183 */           r = (r == null) ? p : reducer.apply(r, p); 
/* 5184 */         this.result = r;
/*      */         
/* 5186 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5188 */           ReduceEntriesTask<K, V> t = (ReduceEntriesTask)c;
/* 5189 */           ReduceEntriesTask<K, V> s = t.rights;
/* 5190 */           while (s != null) {
/*      */             Map.Entry<K, V> sr;
/* 5192 */             if ((sr = s.result) != null) {
/* 5193 */               Map.Entry<K, V> tr; t.result = ((tr = t.result) == null) ? sr : reducer.apply(tr, sr);
/*      */             } 
/* 5195 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceKeysTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/*      */     U result;
/*      */     MapReduceKeysTask<K, V, U> rights;
/*      */     MapReduceKeysTask<K, V, U> nextRight;
/*      */     
/*      */     MapReduceKeysTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceKeysTask<K, V, U> nextRight, ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer) {
/* 5214 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5215 */       this.transformer = transformer;
/* 5216 */       this.reducer = reducer;
/*      */     } public final U getRawResult() {
/* 5218 */       return this.result;
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/* 5222 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         int f, h;
/* 5224 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5226 */           addToPendingCount(1);
/* 5227 */           (this.rights = new MapReduceKeysTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, reducer)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5231 */         U r = null; ConcurrentHashMapV8.Node<K, V> p;
/* 5232 */         while ((p = advance()) != null) {
/*      */           U u;
/* 5234 */           if ((u = transformer.apply(p.key)) != null)
/* 5235 */             r = (r == null) ? u : reducer.apply(r, u); 
/*      */         } 
/* 5237 */         this.result = r;
/*      */         
/* 5239 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5241 */           MapReduceKeysTask<K, V, U> t = (MapReduceKeysTask)c;
/* 5242 */           MapReduceKeysTask<K, V, U> s = t.rights;
/* 5243 */           while (s != null) {
/*      */             U sr;
/* 5245 */             if ((sr = s.result) != null) {
/* 5246 */               U tr; t.result = ((tr = t.result) == null) ? sr : reducer.apply(tr, sr);
/*      */             } 
/* 5248 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceValuesTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/*      */     U result;
/*      */     MapReduceValuesTask<K, V, U> rights;
/*      */     MapReduceValuesTask<K, V, U> nextRight;
/*      */     
/*      */     MapReduceValuesTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceValuesTask<K, V, U> nextRight, ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer) {
/* 5267 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5268 */       this.transformer = transformer;
/* 5269 */       this.reducer = reducer;
/*      */     } public final U getRawResult() {
/* 5271 */       return this.result;
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/* 5275 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         int f, h;
/* 5277 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5279 */           addToPendingCount(1);
/* 5280 */           (this.rights = new MapReduceValuesTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, reducer)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5284 */         U r = null; ConcurrentHashMapV8.Node<K, V> p;
/* 5285 */         while ((p = advance()) != null) {
/*      */           U u;
/* 5287 */           if ((u = transformer.apply(p.val)) != null)
/* 5288 */             r = (r == null) ? u : reducer.apply(r, u); 
/*      */         } 
/* 5290 */         this.result = r;
/*      */         
/* 5292 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5294 */           MapReduceValuesTask<K, V, U> t = (MapReduceValuesTask)c;
/* 5295 */           MapReduceValuesTask<K, V, U> s = t.rights;
/* 5296 */           while (s != null) {
/*      */             U sr;
/* 5298 */             if ((sr = s.result) != null) {
/* 5299 */               U tr; t.result = ((tr = t.result) == null) ? sr : reducer.apply(tr, sr);
/*      */             } 
/* 5301 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceEntriesTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/*      */     U result;
/*      */     MapReduceEntriesTask<K, V, U> rights;
/*      */     MapReduceEntriesTask<K, V, U> nextRight;
/*      */     
/*      */     MapReduceEntriesTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceEntriesTask<K, V, U> nextRight, ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> transformer, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer) {
/* 5320 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5321 */       this.transformer = transformer;
/* 5322 */       this.reducer = reducer;
/*      */     } public final U getRawResult() {
/* 5324 */       return this.result;
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.Fun<Map.Entry<K, V>, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/* 5328 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         int f, h;
/* 5330 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5332 */           addToPendingCount(1);
/* 5333 */           (this.rights = new MapReduceEntriesTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, reducer)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5337 */         U r = null; ConcurrentHashMapV8.Node<K, V> p;
/* 5338 */         while ((p = advance()) != null) {
/*      */           U u;
/* 5340 */           if ((u = transformer.apply(p)) != null)
/* 5341 */             r = (r == null) ? u : reducer.apply(r, u); 
/*      */         } 
/* 5343 */         this.result = r;
/*      */         
/* 5345 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5347 */           MapReduceEntriesTask<K, V, U> t = (MapReduceEntriesTask)c;
/* 5348 */           MapReduceEntriesTask<K, V, U> s = t.rights;
/* 5349 */           while (s != null) {
/*      */             U sr;
/* 5351 */             if ((sr = s.result) != null) {
/* 5352 */               U tr; t.result = ((tr = t.result) == null) ? sr : reducer.apply(tr, sr);
/*      */             } 
/* 5354 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceMappingsTask<K, V, U>
/*      */     extends BulkTask<K, V, U>
/*      */   {
/*      */     final ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/*      */     U result;
/*      */     MapReduceMappingsTask<K, V, U> rights;
/*      */     MapReduceMappingsTask<K, V, U> nextRight;
/*      */     
/*      */     MapReduceMappingsTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceMappingsTask<K, V, U> nextRight, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer) {
/* 5373 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5374 */       this.transformer = transformer;
/* 5375 */       this.reducer = reducer;
/*      */     } public final U getRawResult() {
/* 5377 */       return this.result;
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer;
/*      */       ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
/* 5381 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         int f, h;
/* 5383 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5385 */           addToPendingCount(1);
/* 5386 */           (this.rights = new MapReduceMappingsTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, reducer)).fork();
/*      */         } 
/*      */ 
/*      */         
/* 5390 */         U r = null; ConcurrentHashMapV8.Node<K, V> p;
/* 5391 */         while ((p = advance()) != null) {
/*      */           U u;
/* 5393 */           if ((u = transformer.apply(p.key, p.val)) != null)
/* 5394 */             r = (r == null) ? u : reducer.apply(r, u); 
/*      */         } 
/* 5396 */         this.result = r;
/*      */         
/* 5398 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5400 */           MapReduceMappingsTask<K, V, U> t = (MapReduceMappingsTask)c;
/* 5401 */           MapReduceMappingsTask<K, V, U> s = t.rights;
/* 5402 */           while (s != null) {
/*      */             U sr;
/* 5404 */             if ((sr = s.result) != null) {
/* 5405 */               U tr; t.result = ((tr = t.result) == null) ? sr : reducer.apply(tr, sr);
/*      */             } 
/* 5407 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceKeysToDoubleTask<K, V>
/*      */     extends BulkTask<K, V, Double>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToDouble<? super K> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/*      */     
/*      */     final double basis;
/*      */     double result;
/*      */     MapReduceKeysToDoubleTask<K, V> rights;
/*      */     MapReduceKeysToDoubleTask<K, V> nextRight;
/*      */     
/*      */     MapReduceKeysToDoubleTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceKeysToDoubleTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToDouble<? super K> transformer, double basis, ConcurrentHashMapV8.DoubleByDoubleToDouble reducer) {
/* 5428 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5429 */       this.transformer = transformer;
/* 5430 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Double getRawResult() {
/* 5432 */       return Double.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToDouble<? super K> transformer;
/*      */       ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/* 5436 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5438 */         double r = this.basis; int f, h;
/* 5439 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5441 */           addToPendingCount(1);
/* 5442 */           (this.rights = new MapReduceKeysToDoubleTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5446 */         while ((p = advance()) != null)
/* 5447 */           r = reducer.apply(r, transformer.apply(p.key)); 
/* 5448 */         this.result = r;
/*      */         
/* 5450 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5452 */           MapReduceKeysToDoubleTask<K, V> t = (MapReduceKeysToDoubleTask)c;
/* 5453 */           MapReduceKeysToDoubleTask<K, V> s = t.rights;
/* 5454 */           while (s != null) {
/* 5455 */             t.result = reducer.apply(t.result, s.result);
/* 5456 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceValuesToDoubleTask<K, V>
/*      */     extends BulkTask<K, V, Double>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToDouble<? super V> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/*      */     
/*      */     final double basis;
/*      */     double result;
/*      */     MapReduceValuesToDoubleTask<K, V> rights;
/*      */     MapReduceValuesToDoubleTask<K, V> nextRight;
/*      */     
/*      */     MapReduceValuesToDoubleTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceValuesToDoubleTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToDouble<? super V> transformer, double basis, ConcurrentHashMapV8.DoubleByDoubleToDouble reducer) {
/* 5477 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5478 */       this.transformer = transformer;
/* 5479 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Double getRawResult() {
/* 5481 */       return Double.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToDouble<? super V> transformer;
/*      */       ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/* 5485 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5487 */         double r = this.basis; int f, h;
/* 5488 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5490 */           addToPendingCount(1);
/* 5491 */           (this.rights = new MapReduceValuesToDoubleTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5495 */         while ((p = advance()) != null)
/* 5496 */           r = reducer.apply(r, transformer.apply(p.val)); 
/* 5497 */         this.result = r;
/*      */         
/* 5499 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5501 */           MapReduceValuesToDoubleTask<K, V> t = (MapReduceValuesToDoubleTask)c;
/* 5502 */           MapReduceValuesToDoubleTask<K, V> s = t.rights;
/* 5503 */           while (s != null) {
/* 5504 */             t.result = reducer.apply(t.result, s.result);
/* 5505 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceEntriesToDoubleTask<K, V>
/*      */     extends BulkTask<K, V, Double>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToDouble<Map.Entry<K, V>> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/*      */     
/*      */     final double basis;
/*      */     double result;
/*      */     MapReduceEntriesToDoubleTask<K, V> rights;
/*      */     MapReduceEntriesToDoubleTask<K, V> nextRight;
/*      */     
/*      */     MapReduceEntriesToDoubleTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceEntriesToDoubleTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToDouble<Map.Entry<K, V>> transformer, double basis, ConcurrentHashMapV8.DoubleByDoubleToDouble reducer) {
/* 5526 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5527 */       this.transformer = transformer;
/* 5528 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Double getRawResult() {
/* 5530 */       return Double.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToDouble<Map.Entry<K, V>> transformer;
/*      */       ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/* 5534 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5536 */         double r = this.basis; int f, h;
/* 5537 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5539 */           addToPendingCount(1);
/* 5540 */           (this.rights = new MapReduceEntriesToDoubleTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5544 */         while ((p = advance()) != null)
/* 5545 */           r = reducer.apply(r, transformer.apply(p)); 
/* 5546 */         this.result = r;
/*      */         
/* 5548 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5550 */           MapReduceEntriesToDoubleTask<K, V> t = (MapReduceEntriesToDoubleTask)c;
/* 5551 */           MapReduceEntriesToDoubleTask<K, V> s = t.rights;
/* 5552 */           while (s != null) {
/* 5553 */             t.result = reducer.apply(t.result, s.result);
/* 5554 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceMappingsToDoubleTask<K, V>
/*      */     extends BulkTask<K, V, Double>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectByObjectToDouble<? super K, ? super V> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/*      */     
/*      */     final double basis;
/*      */     double result;
/*      */     MapReduceMappingsToDoubleTask<K, V> rights;
/*      */     MapReduceMappingsToDoubleTask<K, V> nextRight;
/*      */     
/*      */     MapReduceMappingsToDoubleTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceMappingsToDoubleTask<K, V> nextRight, ConcurrentHashMapV8.ObjectByObjectToDouble<? super K, ? super V> transformer, double basis, ConcurrentHashMapV8.DoubleByDoubleToDouble reducer) {
/* 5575 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5576 */       this.transformer = transformer;
/* 5577 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Double getRawResult() {
/* 5579 */       return Double.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectByObjectToDouble<? super K, ? super V> transformer;
/*      */       ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
/* 5583 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5585 */         double r = this.basis; int f, h;
/* 5586 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5588 */           addToPendingCount(1);
/* 5589 */           (this.rights = new MapReduceMappingsToDoubleTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5593 */         while ((p = advance()) != null)
/* 5594 */           r = reducer.apply(r, transformer.apply(p.key, p.val)); 
/* 5595 */         this.result = r;
/*      */         
/* 5597 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5599 */           MapReduceMappingsToDoubleTask<K, V> t = (MapReduceMappingsToDoubleTask)c;
/* 5600 */           MapReduceMappingsToDoubleTask<K, V> s = t.rights;
/* 5601 */           while (s != null) {
/* 5602 */             t.result = reducer.apply(t.result, s.result);
/* 5603 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceKeysToLongTask<K, V>
/*      */     extends BulkTask<K, V, Long>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToLong<? super K> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.LongByLongToLong reducer;
/*      */     
/*      */     final long basis;
/*      */     long result;
/*      */     MapReduceKeysToLongTask<K, V> rights;
/*      */     MapReduceKeysToLongTask<K, V> nextRight;
/*      */     
/*      */     MapReduceKeysToLongTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceKeysToLongTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToLong<? super K> transformer, long basis, ConcurrentHashMapV8.LongByLongToLong reducer) {
/* 5624 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5625 */       this.transformer = transformer;
/* 5626 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Long getRawResult() {
/* 5628 */       return Long.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToLong<? super K> transformer;
/*      */       ConcurrentHashMapV8.LongByLongToLong reducer;
/* 5632 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5634 */         long r = this.basis; int f, h;
/* 5635 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5637 */           addToPendingCount(1);
/* 5638 */           (this.rights = new MapReduceKeysToLongTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5642 */         while ((p = advance()) != null)
/* 5643 */           r = reducer.apply(r, transformer.apply(p.key)); 
/* 5644 */         this.result = r;
/*      */         
/* 5646 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5648 */           MapReduceKeysToLongTask<K, V> t = (MapReduceKeysToLongTask)c;
/* 5649 */           MapReduceKeysToLongTask<K, V> s = t.rights;
/* 5650 */           while (s != null) {
/* 5651 */             t.result = reducer.apply(t.result, s.result);
/* 5652 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceValuesToLongTask<K, V>
/*      */     extends BulkTask<K, V, Long>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToLong<? super V> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.LongByLongToLong reducer;
/*      */     
/*      */     final long basis;
/*      */     long result;
/*      */     MapReduceValuesToLongTask<K, V> rights;
/*      */     MapReduceValuesToLongTask<K, V> nextRight;
/*      */     
/*      */     MapReduceValuesToLongTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceValuesToLongTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToLong<? super V> transformer, long basis, ConcurrentHashMapV8.LongByLongToLong reducer) {
/* 5673 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5674 */       this.transformer = transformer;
/* 5675 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Long getRawResult() {
/* 5677 */       return Long.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToLong<? super V> transformer;
/*      */       ConcurrentHashMapV8.LongByLongToLong reducer;
/* 5681 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5683 */         long r = this.basis; int f, h;
/* 5684 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5686 */           addToPendingCount(1);
/* 5687 */           (this.rights = new MapReduceValuesToLongTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5691 */         while ((p = advance()) != null)
/* 5692 */           r = reducer.apply(r, transformer.apply(p.val)); 
/* 5693 */         this.result = r;
/*      */         
/* 5695 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5697 */           MapReduceValuesToLongTask<K, V> t = (MapReduceValuesToLongTask)c;
/* 5698 */           MapReduceValuesToLongTask<K, V> s = t.rights;
/* 5699 */           while (s != null) {
/* 5700 */             t.result = reducer.apply(t.result, s.result);
/* 5701 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceEntriesToLongTask<K, V>
/*      */     extends BulkTask<K, V, Long>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToLong<Map.Entry<K, V>> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.LongByLongToLong reducer;
/*      */     
/*      */     final long basis;
/*      */     long result;
/*      */     MapReduceEntriesToLongTask<K, V> rights;
/*      */     MapReduceEntriesToLongTask<K, V> nextRight;
/*      */     
/*      */     MapReduceEntriesToLongTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceEntriesToLongTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToLong<Map.Entry<K, V>> transformer, long basis, ConcurrentHashMapV8.LongByLongToLong reducer) {
/* 5722 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5723 */       this.transformer = transformer;
/* 5724 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Long getRawResult() {
/* 5726 */       return Long.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToLong<Map.Entry<K, V>> transformer;
/*      */       ConcurrentHashMapV8.LongByLongToLong reducer;
/* 5730 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5732 */         long r = this.basis; int f, h;
/* 5733 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5735 */           addToPendingCount(1);
/* 5736 */           (this.rights = new MapReduceEntriesToLongTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5740 */         while ((p = advance()) != null)
/* 5741 */           r = reducer.apply(r, transformer.apply(p)); 
/* 5742 */         this.result = r;
/*      */         
/* 5744 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5746 */           MapReduceEntriesToLongTask<K, V> t = (MapReduceEntriesToLongTask)c;
/* 5747 */           MapReduceEntriesToLongTask<K, V> s = t.rights;
/* 5748 */           while (s != null) {
/* 5749 */             t.result = reducer.apply(t.result, s.result);
/* 5750 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceMappingsToLongTask<K, V>
/*      */     extends BulkTask<K, V, Long>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectByObjectToLong<? super K, ? super V> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.LongByLongToLong reducer;
/*      */     
/*      */     final long basis;
/*      */     long result;
/*      */     MapReduceMappingsToLongTask<K, V> rights;
/*      */     MapReduceMappingsToLongTask<K, V> nextRight;
/*      */     
/*      */     MapReduceMappingsToLongTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceMappingsToLongTask<K, V> nextRight, ConcurrentHashMapV8.ObjectByObjectToLong<? super K, ? super V> transformer, long basis, ConcurrentHashMapV8.LongByLongToLong reducer) {
/* 5771 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5772 */       this.transformer = transformer;
/* 5773 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Long getRawResult() {
/* 5775 */       return Long.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectByObjectToLong<? super K, ? super V> transformer;
/*      */       ConcurrentHashMapV8.LongByLongToLong reducer;
/* 5779 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5781 */         long r = this.basis; int f, h;
/* 5782 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5784 */           addToPendingCount(1);
/* 5785 */           (this.rights = new MapReduceMappingsToLongTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5789 */         while ((p = advance()) != null)
/* 5790 */           r = reducer.apply(r, transformer.apply(p.key, p.val)); 
/* 5791 */         this.result = r;
/*      */         
/* 5793 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5795 */           MapReduceMappingsToLongTask<K, V> t = (MapReduceMappingsToLongTask)c;
/* 5796 */           MapReduceMappingsToLongTask<K, V> s = t.rights;
/* 5797 */           while (s != null) {
/* 5798 */             t.result = reducer.apply(t.result, s.result);
/* 5799 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceKeysToIntTask<K, V>
/*      */     extends BulkTask<K, V, Integer>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToInt<? super K> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.IntByIntToInt reducer;
/*      */     
/*      */     final int basis;
/*      */     int result;
/*      */     MapReduceKeysToIntTask<K, V> rights;
/*      */     MapReduceKeysToIntTask<K, V> nextRight;
/*      */     
/*      */     MapReduceKeysToIntTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceKeysToIntTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToInt<? super K> transformer, int basis, ConcurrentHashMapV8.IntByIntToInt reducer) {
/* 5820 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5821 */       this.transformer = transformer;
/* 5822 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Integer getRawResult() {
/* 5824 */       return Integer.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToInt<? super K> transformer;
/*      */       ConcurrentHashMapV8.IntByIntToInt reducer;
/* 5828 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5830 */         int r = this.basis; int f, h;
/* 5831 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5833 */           addToPendingCount(1);
/* 5834 */           (this.rights = new MapReduceKeysToIntTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5838 */         while ((p = advance()) != null)
/* 5839 */           r = reducer.apply(r, transformer.apply(p.key)); 
/* 5840 */         this.result = r;
/*      */         
/* 5842 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5844 */           MapReduceKeysToIntTask<K, V> t = (MapReduceKeysToIntTask)c;
/* 5845 */           MapReduceKeysToIntTask<K, V> s = t.rights;
/* 5846 */           while (s != null) {
/* 5847 */             t.result = reducer.apply(t.result, s.result);
/* 5848 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceValuesToIntTask<K, V>
/*      */     extends BulkTask<K, V, Integer>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToInt<? super V> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.IntByIntToInt reducer;
/*      */     
/*      */     final int basis;
/*      */     int result;
/*      */     MapReduceValuesToIntTask<K, V> rights;
/*      */     MapReduceValuesToIntTask<K, V> nextRight;
/*      */     
/*      */     MapReduceValuesToIntTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceValuesToIntTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToInt<? super V> transformer, int basis, ConcurrentHashMapV8.IntByIntToInt reducer) {
/* 5869 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5870 */       this.transformer = transformer;
/* 5871 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Integer getRawResult() {
/* 5873 */       return Integer.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToInt<? super V> transformer;
/*      */       ConcurrentHashMapV8.IntByIntToInt reducer;
/* 5877 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5879 */         int r = this.basis; int f, h;
/* 5880 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5882 */           addToPendingCount(1);
/* 5883 */           (this.rights = new MapReduceValuesToIntTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5887 */         while ((p = advance()) != null)
/* 5888 */           r = reducer.apply(r, transformer.apply(p.val)); 
/* 5889 */         this.result = r;
/*      */         
/* 5891 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5893 */           MapReduceValuesToIntTask<K, V> t = (MapReduceValuesToIntTask)c;
/* 5894 */           MapReduceValuesToIntTask<K, V> s = t.rights;
/* 5895 */           while (s != null) {
/* 5896 */             t.result = reducer.apply(t.result, s.result);
/* 5897 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceEntriesToIntTask<K, V>
/*      */     extends BulkTask<K, V, Integer>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectToInt<Map.Entry<K, V>> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.IntByIntToInt reducer;
/*      */     
/*      */     final int basis;
/*      */     int result;
/*      */     MapReduceEntriesToIntTask<K, V> rights;
/*      */     MapReduceEntriesToIntTask<K, V> nextRight;
/*      */     
/*      */     MapReduceEntriesToIntTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceEntriesToIntTask<K, V> nextRight, ConcurrentHashMapV8.ObjectToInt<Map.Entry<K, V>> transformer, int basis, ConcurrentHashMapV8.IntByIntToInt reducer) {
/* 5918 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5919 */       this.transformer = transformer;
/* 5920 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Integer getRawResult() {
/* 5922 */       return Integer.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectToInt<Map.Entry<K, V>> transformer;
/*      */       ConcurrentHashMapV8.IntByIntToInt reducer;
/* 5926 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5928 */         int r = this.basis; int f, h;
/* 5929 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5931 */           addToPendingCount(1);
/* 5932 */           (this.rights = new MapReduceEntriesToIntTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5936 */         while ((p = advance()) != null)
/* 5937 */           r = reducer.apply(r, transformer.apply(p)); 
/* 5938 */         this.result = r;
/*      */         
/* 5940 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5942 */           MapReduceEntriesToIntTask<K, V> t = (MapReduceEntriesToIntTask)c;
/* 5943 */           MapReduceEntriesToIntTask<K, V> s = t.rights;
/* 5944 */           while (s != null) {
/* 5945 */             t.result = reducer.apply(t.result, s.result);
/* 5946 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static final class MapReduceMappingsToIntTask<K, V>
/*      */     extends BulkTask<K, V, Integer>
/*      */   {
/*      */     final ConcurrentHashMapV8.ObjectByObjectToInt<? super K, ? super V> transformer;
/*      */     
/*      */     final ConcurrentHashMapV8.IntByIntToInt reducer;
/*      */     
/*      */     final int basis;
/*      */     int result;
/*      */     MapReduceMappingsToIntTask<K, V> rights;
/*      */     MapReduceMappingsToIntTask<K, V> nextRight;
/*      */     
/*      */     MapReduceMappingsToIntTask(ConcurrentHashMapV8.BulkTask<K, V, ?> p, int b, int i, int f, ConcurrentHashMapV8.Node<K, V>[] t, MapReduceMappingsToIntTask<K, V> nextRight, ConcurrentHashMapV8.ObjectByObjectToInt<? super K, ? super V> transformer, int basis, ConcurrentHashMapV8.IntByIntToInt reducer) {
/* 5967 */       super(p, b, i, f, t); this.nextRight = nextRight;
/* 5968 */       this.transformer = transformer;
/* 5969 */       this.basis = basis; this.reducer = reducer;
/*      */     } public final Integer getRawResult() {
/* 5971 */       return Integer.valueOf(this.result);
/*      */     } public final void compute() {
/*      */       ConcurrentHashMapV8.ObjectByObjectToInt<? super K, ? super V> transformer;
/*      */       ConcurrentHashMapV8.IntByIntToInt reducer;
/* 5975 */       if ((transformer = this.transformer) != null && (reducer = this.reducer) != null) {
/*      */         
/* 5977 */         int r = this.basis; int f, h;
/* 5978 */         for (int i = this.baseIndex; this.batch > 0 && (h = (f = this.baseLimit) + i >>> 1) > i; ) {
/*      */           
/* 5980 */           addToPendingCount(1);
/* 5981 */           (this.rights = new MapReduceMappingsToIntTask(this, this.batch >>>= 1, this.baseLimit = h, f, this.tab, this.rights, transformer, r, reducer)).fork();
/*      */         } 
/*      */         
/*      */         ConcurrentHashMapV8.Node<K, V> p;
/* 5985 */         while ((p = advance()) != null)
/* 5986 */           r = reducer.apply(r, transformer.apply(p.key, p.val)); 
/* 5987 */         this.result = r;
/*      */         
/* 5989 */         for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
/*      */           
/* 5991 */           MapReduceMappingsToIntTask<K, V> t = (MapReduceMappingsToIntTask)c;
/* 5992 */           MapReduceMappingsToIntTask<K, V> s = t.rights;
/* 5993 */           while (s != null) {
/* 5994 */             t.result = reducer.apply(t.result, s.result);
/* 5995 */             s = t.rights = s.nextRight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static final class CounterCell {
/*      */     volatile long p0;
/*      */     volatile long p1;
/*      */     volatile long p2;
/*      */     volatile long p3;
/*      */     volatile long p4;
/*      */     volatile long p5;
/*      */     volatile long p6;
/*      */     
/*      */     CounterCell(long x) {
/* 6012 */       this.value = x;
/*      */     }
/*      */     
/*      */     volatile long value;
/*      */     volatile long q0;
/*      */     volatile long q1;
/*      */     volatile long q2;
/*      */     volatile long q3;
/*      */     volatile long q4;
/*      */     volatile long q5;
/*      */     volatile long q6; }
/*      */   
/*      */   static final class CounterHashCode {
/*      */     int code;
/*      */   }
/* 6027 */   static final AtomicInteger counterHashCodeGenerator = new AtomicInteger(); static final int SEED_INCREMENT = 1640531527; private static final Unsafe U; private static final long SIZECTL; private static final long TRANSFERINDEX;
/*      */   private static final long TRANSFERORIGIN;
/*      */   private static final long BASECOUNT;
/*      */   private static final long CELLSBUSY;
/*      */   private static final long CELLVALUE;
/*      */   private static final long ABASE;
/*      */   private static final int ASHIFT;
/*      */   
/*      */   final long sumCount() {
/* 6036 */     CounterCell[] as = this.counterCells;
/* 6037 */     long sum = this.baseCount;
/* 6038 */     if (as != null)
/* 6039 */       for (int i = 0; i < as.length; i++) {
/* 6040 */         CounterCell a; if ((a = as[i]) != null) {
/* 6041 */           sum += a.value;
/*      */         }
/*      */       }  
/* 6044 */     return sum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void fullAddCount(InternalThreadLocalMap threadLocals, long x, IntegerHolder hc, boolean wasUncontended) {
/*      */     int h;
/* 6052 */     if (hc == null) {
/* 6053 */       hc = new IntegerHolder();
/* 6054 */       int s = counterHashCodeGenerator.addAndGet(1640531527);
/* 6055 */       h = hc.value = (s == 0) ? 1 : s;
/* 6056 */       threadLocals.setCounterHashCode(hc);
/*      */     } else {
/*      */       
/* 6059 */       h = hc.value;
/* 6060 */     }  boolean collide = false; while (true) {
/*      */       CounterCell[] as;
/*      */       int n;
/* 6063 */       if ((as = this.counterCells) != null && (n = as.length) > 0) {
/* 6064 */         CounterCell a; if ((a = as[n - 1 & h]) == null)
/* 6065 */         { if (this.cellsBusy == 0) {
/* 6066 */             CounterCell r = new CounterCell(x);
/* 6067 */             if (this.cellsBusy == 0 && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
/*      */               
/* 6069 */               boolean created = false; try {
/*      */                 CounterCell[] rs; int m;
/*      */                 int j;
/* 6072 */                 if ((rs = this.counterCells) != null && (m = rs.length) > 0 && rs[j = m - 1 & h] == null) {
/*      */ 
/*      */                   
/* 6075 */                   rs[j] = r;
/* 6076 */                   created = true;
/*      */                 } 
/*      */               } finally {
/* 6079 */                 this.cellsBusy = 0;
/*      */               } 
/* 6081 */               if (created)
/*      */                 break; 
/*      */               continue;
/*      */             } 
/*      */           } 
/* 6086 */           collide = false; }
/*      */         
/* 6088 */         else if (!wasUncontended)
/* 6089 */         { wasUncontended = true; }
/* 6090 */         else { long l; if (U.compareAndSwapLong(a, CELLVALUE, l = a.value, l + x))
/*      */             break; 
/* 6092 */           if (this.counterCells != as || n >= NCPU) {
/* 6093 */             collide = false;
/* 6094 */           } else if (!collide) {
/* 6095 */             collide = true;
/* 6096 */           } else if (this.cellsBusy == 0 && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
/*      */             
/*      */             try {
/* 6099 */               if (this.counterCells == as) {
/* 6100 */                 CounterCell[] rs = new CounterCell[n << 1];
/* 6101 */                 for (int i = 0; i < n; i++)
/* 6102 */                   rs[i] = as[i]; 
/* 6103 */                 this.counterCells = rs;
/*      */               } 
/*      */             } finally {
/* 6106 */               this.cellsBusy = 0;
/*      */             } 
/* 6108 */             collide = false; continue;
/*      */           }  }
/*      */         
/* 6111 */         h ^= h << 13;
/* 6112 */         h ^= h >>> 17;
/* 6113 */         h ^= h << 5; continue;
/*      */       } 
/* 6115 */       if (this.cellsBusy == 0 && this.counterCells == as && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
/*      */         
/* 6117 */         boolean init = false;
/*      */         try {
/* 6119 */           if (this.counterCells == as) {
/* 6120 */             CounterCell[] rs = new CounterCell[2];
/* 6121 */             rs[h & 0x1] = new CounterCell(x);
/* 6122 */             this.counterCells = rs;
/* 6123 */             init = true;
/*      */           } 
/*      */         } finally {
/* 6126 */           this.cellsBusy = 0;
/*      */         } 
/* 6128 */         if (init)
/*      */           break;  continue;
/*      */       }  long v;
/* 6131 */       if (U.compareAndSwapLong(this, BASECOUNT, v = this.baseCount, v + x))
/*      */         break; 
/*      */     } 
/* 6134 */     hc.value = h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*      */     try {
/* 6150 */       U = getUnsafe();
/* 6151 */       Class<?> k = ConcurrentHashMapV8.class;
/* 6152 */       SIZECTL = U.objectFieldOffset(k.getDeclaredField("sizeCtl"));
/*      */       
/* 6154 */       TRANSFERINDEX = U.objectFieldOffset(k.getDeclaredField("transferIndex"));
/*      */       
/* 6156 */       TRANSFERORIGIN = U.objectFieldOffset(k.getDeclaredField("transferOrigin"));
/*      */       
/* 6158 */       BASECOUNT = U.objectFieldOffset(k.getDeclaredField("baseCount"));
/*      */       
/* 6160 */       CELLSBUSY = U.objectFieldOffset(k.getDeclaredField("cellsBusy"));
/*      */       
/* 6162 */       Class<?> ck = CounterCell.class;
/* 6163 */       CELLVALUE = U.objectFieldOffset(ck.getDeclaredField("value"));
/*      */       
/* 6165 */       Class<?> ak = Node[].class;
/* 6166 */       ABASE = U.arrayBaseOffset(ak);
/* 6167 */       int scale = U.arrayIndexScale(ak);
/* 6168 */       if ((scale & scale - 1) != 0)
/* 6169 */         throw new Error("data type scale not a power of two"); 
/* 6170 */       ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
/* 6171 */     } catch (Exception e) {
/* 6172 */       throw new Error(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Unsafe getUnsafe() {
/*      */     try {
/* 6185 */       return Unsafe.getUnsafe();
/* 6186 */     } catch (SecurityException tryReflectionInstead) {
/*      */       try {
/* 6188 */         return AccessController.<Unsafe>doPrivileged(new PrivilegedExceptionAction<Unsafe>()
/*      */             {
/*      */               public Unsafe run() throws Exception {
/* 6191 */                 Class<Unsafe> k = Unsafe.class;
/* 6192 */                 for (Field f : k.getDeclaredFields()) {
/* 6193 */                   f.setAccessible(true);
/* 6194 */                   Object x = f.get((Object)null);
/* 6195 */                   if (k.isInstance(x))
/* 6196 */                     return k.cast(x); 
/*      */                 } 
/* 6198 */                 throw new NoSuchFieldError("the Unsafe"); }
/*      */             });
/* 6200 */       } catch (PrivilegedActionException e) {
/* 6201 */         throw new RuntimeException("Could not initialize intrinsics", e.getCause());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static interface IntByIntToInt {
/*      */     int apply(int param1Int1, int param1Int2);
/*      */   }
/*      */   
/*      */   public static interface LongByLongToLong {
/*      */     long apply(long param1Long1, long param1Long2);
/*      */   }
/*      */   
/*      */   public static interface DoubleByDoubleToDouble {
/*      */     double apply(double param1Double1, double param1Double2);
/*      */   }
/*      */   
/*      */   public static interface ObjectByObjectToInt<A, B> {
/*      */     int apply(A param1A, B param1B);
/*      */   }
/*      */   
/*      */   public static interface ObjectByObjectToLong<A, B> {
/*      */     long apply(A param1A, B param1B);
/*      */   }
/*      */   
/*      */   public static interface ObjectByObjectToDouble<A, B> {
/*      */     double apply(A param1A, B param1B);
/*      */   }
/*      */   
/*      */   public static interface ObjectToInt<A> {
/*      */     int apply(A param1A);
/*      */   }
/*      */   
/*      */   public static interface ObjectToLong<A> {
/*      */     long apply(A param1A);
/*      */   }
/*      */   
/*      */   public static interface ObjectToDouble<A> {
/*      */     double apply(A param1A);
/*      */   }
/*      */   
/*      */   public static interface BiFun<A, B, T> {
/*      */     T apply(A param1A, B param1B);
/*      */   }
/*      */   
/*      */   public static interface Fun<A, T> {
/*      */     T apply(A param1A);
/*      */   }
/*      */   
/*      */   public static interface BiAction<A, B> {
/*      */     void apply(A param1A, B param1B);
/*      */   }
/*      */   
/*      */   public static interface Action<A> {
/*      */     void apply(A param1A);
/*      */   }
/*      */   
/*      */   public static interface ConcurrentHashMapSpliterator<T> {
/*      */     ConcurrentHashMapSpliterator<T> trySplit();
/*      */     
/*      */     long estimateSize();
/*      */     
/*      */     void forEachRemaining(ConcurrentHashMapV8.Action<? super T> param1Action);
/*      */     
/*      */     boolean tryAdvance(ConcurrentHashMapV8.Action<? super T> param1Action);
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\nett\\util\internal\chmv8\ConcurrentHashMapV8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */