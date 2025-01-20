/*     */ package io.netty.handler.codec;
/*     */ 
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*     */ import io.netty.util.ReferenceCountUtil;
/*     */ import io.netty.util.internal.RecyclableArrayList;
/*     */ import io.netty.util.internal.TypeParameterMatcher;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MessageToMessageDecoder<I>
/*     */   extends ChannelInboundHandlerAdapter
/*     */ {
/*     */   private final TypeParameterMatcher matcher;
/*     */   
/*     */   protected MessageToMessageDecoder() {
/*  61 */     this.matcher = TypeParameterMatcher.find(this, MessageToMessageDecoder.class, "I");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MessageToMessageDecoder(Class<? extends I> inboundMessageType) {
/*  70 */     this.matcher = TypeParameterMatcher.get(inboundMessageType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean acceptInboundMessage(Object msg) throws Exception {
/*  78 */     return this.matcher.match(msg);
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/*  83 */     RecyclableArrayList out = RecyclableArrayList.newInstance();
/*     */     try {
/*  85 */       if (acceptInboundMessage(msg)) {
/*     */         
/*  87 */         I cast = (I)msg;
/*     */         try {
/*  89 */           decode(ctx, cast, (List<Object>)out);
/*     */         } finally {
/*  91 */           ReferenceCountUtil.release(cast);
/*     */         } 
/*     */       } else {
/*  94 */         out.add(msg);
/*     */       } 
/*  96 */     } catch (DecoderException e) {
/*  97 */       throw e;
/*  98 */     } catch (Exception e) {
/*  99 */       throw new DecoderException(e);
/*     */     } finally {
/* 101 */       int size = out.size();
/* 102 */       for (int i = 0; i < size; i++) {
/* 103 */         ctx.fireChannelRead(out.get(i));
/*     */       }
/* 105 */       out.recycle();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void decode(ChannelHandlerContext paramChannelHandlerContext, I paramI, List<Object> paramList) throws Exception;
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\handler\codec\MessageToMessageDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */