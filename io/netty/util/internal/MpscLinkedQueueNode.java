/*    */ package io.netty.util.internal;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MpscLinkedQueueNode<T>
/*    */ {
/*    */   private static final AtomicReferenceFieldUpdater<MpscLinkedQueueNode, MpscLinkedQueueNode> nextUpdater;
/*    */   private volatile MpscLinkedQueueNode<T> next;
/*    */   
/*    */   static {
/* 30 */     AtomicReferenceFieldUpdater<MpscLinkedQueueNode, MpscLinkedQueueNode> u = PlatformDependent.newAtomicReferenceFieldUpdater(MpscLinkedQueueNode.class, "next");
/* 31 */     if (u == null) {
/* 32 */       u = AtomicReferenceFieldUpdater.newUpdater(MpscLinkedQueueNode.class, MpscLinkedQueueNode.class, "next");
/*    */     }
/* 34 */     nextUpdater = u;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   final MpscLinkedQueueNode<T> next() {
/* 41 */     return this.next;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   final void setNext(MpscLinkedQueueNode<T> newNext) {
/* 47 */     nextUpdater.lazySet(this, newNext);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected T clearMaybe() {
/* 56 */     return value();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void unlink() {
/* 63 */     setNext(null);
/*    */   }
/*    */   
/*    */   public abstract T value();
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\nett\\util\internal\MpscLinkedQueueNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */