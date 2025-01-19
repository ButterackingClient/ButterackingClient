/*    */ package io.netty.handler.codec.serialization;
/*    */ 
/*    */ import java.io.EOFException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectStreamClass;
/*    */ import java.io.StreamCorruptedException;
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
/*    */ class CompactObjectInputStream
/*    */   extends ObjectInputStream
/*    */ {
/*    */   private final ClassResolver classResolver;
/*    */   
/*    */   CompactObjectInputStream(InputStream in, ClassResolver classResolver) throws IOException {
/* 30 */     super(in);
/* 31 */     this.classResolver = classResolver;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void readStreamHeader() throws IOException {
/* 36 */     int version = readByte() & 0xFF;
/* 37 */     if (version != 5) {
/* 38 */       throw new StreamCorruptedException("Unsupported version: " + version);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
/*    */     String className;
/*    */     Class<?> clazz;
/* 46 */     int type = read();
/* 47 */     if (type < 0) {
/* 48 */       throw new EOFException();
/*    */     }
/* 50 */     switch (type) {
/*    */       case 0:
/* 52 */         return super.readClassDescriptor();
/*    */       case 1:
/* 54 */         className = readUTF();
/* 55 */         clazz = this.classResolver.resolve(className);
/* 56 */         return ObjectStreamClass.lookupAny(clazz);
/*    */     } 
/* 58 */     throw new StreamCorruptedException("Unexpected class descriptor type: " + type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
/*    */     Class<?> clazz;
/*    */     try {
/* 67 */       clazz = this.classResolver.resolve(desc.getName());
/* 68 */     } catch (ClassNotFoundException ignored) {
/* 69 */       clazz = super.resolveClass(desc);
/*    */     } 
/*    */     
/* 72 */     return clazz;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\handler\codec\serialization\CompactObjectInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */