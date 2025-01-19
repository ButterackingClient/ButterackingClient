/*    */ package net.minecraft.item;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class ItemAxe
/*    */   extends ItemTool
/*    */ {
/* 12 */   private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet((Object[])new Block[] { Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, (Block)Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder });
/*    */   
/*    */   protected ItemAxe(Item.ToolMaterial material) {
/* 15 */     super(3.0F, material, EFFECTIVE_ON);
/*    */   }
/*    */   
/*    */   public float getStrVsBlock(ItemStack stack, Block state) {
/* 19 */     return (state.getMaterial() != Material.wood && state.getMaterial() != Material.plants && state.getMaterial() != Material.vine) ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\item\ItemAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */