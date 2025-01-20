/*    */ package io.netty.handler.codec.protobuf;
/*    */ 
/*    */ import com.google.protobuf.MessageLite;
/*    */ import com.google.protobuf.MessageLiteOrBuilder;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageEncoder;
/*    */ import java.util.List;
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
/*    */ public class ProtobufEncoder
/*    */   extends MessageToMessageEncoder<MessageLiteOrBuilder>
/*    */ {
/*    */   protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
/* 65 */     if (msg instanceof MessageLite) {
/* 66 */       out.add(Unpooled.wrappedBuffer(((MessageLite)msg).toByteArray()));
/*    */       return;
/*    */     } 
/* 69 */     if (msg instanceof MessageLite.Builder)
/* 70 */       out.add(Unpooled.wrappedBuffer(((MessageLite.Builder)msg).build().toByteArray())); 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\handler\codec\protobuf\ProtobufEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */