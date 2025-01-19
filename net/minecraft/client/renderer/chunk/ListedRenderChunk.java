/*    */ package net.minecraft.client.renderer.chunk;
/*    */ 
/*    */ import net.minecraft.client.renderer.GLAllocation;
/*    */ import net.minecraft.client.renderer.RenderGlobal;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ListedRenderChunk extends RenderChunk {
/* 10 */   private final int baseDisplayList = GLAllocation.generateDisplayLists((EnumWorldBlockLayer.values()).length);
/*    */   
/*    */   public ListedRenderChunk(World worldIn, RenderGlobal renderGlobalIn, BlockPos pos, int indexIn) {
/* 13 */     super(worldIn, renderGlobalIn, pos, indexIn);
/*    */   }
/*    */   
/*    */   public int getDisplayList(EnumWorldBlockLayer layer, CompiledChunk p_178600_2_) {
/* 17 */     return !p_178600_2_.isLayerEmpty(layer) ? (this.baseDisplayList + layer.ordinal()) : -1;
/*    */   }
/*    */   
/*    */   public void deleteGlResources() {
/* 21 */     super.deleteGlResources();
/* 22 */     GLAllocation.deleteDisplayLists(this.baseDisplayList, (EnumWorldBlockLayer.values()).length);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\client\renderer\chunk\ListedRenderChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */