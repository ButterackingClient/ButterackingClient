/*     */ package io.netty.channel;
/*     */ 
/*     */ import io.netty.util.concurrent.DefaultProgressivePromise;
/*     */ import io.netty.util.concurrent.EventExecutor;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import io.netty.util.concurrent.ProgressiveFuture;
/*     */ import io.netty.util.concurrent.ProgressivePromise;
/*     */ import io.netty.util.concurrent.Promise;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultChannelProgressivePromise
/*     */   extends DefaultProgressivePromise<Void>
/*     */   implements ChannelProgressivePromise, ChannelFlushPromiseNotifier.FlushCheckpoint
/*     */ {
/*     */   private final Channel channel;
/*     */   private long checkpoint;
/*     */   
/*     */   public DefaultChannelProgressivePromise(Channel channel) {
/*  42 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultChannelProgressivePromise(Channel channel, EventExecutor executor) {
/*  52 */     super(executor);
/*  53 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EventExecutor executor() {
/*  58 */     EventExecutor e = super.executor();
/*  59 */     if (e == null) {
/*  60 */       return channel().eventLoop();
/*     */     }
/*  62 */     return e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Channel channel() {
/*  68 */     return this.channel;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise setSuccess() {
/*  73 */     return setSuccess((Void)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise setSuccess(Void result) {
/*  78 */     super.setSuccess(result);
/*  79 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trySuccess() {
/*  84 */     return trySuccess(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise setFailure(Throwable cause) {
/*  89 */     super.setFailure(cause);
/*  90 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise setProgress(long progress, long total) {
/*  95 */     super.setProgress(progress, total);
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> listener) {
/* 101 */     super.addListener(listener);
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
/* 107 */     super.addListeners((GenericFutureListener[])listeners);
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> listener) {
/* 113 */     super.removeListener(listener);
/* 114 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
/* 120 */     super.removeListeners((GenericFutureListener[])listeners);
/* 121 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise sync() throws InterruptedException {
/* 126 */     super.sync();
/* 127 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise syncUninterruptibly() {
/* 132 */     super.syncUninterruptibly();
/* 133 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise await() throws InterruptedException {
/* 138 */     super.await();
/* 139 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise awaitUninterruptibly() {
/* 144 */     super.awaitUninterruptibly();
/* 145 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public long flushCheckpoint() {
/* 150 */     return this.checkpoint;
/*     */   }
/*     */ 
/*     */   
/*     */   public void flushCheckpoint(long checkpoint) {
/* 155 */     this.checkpoint = checkpoint;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise promise() {
/* 160 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkDeadLock() {
/* 165 */     if (channel().isRegistered())
/* 166 */       super.checkDeadLock(); 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\channel\DefaultChannelProgressivePromise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */