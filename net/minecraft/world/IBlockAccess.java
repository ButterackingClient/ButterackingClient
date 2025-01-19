package net.minecraft.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBlockAccess {
  TileEntity getTileEntity(BlockPos paramBlockPos);
  
  int getCombinedLight(BlockPos paramBlockPos, int paramInt);
  
  IBlockState getBlockState(BlockPos paramBlockPos);
  
  boolean isAirBlock(BlockPos paramBlockPos);
  
  BiomeGenBase getBiomeGenForCoords(BlockPos paramBlockPos);
  
  boolean extendedLevelsInChunkCache();
  
  int getStrongPower(BlockPos paramBlockPos, EnumFacing paramEnumFacing);
  
  WorldType getWorldType();
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\world\IBlockAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */