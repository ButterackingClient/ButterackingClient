/*    */ package io.netty.handler.codec.rtsp;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.handler.codec.http.HttpMessage;
/*    */ import io.netty.handler.codec.http.HttpObjectEncoder;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Sharable
/*    */ public abstract class RtspObjectEncoder<H extends HttpMessage>
/*    */   extends HttpObjectEncoder<H>
/*    */ {
/*    */   public boolean acceptOutboundMessage(Object msg) throws Exception {
/* 39 */     return msg instanceof io.netty.handler.codec.http.FullHttpMessage;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\handler\codec\rtsp\RtspObjectEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */