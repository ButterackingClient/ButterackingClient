/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LowerStringMap<V>
/*    */   implements Map<String, V>
/*    */ {
/* 11 */   private final Map<String, V> internalMap = Maps.newLinkedHashMap();
/*    */   
/*    */   public int size() {
/* 14 */     return this.internalMap.size();
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 18 */     return this.internalMap.isEmpty();
/*    */   }
/*    */   
/*    */   public boolean containsKey(Object p_containsKey_1_) {
/* 22 */     return this.internalMap.containsKey(p_containsKey_1_.toString().toLowerCase());
/*    */   }
/*    */   
/*    */   public boolean containsValue(Object p_containsValue_1_) {
/* 26 */     return this.internalMap.containsKey(p_containsValue_1_);
/*    */   }
/*    */   
/*    */   public V get(Object p_get_1_) {
/* 30 */     return this.internalMap.get(p_get_1_.toString().toLowerCase());
/*    */   }
/*    */   
/*    */   public V put(String p_put_1_, V p_put_2_) {
/* 34 */     return this.internalMap.put(p_put_1_.toLowerCase(), p_put_2_);
/*    */   }
/*    */   
/*    */   public V remove(Object p_remove_1_) {
/* 38 */     return this.internalMap.remove(p_remove_1_.toString().toLowerCase());
/*    */   }
/*    */   
/*    */   public void putAll(Map<? extends String, ? extends V> p_putAll_1_) {
/* 42 */     for (Map.Entry<? extends String, ? extends V> entry : p_putAll_1_.entrySet()) {
/* 43 */       put(entry.getKey(), entry.getValue());
/*    */     }
/*    */   }
/*    */   
/*    */   public void clear() {
/* 48 */     this.internalMap.clear();
/*    */   }
/*    */   
/*    */   public Set<String> keySet() {
/* 52 */     return this.internalMap.keySet();
/*    */   }
/*    */   
/*    */   public Collection<V> values() {
/* 56 */     return this.internalMap.values();
/*    */   }
/*    */   
/*    */   public Set<Map.Entry<String, V>> entrySet() {
/* 60 */     return this.internalMap.entrySet();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\server\management\LowerStringMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */