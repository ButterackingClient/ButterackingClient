/*    */ package net.minecraft.nbt;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTTagShort
/*    */   extends NBTBase.NBTPrimitive
/*    */ {
/*    */   private short data;
/*    */   
/*    */   public NBTTagShort() {}
/*    */   
/*    */   public NBTTagShort(short data) {
/* 17 */     this.data = data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput output) throws IOException {
/* 24 */     output.writeShort(this.data);
/*    */   }
/*    */   
/*    */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/* 28 */     sizeTracker.read(80L);
/* 29 */     this.data = input.readShort();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getId() {
/* 36 */     return 2;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 40 */     return this.data + "s";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 47 */     return new NBTTagShort(this.data);
/*    */   }
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 51 */     if (super.equals(p_equals_1_)) {
/* 52 */       NBTTagShort nbttagshort = (NBTTagShort)p_equals_1_;
/* 53 */       return (this.data == nbttagshort.data);
/*    */     } 
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 60 */     return super.hashCode() ^ this.data;
/*    */   }
/*    */   
/*    */   public long getLong() {
/* 64 */     return this.data;
/*    */   }
/*    */   
/*    */   public int getInt() {
/* 68 */     return this.data;
/*    */   }
/*    */   
/*    */   public short getShort() {
/* 72 */     return this.data;
/*    */   }
/*    */   
/*    */   public byte getByte() {
/* 76 */     return (byte)(this.data & 0xFF);
/*    */   }
/*    */   
/*    */   public double getDouble() {
/* 80 */     return this.data;
/*    */   }
/*    */   
/*    */   public float getFloat() {
/* 84 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\nbt\NBTTagShort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */