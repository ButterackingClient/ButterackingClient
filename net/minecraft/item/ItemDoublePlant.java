/*    */ package net.minecraft.item;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockDoublePlant;
/*    */ import net.minecraft.world.ColorizerGrass;
/*    */ 
/*    */ public class ItemDoublePlant extends ItemMultiTexture {
/*    */   public ItemDoublePlant(Block block, Block block2, Function<ItemStack, String> nameFunction) {
/* 10 */     super(block, block2, nameFunction);
/*    */   }
/*    */   
/*    */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 14 */     BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = BlockDoublePlant.EnumPlantType.byMetadata(stack.getMetadata());
/* 15 */     return (blockdoubleplant$enumplanttype != BlockDoublePlant.EnumPlantType.GRASS && blockdoubleplant$enumplanttype != BlockDoublePlant.EnumPlantType.FERN) ? super.getColorFromItemStack(stack, renderPass) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\item\ItemDoublePlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */