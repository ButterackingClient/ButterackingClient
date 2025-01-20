/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockTallGrass;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenTallGrass extends WorldGenerator {
/*    */   private final IBlockState tallGrassState;
/*    */   
/*    */   public WorldGenTallGrass(BlockTallGrass.EnumType p_i45629_1_) {
/* 17 */     this.tallGrassState = Blocks.tallgrass.getDefaultState().withProperty((IProperty)BlockTallGrass.TYPE, (Comparable)p_i45629_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random rand, BlockPos position) {
/*    */     Block block;
/* 23 */     while (((block = worldIn.getBlockState(position).getBlock()).getMaterial() == Material.air || block.getMaterial() == Material.leaves) && position.getY() > 0) {
/* 24 */       position = position.down();
/*    */     }
/*    */     
/* 27 */     for (int i = 0; i < 128; i++) {
/* 28 */       BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
/*    */       
/* 30 */       if (worldIn.isAirBlock(blockpos) && Blocks.tallgrass.canBlockStay(worldIn, blockpos, this.tallGrassState)) {
/* 31 */         worldIn.setBlockState(blockpos, this.tallGrassState, 2);
/*    */       }
/*    */     } 
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\world\gen\feature\WorldGenTallGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */