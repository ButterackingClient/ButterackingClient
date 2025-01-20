/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.Vec3i;
/*    */ import net.minecraft.world.ChunkCoordIntPair;
/*    */ import net.minecraft.world.chunk.Chunk;
/*    */ 
/*    */ public class S22PacketMultiBlockChange implements Packet<INetHandlerPlayClient> {
/*    */   private ChunkCoordIntPair chunkPosCoord;
/*    */   private BlockUpdateData[] changedBlocks;
/*    */   
/*    */   public S22PacketMultiBlockChange() {}
/*    */   
/*    */   public S22PacketMultiBlockChange(int p_i45181_1_, short[] crammedPositionsIn, Chunk chunkIn) {
/* 22 */     this.chunkPosCoord = new ChunkCoordIntPair(chunkIn.xPosition, chunkIn.zPosition);
/* 23 */     this.changedBlocks = new BlockUpdateData[p_i45181_1_];
/*    */     
/* 25 */     for (int i = 0; i < this.changedBlocks.length; i++) {
/* 26 */       this.changedBlocks[i] = new BlockUpdateData(crammedPositionsIn[i], chunkIn);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer buf) throws IOException {
/* 34 */     this.chunkPosCoord = new ChunkCoordIntPair(buf.readInt(), buf.readInt());
/* 35 */     this.changedBlocks = new BlockUpdateData[buf.readVarIntFromBuffer()];
/*    */     
/* 37 */     for (int i = 0; i < this.changedBlocks.length; i++) {
/* 38 */       this.changedBlocks[i] = new BlockUpdateData(buf.readShort(), (IBlockState)Block.BLOCK_STATE_IDS.getByValue(buf.readVarIntFromBuffer()));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer buf) throws IOException {
/* 46 */     buf.writeInt(this.chunkPosCoord.chunkXPos);
/* 47 */     buf.writeInt(this.chunkPosCoord.chunkZPos);
/* 48 */     buf.writeVarIntToBuffer(this.changedBlocks.length); byte b; int i;
/*    */     BlockUpdateData[] arrayOfBlockUpdateData;
/* 50 */     for (i = (arrayOfBlockUpdateData = this.changedBlocks).length, b = 0; b < i; ) { BlockUpdateData s22packetmultiblockchange$blockupdatedata = arrayOfBlockUpdateData[b];
/* 51 */       buf.writeShort(s22packetmultiblockchange$blockupdatedata.func_180089_b());
/* 52 */       buf.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(s22packetmultiblockchange$blockupdatedata.getBlockState()));
/*    */       b++; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 60 */     handler.handleMultiBlockChange(this);
/*    */   }
/*    */   
/*    */   public BlockUpdateData[] getChangedBlocks() {
/* 64 */     return this.changedBlocks;
/*    */   }
/*    */   
/*    */   public class BlockUpdateData {
/*    */     private final short chunkPosCrammed;
/*    */     private final IBlockState blockState;
/*    */     
/*    */     public BlockUpdateData(short p_i45984_2_, IBlockState state) {
/* 72 */       this.chunkPosCrammed = p_i45984_2_;
/* 73 */       this.blockState = state;
/*    */     }
/*    */     
/*    */     public BlockUpdateData(short p_i45985_2_, Chunk chunkIn) {
/* 77 */       this.chunkPosCrammed = p_i45985_2_;
/* 78 */       this.blockState = chunkIn.getBlockState(getPos());
/*    */     }
/*    */     
/*    */     public BlockPos getPos() {
/* 82 */       return new BlockPos((Vec3i)S22PacketMultiBlockChange.this.chunkPosCoord.getBlock(this.chunkPosCrammed >> 12 & 0xF, this.chunkPosCrammed & 0xFF, this.chunkPosCrammed >> 8 & 0xF));
/*    */     }
/*    */     
/*    */     public short func_180089_b() {
/* 86 */       return this.chunkPosCrammed;
/*    */     }
/*    */     
/*    */     public IBlockState getBlockState() {
/* 90 */       return this.blockState;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\network\play\server\S22PacketMultiBlockChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */