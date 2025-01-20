/*    */ package net.minecraft.nbt;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class NBTTagEnd extends NBTBase {
/*    */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  9 */     sizeTracker.read(64L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput output) throws IOException {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getId() {
/* 22 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     return "END";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 33 */     return new NBTTagEnd();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\nbt\NBTTagEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */