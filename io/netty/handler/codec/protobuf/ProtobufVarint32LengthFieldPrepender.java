/*    */ package io.netty.handler.codec.protobuf;
/*    */ 
/*    */ import com.google.protobuf.CodedOutputStream;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.ByteBufOutputStream;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import java.io.OutputStream;
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
/*    */ public class ProtobufVarint32LengthFieldPrepender
/*    */   extends MessageToByteEncoder<ByteBuf>
/*    */ {
/*    */   protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
/* 45 */     int bodyLen = msg.readableBytes();
/* 46 */     int headerLen = CodedOutputStream.computeRawVarint32Size(bodyLen);
/* 47 */     out.ensureWritable(headerLen + bodyLen);
/*    */     
/* 49 */     CodedOutputStream headerOut = CodedOutputStream.newInstance((OutputStream)new ByteBufOutputStream(out), headerLen);
/*    */     
/* 51 */     headerOut.writeRawVarint32(bodyLen);
/* 52 */     headerOut.flush();
/*    */     
/* 54 */     out.writeBytes(msg, msg.readerIndex(), bodyLen);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\netty\handler\codec\protobuf\ProtobufVarint32LengthFieldPrepender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */