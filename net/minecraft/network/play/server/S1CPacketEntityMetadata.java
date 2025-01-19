/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.DataWatcher;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S1CPacketEntityMetadata
/*    */   implements Packet<INetHandlerPlayClient> {
/*    */   private int entityId;
/*    */   private List<DataWatcher.WatchableObject> field_149378_b;
/*    */   
/*    */   public S1CPacketEntityMetadata() {}
/*    */   
/*    */   public S1CPacketEntityMetadata(int entityIdIn, DataWatcher p_i45217_2_, boolean p_i45217_3_) {
/* 19 */     this.entityId = entityIdIn;
/*    */     
/* 21 */     if (p_i45217_3_) {
/* 22 */       this.field_149378_b = p_i45217_2_.getAllWatched();
/*    */     } else {
/* 24 */       this.field_149378_b = p_i45217_2_.getChanged();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer buf) throws IOException {
/* 32 */     this.entityId = buf.readVarIntFromBuffer();
/* 33 */     this.field_149378_b = DataWatcher.readWatchedListFromPacketBuffer(buf);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer buf) throws IOException {
/* 40 */     buf.writeVarIntToBuffer(this.entityId);
/* 41 */     DataWatcher.writeWatchedListToPacketBuffer(this.field_149378_b, buf);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 48 */     handler.handleEntityMetadata(this);
/*    */   }
/*    */   
/*    */   public List<DataWatcher.WatchableObject> func_149376_c() {
/* 52 */     return this.field_149378_b;
/*    */   }
/*    */   
/*    */   public int getEntityId() {
/* 56 */     return this.entityId;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\network\play\server\S1CPacketEntityMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */