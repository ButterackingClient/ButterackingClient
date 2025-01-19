/*     */ package net.minecraft.world.gen.structure;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlowerPot;
/*     */ import net.minecraft.block.BlockLever;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.BlockSandStone;
/*     */ import net.minecraft.block.BlockStoneBrick;
/*     */ import net.minecraft.block.BlockStoneSlab;
/*     */ import net.minecraft.block.BlockTripWire;
/*     */ import net.minecraft.block.BlockTripWireHook;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ComponentScatteredFeaturePieces {
/*     */   public static void registerScatteredFeaturePieces() {
/*  29 */     MapGenStructureIO.registerStructureComponent((Class)DesertPyramid.class, "TeDP");
/*  30 */     MapGenStructureIO.registerStructureComponent((Class)JunglePyramid.class, "TeJP");
/*  31 */     MapGenStructureIO.registerStructureComponent((Class)SwampHut.class, "TeSH");
/*     */   }
/*     */   
/*     */   public static class DesertPyramid extends Feature {
/*  35 */     private boolean[] hasPlacedChest = new boolean[4];
/*  36 */     private static final List<WeightedRandomChestContent> itemsToGenerateInTemple = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15), new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2), new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20), new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
/*     */ 
/*     */     
/*     */     public DesertPyramid() {}
/*     */     
/*     */     public DesertPyramid(Random p_i2062_1_, int p_i2062_2_, int p_i2062_3_) {
/*  42 */       super(p_i2062_1_, p_i2062_2_, 64, p_i2062_3_, 21, 15, 21);
/*     */     }
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound tagCompound) {
/*  46 */       super.writeStructureToNBT(tagCompound);
/*  47 */       tagCompound.setBoolean("hasPlacedChest0", this.hasPlacedChest[0]);
/*  48 */       tagCompound.setBoolean("hasPlacedChest1", this.hasPlacedChest[1]);
/*  49 */       tagCompound.setBoolean("hasPlacedChest2", this.hasPlacedChest[2]);
/*  50 */       tagCompound.setBoolean("hasPlacedChest3", this.hasPlacedChest[3]);
/*     */     }
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound tagCompound) {
/*  54 */       super.readStructureFromNBT(tagCompound);
/*  55 */       this.hasPlacedChest[0] = tagCompound.getBoolean("hasPlacedChest0");
/*  56 */       this.hasPlacedChest[1] = tagCompound.getBoolean("hasPlacedChest1");
/*  57 */       this.hasPlacedChest[2] = tagCompound.getBoolean("hasPlacedChest2");
/*  58 */       this.hasPlacedChest[3] = tagCompound.getBoolean("hasPlacedChest3");
/*     */     }
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
/*  62 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/*     */       
/*  64 */       for (int i = 1; i <= 9; i++) {
/*  65 */         fillWithBlocks(worldIn, structureBoundingBoxIn, i, i, i, this.scatteredFeatureSizeX - 1 - i, i, this.scatteredFeatureSizeZ - 1 - i, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/*  66 */         fillWithBlocks(worldIn, structureBoundingBoxIn, i + 1, i, i + 1, this.scatteredFeatureSizeX - 2 - i, i, this.scatteredFeatureSizeZ - 2 - i, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       } 
/*     */       
/*  69 */       for (int j2 = 0; j2 < this.scatteredFeatureSizeX; j2++) {
/*  70 */         for (int j = 0; j < this.scatteredFeatureSizeZ; j++) {
/*  71 */           int k = -5;
/*  72 */           replaceAirAndLiquidDownwards(worldIn, Blocks.sandstone.getDefaultState(), j2, k, j, structureBoundingBoxIn);
/*     */         } 
/*     */       } 
/*     */       
/*  76 */       int k2 = getMetadataWithOffset(Blocks.sandstone_stairs, 3);
/*  77 */       int l2 = getMetadataWithOffset(Blocks.sandstone_stairs, 2);
/*  78 */       int i3 = getMetadataWithOffset(Blocks.sandstone_stairs, 0);
/*  79 */       int l = getMetadataWithOffset(Blocks.sandstone_stairs, 1);
/*  80 */       int i1 = (EnumDyeColor.ORANGE.getDyeDamage() ^ 0xFFFFFFFF) & 0xF;
/*  81 */       int j1 = (EnumDyeColor.BLUE.getDyeDamage() ^ 0xFFFFFFFF) & 0xF;
/*  82 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 9, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  83 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 10, 1, 3, 10, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/*  84 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(k2), 2, 10, 0, structureBoundingBoxIn);
/*  85 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(l2), 2, 10, 4, structureBoundingBoxIn);
/*  86 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(i3), 0, 10, 2, structureBoundingBoxIn);
/*  87 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(l), 4, 10, 2, structureBoundingBoxIn);
/*  88 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 5, 0, 0, this.scatteredFeatureSizeX - 1, 9, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  89 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 4, 10, 1, this.scatteredFeatureSizeX - 2, 10, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/*  90 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(k2), this.scatteredFeatureSizeX - 3, 10, 0, structureBoundingBoxIn);
/*  91 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(l2), this.scatteredFeatureSizeX - 3, 10, 4, structureBoundingBoxIn);
/*  92 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(i3), this.scatteredFeatureSizeX - 5, 10, 2, structureBoundingBoxIn);
/*  93 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(l), this.scatteredFeatureSizeX - 1, 10, 2, structureBoundingBoxIn);
/*  94 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 12, 4, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  95 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 0, 11, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  96 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 9, 1, 1, structureBoundingBoxIn);
/*  97 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 9, 2, 1, structureBoundingBoxIn);
/*  98 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 9, 3, 1, structureBoundingBoxIn);
/*  99 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 10, 3, 1, structureBoundingBoxIn);
/* 100 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 11, 3, 1, structureBoundingBoxIn);
/* 101 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 11, 2, 1, structureBoundingBoxIn);
/* 102 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 11, 1, 1, structureBoundingBoxIn);
/* 103 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 8, 3, 3, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 104 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 2, 8, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 105 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 1, 16, 3, 3, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 106 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 2, 16, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 107 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 5, this.scatteredFeatureSizeX - 6, 4, this.scatteredFeatureSizeZ - 6, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 108 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 4, 9, 11, 4, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 109 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 8, 8, 3, 8, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 110 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 8, 12, 3, 8, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 111 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 12, 8, 3, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 112 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, 12, 12, 3, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 113 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 4, 4, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 114 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 5, 1, 5, this.scatteredFeatureSizeX - 2, 4, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 115 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 7, 9, 6, 7, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 116 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 7, 7, 9, this.scatteredFeatureSizeX - 7, 7, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 117 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 9, 5, 7, 11, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 118 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 6, 5, 9, this.scatteredFeatureSizeX - 6, 7, 11, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 119 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 5, 5, 10, structureBoundingBoxIn);
/* 120 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 5, 6, 10, structureBoundingBoxIn);
/* 121 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 6, 6, 10, structureBoundingBoxIn);
/* 122 */       setBlockState(worldIn, Blocks.air.getDefaultState(), this.scatteredFeatureSizeX - 6, 5, 10, structureBoundingBoxIn);
/* 123 */       setBlockState(worldIn, Blocks.air.getDefaultState(), this.scatteredFeatureSizeX - 6, 6, 10, structureBoundingBoxIn);
/* 124 */       setBlockState(worldIn, Blocks.air.getDefaultState(), this.scatteredFeatureSizeX - 7, 6, 10, structureBoundingBoxIn);
/* 125 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 4, 4, 2, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 126 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 3, 4, 4, this.scatteredFeatureSizeX - 3, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 127 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(k2), 2, 4, 5, structureBoundingBoxIn);
/* 128 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(k2), 2, 3, 4, structureBoundingBoxIn);
/* 129 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(k2), this.scatteredFeatureSizeX - 3, 4, 5, structureBoundingBoxIn);
/* 130 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(k2), this.scatteredFeatureSizeX - 3, 3, 4, structureBoundingBoxIn);
/* 131 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 3, 2, 2, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 132 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 3, 1, 3, this.scatteredFeatureSizeX - 2, 2, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 133 */       setBlockState(worldIn, Blocks.sandstone_stairs.getDefaultState(), 1, 1, 2, structureBoundingBoxIn);
/* 134 */       setBlockState(worldIn, Blocks.sandstone_stairs.getDefaultState(), this.scatteredFeatureSizeX - 2, 1, 2, structureBoundingBoxIn);
/* 135 */       setBlockState(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.getMetadata()), 1, 2, 2, structureBoundingBoxIn);
/* 136 */       setBlockState(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.getMetadata()), this.scatteredFeatureSizeX - 2, 2, 2, structureBoundingBoxIn);
/* 137 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(l), 2, 1, 2, structureBoundingBoxIn);
/* 138 */       setBlockState(worldIn, Blocks.sandstone_stairs.getStateFromMeta(i3), this.scatteredFeatureSizeX - 3, 1, 2, structureBoundingBoxIn);
/* 139 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 5, 4, 3, 18, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 140 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 5, 3, 5, this.scatteredFeatureSizeX - 5, 3, 17, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 141 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 5, 4, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 142 */       fillWithBlocks(worldIn, structureBoundingBoxIn, this.scatteredFeatureSizeX - 6, 1, 5, this.scatteredFeatureSizeX - 5, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       
/* 144 */       for (int k1 = 5; k1 <= 17; k1 += 2) {
/* 145 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 4, 1, k1, structureBoundingBoxIn);
/* 146 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 4, 2, k1, structureBoundingBoxIn);
/* 147 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), this.scatteredFeatureSizeX - 5, 1, k1, structureBoundingBoxIn);
/* 148 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), this.scatteredFeatureSizeX - 5, 2, k1, structureBoundingBoxIn);
/*     */       } 
/*     */       
/* 151 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 10, 0, 7, structureBoundingBoxIn);
/* 152 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 10, 0, 8, structureBoundingBoxIn);
/* 153 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 9, 0, 9, structureBoundingBoxIn);
/* 154 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 11, 0, 9, structureBoundingBoxIn);
/* 155 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 8, 0, 10, structureBoundingBoxIn);
/* 156 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 12, 0, 10, structureBoundingBoxIn);
/* 157 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 7, 0, 10, structureBoundingBoxIn);
/* 158 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 13, 0, 10, structureBoundingBoxIn);
/* 159 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 9, 0, 11, structureBoundingBoxIn);
/* 160 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 11, 0, 11, structureBoundingBoxIn);
/* 161 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 10, 0, 12, structureBoundingBoxIn);
/* 162 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 10, 0, 13, structureBoundingBoxIn);
/* 163 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(j1), 10, 0, 10, structureBoundingBoxIn);
/*     */       
/* 165 */       for (int j3 = 0; j3 <= this.scatteredFeatureSizeX - 1; j3 += this.scatteredFeatureSizeX - 1) {
/* 166 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 2, 1, structureBoundingBoxIn);
/* 167 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 2, 2, structureBoundingBoxIn);
/* 168 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 2, 3, structureBoundingBoxIn);
/* 169 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 3, 1, structureBoundingBoxIn);
/* 170 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 3, 2, structureBoundingBoxIn);
/* 171 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 3, 3, structureBoundingBoxIn);
/* 172 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 4, 1, structureBoundingBoxIn);
/* 173 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), j3, 4, 2, structureBoundingBoxIn);
/* 174 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 4, 3, structureBoundingBoxIn);
/* 175 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 5, 1, structureBoundingBoxIn);
/* 176 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 5, 2, structureBoundingBoxIn);
/* 177 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 5, 3, structureBoundingBoxIn);
/* 178 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 6, 1, structureBoundingBoxIn);
/* 179 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), j3, 6, 2, structureBoundingBoxIn);
/* 180 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 6, 3, structureBoundingBoxIn);
/* 181 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 7, 1, structureBoundingBoxIn);
/* 182 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 7, 2, structureBoundingBoxIn);
/* 183 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), j3, 7, 3, structureBoundingBoxIn);
/* 184 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 8, 1, structureBoundingBoxIn);
/* 185 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 8, 2, structureBoundingBoxIn);
/* 186 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), j3, 8, 3, structureBoundingBoxIn);
/*     */       } 
/*     */       
/* 189 */       for (int k3 = 2; k3 <= this.scatteredFeatureSizeX - 3; k3 += this.scatteredFeatureSizeX - 3 - 2) {
/* 190 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 - 1, 2, 0, structureBoundingBoxIn);
/* 191 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3, 2, 0, structureBoundingBoxIn);
/* 192 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 + 1, 2, 0, structureBoundingBoxIn);
/* 193 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 - 1, 3, 0, structureBoundingBoxIn);
/* 194 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3, 3, 0, structureBoundingBoxIn);
/* 195 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 + 1, 3, 0, structureBoundingBoxIn);
/* 196 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3 - 1, 4, 0, structureBoundingBoxIn);
/* 197 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), k3, 4, 0, structureBoundingBoxIn);
/* 198 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3 + 1, 4, 0, structureBoundingBoxIn);
/* 199 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 - 1, 5, 0, structureBoundingBoxIn);
/* 200 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3, 5, 0, structureBoundingBoxIn);
/* 201 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 + 1, 5, 0, structureBoundingBoxIn);
/* 202 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3 - 1, 6, 0, structureBoundingBoxIn);
/* 203 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), k3, 6, 0, structureBoundingBoxIn);
/* 204 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3 + 1, 6, 0, structureBoundingBoxIn);
/* 205 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3 - 1, 7, 0, structureBoundingBoxIn);
/* 206 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3, 7, 0, structureBoundingBoxIn);
/* 207 */         setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), k3 + 1, 7, 0, structureBoundingBoxIn);
/* 208 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 - 1, 8, 0, structureBoundingBoxIn);
/* 209 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3, 8, 0, structureBoundingBoxIn);
/* 210 */         setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), k3 + 1, 8, 0, structureBoundingBoxIn);
/*     */       } 
/*     */       
/* 213 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 4, 0, 12, 6, 0, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 214 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 8, 6, 0, structureBoundingBoxIn);
/* 215 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 12, 6, 0, structureBoundingBoxIn);
/* 216 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 9, 5, 0, structureBoundingBoxIn);
/* 217 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 10, 5, 0, structureBoundingBoxIn);
/* 218 */       setBlockState(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(i1), 11, 5, 0, structureBoundingBoxIn);
/* 219 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -14, 8, 12, -11, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 220 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -10, 8, 12, -10, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), false);
/* 221 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -9, 8, 12, -9, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), false);
/* 222 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 8, -8, 8, 12, -1, 12, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 223 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 9, -11, 9, 11, -1, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 224 */       setBlockState(worldIn, Blocks.stone_pressure_plate.getDefaultState(), 10, -11, 10, structureBoundingBoxIn);
/* 225 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 9, -13, 9, 11, -13, 11, Blocks.tnt.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 226 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 8, -11, 10, structureBoundingBoxIn);
/* 227 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 8, -10, 10, structureBoundingBoxIn);
/* 228 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 7, -10, 10, structureBoundingBoxIn);
/* 229 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 7, -11, 10, structureBoundingBoxIn);
/* 230 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 12, -11, 10, structureBoundingBoxIn);
/* 231 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 12, -10, 10, structureBoundingBoxIn);
/* 232 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 13, -10, 10, structureBoundingBoxIn);
/* 233 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 13, -11, 10, structureBoundingBoxIn);
/* 234 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 10, -11, 8, structureBoundingBoxIn);
/* 235 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 10, -10, 8, structureBoundingBoxIn);
/* 236 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 10, -10, 7, structureBoundingBoxIn);
/* 237 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 10, -11, 7, structureBoundingBoxIn);
/* 238 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 10, -11, 12, structureBoundingBoxIn);
/* 239 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 10, -10, 12, structureBoundingBoxIn);
/* 240 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 10, -10, 13, structureBoundingBoxIn);
/* 241 */       setBlockState(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 10, -11, 13, structureBoundingBoxIn);
/*     */       
/* 243 */       for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
/* 244 */         if (!this.hasPlacedChest[enumfacing.getHorizontalIndex()]) {
/* 245 */           int l1 = enumfacing.getFrontOffsetX() * 2;
/* 246 */           int i2 = enumfacing.getFrontOffsetZ() * 2;
/* 247 */           this.hasPlacedChest[enumfacing.getHorizontalIndex()] = generateChestContents(worldIn, structureBoundingBoxIn, randomIn, 10 + l1, -11, 10 + i2, WeightedRandomChestContent.func_177629_a(itemsToGenerateInTemple, new WeightedRandomChestContent[] { Items.enchanted_book.getRandom(randomIn) }), 2 + randomIn.nextInt(5));
/*     */         } 
/*     */       } 
/*     */       
/* 251 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class Feature extends StructureComponent {
/*     */     protected int scatteredFeatureSizeX;
/*     */     protected int scatteredFeatureSizeY;
/*     */     protected int scatteredFeatureSizeZ;
/* 259 */     protected int field_74936_d = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Feature(Random p_i2065_1_, int p_i2065_2_, int p_i2065_3_, int p_i2065_4_, int p_i2065_5_, int p_i2065_6_, int p_i2065_7_) {
/* 265 */       super(0);
/* 266 */       this.scatteredFeatureSizeX = p_i2065_5_;
/* 267 */       this.scatteredFeatureSizeY = p_i2065_6_;
/* 268 */       this.scatteredFeatureSizeZ = p_i2065_7_;
/* 269 */       this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2065_1_);
/*     */       
/* 271 */       switch (this.coordBaseMode) {
/*     */         case NORTH:
/*     */         case SOUTH:
/* 274 */           this.boundingBox = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_5_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_7_ - 1);
/*     */           return;
/*     */       } 
/*     */       
/* 278 */       this.boundingBox = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_7_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_5_ - 1);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound tagCompound) {
/* 283 */       tagCompound.setInteger("Width", this.scatteredFeatureSizeX);
/* 284 */       tagCompound.setInteger("Height", this.scatteredFeatureSizeY);
/* 285 */       tagCompound.setInteger("Depth", this.scatteredFeatureSizeZ);
/* 286 */       tagCompound.setInteger("HPos", this.field_74936_d);
/*     */     }
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound tagCompound) {
/* 290 */       this.scatteredFeatureSizeX = tagCompound.getInteger("Width");
/* 291 */       this.scatteredFeatureSizeY = tagCompound.getInteger("Height");
/* 292 */       this.scatteredFeatureSizeZ = tagCompound.getInteger("Depth");
/* 293 */       this.field_74936_d = tagCompound.getInteger("HPos");
/*     */     }
/*     */     
/*     */     protected boolean func_74935_a(World worldIn, StructureBoundingBox p_74935_2_, int p_74935_3_) {
/* 297 */       if (this.field_74936_d >= 0) {
/* 298 */         return true;
/*     */       }
/* 300 */       int i = 0;
/* 301 */       int j = 0;
/* 302 */       BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
/*     */       
/* 304 */       for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; k++) {
/* 305 */         for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; l++) {
/* 306 */           blockpos$mutableblockpos.set(l, 64, k);
/*     */           
/* 308 */           if (p_74935_2_.isVecInside((Vec3i)blockpos$mutableblockpos)) {
/* 309 */             i += Math.max(worldIn.getTopSolidOrLiquidBlock((BlockPos)blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel());
/* 310 */             j++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 315 */       if (j == 0) {
/* 316 */         return false;
/*     */       }
/* 318 */       this.field_74936_d = i / j;
/* 319 */       this.boundingBox.offset(0, this.field_74936_d - this.boundingBox.minY + p_74935_3_, 0);
/* 320 */       return true;
/*     */     }
/*     */     
/*     */     public Feature() {}
/*     */   }
/*     */   
/*     */   public static class JunglePyramid extends Feature {
/*     */     private boolean placedMainChest;
/*     */     private boolean placedHiddenChest;
/*     */     private boolean placedTrap1;
/*     */     private boolean placedTrap2;
/* 331 */     private static final List<WeightedRandomChestContent> field_175816_i = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15), new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2), new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20), new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
/* 332 */     private static final List<WeightedRandomChestContent> field_175815_j = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.arrow, 0, 2, 7, 30) });
/* 333 */     private static Stones junglePyramidsRandomScatteredStones = new Stones(null);
/*     */ 
/*     */     
/*     */     public JunglePyramid() {}
/*     */     
/*     */     public JunglePyramid(Random p_i2064_1_, int p_i2064_2_, int p_i2064_3_) {
/* 339 */       super(p_i2064_1_, p_i2064_2_, 64, p_i2064_3_, 12, 10, 15);
/*     */     }
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound tagCompound) {
/* 343 */       super.writeStructureToNBT(tagCompound);
/* 344 */       tagCompound.setBoolean("placedMainChest", this.placedMainChest);
/* 345 */       tagCompound.setBoolean("placedHiddenChest", this.placedHiddenChest);
/* 346 */       tagCompound.setBoolean("placedTrap1", this.placedTrap1);
/* 347 */       tagCompound.setBoolean("placedTrap2", this.placedTrap2);
/*     */     }
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound tagCompound) {
/* 351 */       super.readStructureFromNBT(tagCompound);
/* 352 */       this.placedMainChest = tagCompound.getBoolean("placedMainChest");
/* 353 */       this.placedHiddenChest = tagCompound.getBoolean("placedHiddenChest");
/* 354 */       this.placedTrap1 = tagCompound.getBoolean("placedTrap1");
/* 355 */       this.placedTrap2 = tagCompound.getBoolean("placedTrap2");
/*     */     }
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
/* 359 */       if (!func_74935_a(worldIn, structureBoundingBoxIn, 0)) {
/* 360 */         return false;
/*     */       }
/* 362 */       int i = getMetadataWithOffset(Blocks.stone_stairs, 3);
/* 363 */       int j = getMetadataWithOffset(Blocks.stone_stairs, 2);
/* 364 */       int k = getMetadataWithOffset(Blocks.stone_stairs, 0);
/* 365 */       int l = getMetadataWithOffset(Blocks.stone_stairs, 1);
/* 366 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 367 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 1, 2, 9, 2, 2, false, randomIn, junglePyramidsRandomScatteredStones);
/* 368 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 1, 12, 9, 2, 12, false, randomIn, junglePyramidsRandomScatteredStones);
/* 369 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 1, 3, 2, 2, 11, false, randomIn, junglePyramidsRandomScatteredStones);
/* 370 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 1, 3, 9, 2, 11, false, randomIn, junglePyramidsRandomScatteredStones);
/* 371 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 10, 6, 1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 372 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 3, 13, 10, 6, 13, false, randomIn, junglePyramidsRandomScatteredStones);
/* 373 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 3, 2, 1, 6, 12, false, randomIn, junglePyramidsRandomScatteredStones);
/* 374 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 10, 3, 2, 10, 6, 12, false, randomIn, junglePyramidsRandomScatteredStones);
/* 375 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 3, 2, 9, 3, 12, false, randomIn, junglePyramidsRandomScatteredStones);
/* 376 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 6, 2, 9, 6, 12, false, randomIn, junglePyramidsRandomScatteredStones);
/* 377 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 3, 7, 3, 8, 7, 11, false, randomIn, junglePyramidsRandomScatteredStones);
/* 378 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 8, 4, 7, 8, 10, false, randomIn, junglePyramidsRandomScatteredStones);
/* 379 */       fillWithAir(worldIn, structureBoundingBoxIn, 3, 1, 3, 8, 2, 11);
/* 380 */       fillWithAir(worldIn, structureBoundingBoxIn, 4, 3, 6, 7, 3, 9);
/* 381 */       fillWithAir(worldIn, structureBoundingBoxIn, 2, 4, 2, 9, 5, 12);
/* 382 */       fillWithAir(worldIn, structureBoundingBoxIn, 4, 6, 5, 7, 6, 9);
/* 383 */       fillWithAir(worldIn, structureBoundingBoxIn, 5, 7, 6, 6, 7, 8);
/* 384 */       fillWithAir(worldIn, structureBoundingBoxIn, 5, 1, 2, 6, 2, 2);
/* 385 */       fillWithAir(worldIn, structureBoundingBoxIn, 5, 2, 12, 6, 2, 12);
/* 386 */       fillWithAir(worldIn, structureBoundingBoxIn, 5, 5, 1, 6, 5, 1);
/* 387 */       fillWithAir(worldIn, structureBoundingBoxIn, 5, 5, 13, 6, 5, 13);
/* 388 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 5, 5, structureBoundingBoxIn);
/* 389 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 10, 5, 5, structureBoundingBoxIn);
/* 390 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 5, 9, structureBoundingBoxIn);
/* 391 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 10, 5, 9, structureBoundingBoxIn);
/*     */       
/* 393 */       for (int i1 = 0; i1 <= 14; i1 += 14) {
/* 394 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 4, i1, 2, 5, i1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 395 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 4, i1, 4, 5, i1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 396 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 4, i1, 7, 5, i1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 397 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 4, i1, 9, 5, i1, false, randomIn, junglePyramidsRandomScatteredStones);
/*     */       } 
/*     */       
/* 400 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 5, 6, 0, 6, 6, 0, false, randomIn, junglePyramidsRandomScatteredStones);
/*     */       
/* 402 */       for (int k1 = 0; k1 <= 11; k1 += 11) {
/* 403 */         for (int j1 = 2; j1 <= 12; j1 += 2) {
/* 404 */           fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, k1, 4, j1, k1, 5, j1, false, randomIn, junglePyramidsRandomScatteredStones);
/*     */         }
/*     */         
/* 407 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, k1, 6, 5, k1, 6, 5, false, randomIn, junglePyramidsRandomScatteredStones);
/* 408 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, k1, 6, 9, k1, 6, 9, false, randomIn, junglePyramidsRandomScatteredStones);
/*     */       } 
/*     */       
/* 411 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 7, 2, 2, 9, 2, false, randomIn, junglePyramidsRandomScatteredStones);
/* 412 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 7, 2, 9, 9, 2, false, randomIn, junglePyramidsRandomScatteredStones);
/* 413 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, 7, 12, 2, 9, 12, false, randomIn, junglePyramidsRandomScatteredStones);
/* 414 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, 7, 12, 9, 9, 12, false, randomIn, junglePyramidsRandomScatteredStones);
/* 415 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 9, 4, 4, 9, 4, false, randomIn, junglePyramidsRandomScatteredStones);
/* 416 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 9, 4, 7, 9, 4, false, randomIn, junglePyramidsRandomScatteredStones);
/* 417 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 9, 10, 4, 9, 10, false, randomIn, junglePyramidsRandomScatteredStones);
/* 418 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 9, 10, 7, 9, 10, false, randomIn, junglePyramidsRandomScatteredStones);
/* 419 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 5, 9, 7, 6, 9, 7, false, randomIn, junglePyramidsRandomScatteredStones);
/* 420 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 5, 9, 6, structureBoundingBoxIn);
/* 421 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 6, 9, 6, structureBoundingBoxIn);
/* 422 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(j), 5, 9, 8, structureBoundingBoxIn);
/* 423 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(j), 6, 9, 8, structureBoundingBoxIn);
/* 424 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 4, 0, 0, structureBoundingBoxIn);
/* 425 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 5, 0, 0, structureBoundingBoxIn);
/* 426 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 6, 0, 0, structureBoundingBoxIn);
/* 427 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 7, 0, 0, structureBoundingBoxIn);
/* 428 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 4, 1, 8, structureBoundingBoxIn);
/* 429 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 4, 2, 9, structureBoundingBoxIn);
/* 430 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 4, 3, 10, structureBoundingBoxIn);
/* 431 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 7, 1, 8, structureBoundingBoxIn);
/* 432 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 7, 2, 9, structureBoundingBoxIn);
/* 433 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 7, 3, 10, structureBoundingBoxIn);
/* 434 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 9, 4, 1, 9, false, randomIn, junglePyramidsRandomScatteredStones);
/* 435 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, 1, 9, 7, 1, 9, false, randomIn, junglePyramidsRandomScatteredStones);
/* 436 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 10, 7, 2, 10, false, randomIn, junglePyramidsRandomScatteredStones);
/* 437 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 5, 4, 5, 6, 4, 5, false, randomIn, junglePyramidsRandomScatteredStones);
/* 438 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(k), 4, 4, 5, structureBoundingBoxIn);
/* 439 */       setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(l), 7, 4, 5, structureBoundingBoxIn);
/*     */       
/* 441 */       for (int l1 = 0; l1 < 4; l1++) {
/* 442 */         setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(j), 5, 0 - l1, 6 + l1, structureBoundingBoxIn);
/* 443 */         setBlockState(worldIn, Blocks.stone_stairs.getStateFromMeta(j), 6, 0 - l1, 6 + l1, structureBoundingBoxIn);
/* 444 */         fillWithAir(worldIn, structureBoundingBoxIn, 5, 0 - l1, 7 + l1, 6, 0 - l1, 9 + l1);
/*     */       } 
/*     */       
/* 447 */       fillWithAir(worldIn, structureBoundingBoxIn, 1, -3, 12, 10, -1, 13);
/* 448 */       fillWithAir(worldIn, structureBoundingBoxIn, 1, -3, 1, 3, -1, 13);
/* 449 */       fillWithAir(worldIn, structureBoundingBoxIn, 1, -3, 1, 9, -1, 5);
/*     */       
/* 451 */       for (int i2 = 1; i2 <= 13; i2 += 2) {
/* 452 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, -3, i2, 1, -2, i2, false, randomIn, junglePyramidsRandomScatteredStones);
/*     */       }
/*     */       
/* 455 */       for (int j2 = 2; j2 <= 12; j2 += 2) {
/* 456 */         fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, -1, j2, 3, -1, j2, false, randomIn, junglePyramidsRandomScatteredStones);
/*     */       }
/*     */       
/* 459 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, -2, 1, 5, -2, 1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 460 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 7, -2, 1, 9, -2, 1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 461 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 6, -3, 1, 6, -3, 1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 462 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 6, -1, 1, 6, -1, 1, false, randomIn, junglePyramidsRandomScatteredStones);
/* 463 */       setBlockState(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.EAST.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, structureBoundingBoxIn);
/* 464 */       setBlockState(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.WEST.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, structureBoundingBoxIn);
/* 465 */       setBlockState(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, structureBoundingBoxIn);
/* 466 */       setBlockState(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, structureBoundingBoxIn);
/* 467 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 7, structureBoundingBoxIn);
/* 468 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 6, structureBoundingBoxIn);
/* 469 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 5, structureBoundingBoxIn);
/* 470 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 4, structureBoundingBoxIn);
/* 471 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 3, structureBoundingBoxIn);
/* 472 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 2, structureBoundingBoxIn);
/* 473 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 1, structureBoundingBoxIn);
/* 474 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 4, -3, 1, structureBoundingBoxIn);
/* 475 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, -3, 1, structureBoundingBoxIn);
/*     */       
/* 477 */       if (!this.placedTrap1) {
/* 478 */         this.placedTrap1 = generateDispenserContents(worldIn, structureBoundingBoxIn, randomIn, 3, -2, 1, EnumFacing.NORTH.getIndex(), field_175815_j, 2);
/*     */       }
/*     */       
/* 481 */       setBlockState(worldIn, Blocks.vine.getStateFromMeta(15), 3, -2, 2, structureBoundingBoxIn);
/* 482 */       setBlockState(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.NORTH.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, structureBoundingBoxIn);
/* 483 */       setBlockState(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.SOUTH.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, structureBoundingBoxIn);
/* 484 */       setBlockState(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, structureBoundingBoxIn);
/* 485 */       setBlockState(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, structureBoundingBoxIn);
/* 486 */       setBlockState(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, structureBoundingBoxIn);
/* 487 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 8, -3, 6, structureBoundingBoxIn);
/* 488 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 9, -3, 6, structureBoundingBoxIn);
/* 489 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 9, -3, 5, structureBoundingBoxIn);
/* 490 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 4, structureBoundingBoxIn);
/* 491 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 9, -2, 4, structureBoundingBoxIn);
/*     */       
/* 493 */       if (!this.placedTrap2) {
/* 494 */         this.placedTrap2 = generateDispenserContents(worldIn, structureBoundingBoxIn, randomIn, 9, -2, 3, EnumFacing.WEST.getIndex(), field_175815_j, 2);
/*     */       }
/*     */       
/* 497 */       setBlockState(worldIn, Blocks.vine.getStateFromMeta(15), 8, -1, 3, structureBoundingBoxIn);
/* 498 */       setBlockState(worldIn, Blocks.vine.getStateFromMeta(15), 8, -2, 3, structureBoundingBoxIn);
/*     */       
/* 500 */       if (!this.placedMainChest) {
/* 501 */         this.placedMainChest = generateChestContents(worldIn, structureBoundingBoxIn, randomIn, 8, -3, 3, WeightedRandomChestContent.func_177629_a(field_175816_i, new WeightedRandomChestContent[] { Items.enchanted_book.getRandom(randomIn) }), 2 + randomIn.nextInt(5));
/*     */       }
/*     */       
/* 504 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 2, structureBoundingBoxIn);
/* 505 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 1, structureBoundingBoxIn);
/* 506 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 4, -3, 5, structureBoundingBoxIn);
/* 507 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 5, -2, 5, structureBoundingBoxIn);
/* 508 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 5, -1, 5, structureBoundingBoxIn);
/* 509 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, -3, 5, structureBoundingBoxIn);
/* 510 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 7, -2, 5, structureBoundingBoxIn);
/* 511 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 7, -1, 5, structureBoundingBoxIn);
/* 512 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 5, structureBoundingBoxIn);
/* 513 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, -1, 1, 9, -1, 5, false, randomIn, junglePyramidsRandomScatteredStones);
/* 514 */       fillWithAir(worldIn, structureBoundingBoxIn, 8, -3, 8, 10, -1, 10);
/* 515 */       setBlockState(worldIn, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 8, -2, 11, structureBoundingBoxIn);
/* 516 */       setBlockState(worldIn, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 9, -2, 11, structureBoundingBoxIn);
/* 517 */       setBlockState(worldIn, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 10, -2, 11, structureBoundingBoxIn);
/* 518 */       setBlockState(worldIn, Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 8, -2, 12, structureBoundingBoxIn);
/* 519 */       setBlockState(worldIn, Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 9, -2, 12, structureBoundingBoxIn);
/* 520 */       setBlockState(worldIn, Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 10, -2, 12, structureBoundingBoxIn);
/* 521 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 8, -3, 8, 8, -3, 10, false, randomIn, junglePyramidsRandomScatteredStones);
/* 522 */       fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 10, -3, 8, 10, -3, 10, false, randomIn, junglePyramidsRandomScatteredStones);
/* 523 */       setBlockState(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 10, -2, 9, structureBoundingBoxIn);
/* 524 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 8, -2, 9, structureBoundingBoxIn);
/* 525 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 8, -2, 10, structureBoundingBoxIn);
/* 526 */       setBlockState(worldIn, Blocks.redstone_wire.getDefaultState(), 10, -1, 9, structureBoundingBoxIn);
/* 527 */       setBlockState(worldIn, Blocks.sticky_piston.getStateFromMeta(EnumFacing.UP.getIndex()), 9, -2, 8, structureBoundingBoxIn);
/* 528 */       setBlockState(worldIn, Blocks.sticky_piston.getStateFromMeta(getMetadataWithOffset((Block)Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -2, 8, structureBoundingBoxIn);
/* 529 */       setBlockState(worldIn, Blocks.sticky_piston.getStateFromMeta(getMetadataWithOffset((Block)Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -1, 8, structureBoundingBoxIn);
/* 530 */       setBlockState(worldIn, Blocks.unpowered_repeater.getStateFromMeta(getMetadataWithOffset((Block)Blocks.unpowered_repeater, EnumFacing.NORTH.getHorizontalIndex())), 10, -2, 10, structureBoundingBoxIn);
/*     */       
/* 532 */       if (!this.placedHiddenChest) {
/* 533 */         this.placedHiddenChest = generateChestContents(worldIn, structureBoundingBoxIn, randomIn, 9, -3, 10, WeightedRandomChestContent.func_177629_a(field_175816_i, new WeightedRandomChestContent[] { Items.enchanted_book.getRandom(randomIn) }), 2 + randomIn.nextInt(5));
/*     */       }
/*     */       
/* 536 */       return true;
/*     */     }
/*     */     
/*     */     static class Stones
/*     */       extends StructureComponent.BlockSelector
/*     */     {
/*     */       private Stones() {}
/*     */       
/*     */       public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_) {
/* 545 */         if (rand.nextFloat() < 0.4F) {
/* 546 */           this.blockstate = Blocks.cobblestone.getDefaultState();
/*     */         } else {
/* 548 */           this.blockstate = Blocks.mossy_cobblestone.getDefaultState();
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SwampHut
/*     */     extends Feature {
/*     */     private boolean hasWitch;
/*     */     
/*     */     public SwampHut() {}
/*     */     
/*     */     public SwampHut(Random p_i2066_1_, int p_i2066_2_, int p_i2066_3_) {
/* 561 */       super(p_i2066_1_, p_i2066_2_, 64, p_i2066_3_, 7, 7, 9);
/*     */     }
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound tagCompound) {
/* 565 */       super.writeStructureToNBT(tagCompound);
/* 566 */       tagCompound.setBoolean("Witch", this.hasWitch);
/*     */     }
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound tagCompound) {
/* 570 */       super.readStructureFromNBT(tagCompound);
/* 571 */       this.hasWitch = tagCompound.getBoolean("Witch");
/*     */     }
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
/* 575 */       if (!func_74935_a(worldIn, structureBoundingBoxIn, 0)) {
/* 576 */         return false;
/*     */       }
/* 578 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 5, 1, 7, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), false);
/* 579 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 2, 5, 4, 7, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), false);
/* 580 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 0, 4, 1, 0, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), false);
/* 581 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 2, 3, 3, 2, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), false);
/* 582 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 3, 1, 3, 6, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), false);
/* 583 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 3, 5, 3, 6, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), false);
/* 584 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 7, 4, 3, 7, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()), false);
/* 585 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 2, 1, 3, 2, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 586 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 2, 5, 3, 2, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 587 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 7, 1, 3, 7, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 588 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 7, 5, 3, 7, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 589 */       setBlockState(worldIn, Blocks.oak_fence.getDefaultState(), 2, 3, 2, structureBoundingBoxIn);
/* 590 */       setBlockState(worldIn, Blocks.oak_fence.getDefaultState(), 3, 3, 7, structureBoundingBoxIn);
/* 591 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 3, 4, structureBoundingBoxIn);
/* 592 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 5, 3, 4, structureBoundingBoxIn);
/* 593 */       setBlockState(worldIn, Blocks.air.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);
/* 594 */       setBlockState(worldIn, Blocks.flower_pot.getDefaultState().withProperty((IProperty)BlockFlowerPot.CONTENTS, (Comparable)BlockFlowerPot.EnumFlowerType.MUSHROOM_RED), 1, 3, 5, structureBoundingBoxIn);
/* 595 */       setBlockState(worldIn, Blocks.crafting_table.getDefaultState(), 3, 2, 6, structureBoundingBoxIn);
/* 596 */       setBlockState(worldIn, Blocks.cauldron.getDefaultState(), 4, 2, 6, structureBoundingBoxIn);
/* 597 */       setBlockState(worldIn, Blocks.oak_fence.getDefaultState(), 1, 2, 1, structureBoundingBoxIn);
/* 598 */       setBlockState(worldIn, Blocks.oak_fence.getDefaultState(), 5, 2, 1, structureBoundingBoxIn);
/* 599 */       int i = getMetadataWithOffset(Blocks.oak_stairs, 3);
/* 600 */       int j = getMetadataWithOffset(Blocks.oak_stairs, 1);
/* 601 */       int k = getMetadataWithOffset(Blocks.oak_stairs, 0);
/* 602 */       int l = getMetadataWithOffset(Blocks.oak_stairs, 2);
/* 603 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 6, 4, 1, Blocks.spruce_stairs.getStateFromMeta(i), Blocks.spruce_stairs.getStateFromMeta(i), false);
/* 604 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 2, 0, 4, 7, Blocks.spruce_stairs.getStateFromMeta(k), Blocks.spruce_stairs.getStateFromMeta(k), false);
/* 605 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 4, 2, 6, 4, 7, Blocks.spruce_stairs.getStateFromMeta(j), Blocks.spruce_stairs.getStateFromMeta(j), false);
/* 606 */       fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 8, 6, 4, 8, Blocks.spruce_stairs.getStateFromMeta(l), Blocks.spruce_stairs.getStateFromMeta(l), false);
/*     */       
/* 608 */       for (int i1 = 2; i1 <= 7; i1 += 5) {
/* 609 */         for (int j1 = 1; j1 <= 5; j1 += 4) {
/* 610 */           replaceAirAndLiquidDownwards(worldIn, Blocks.log.getDefaultState(), j1, -1, i1, structureBoundingBoxIn);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 615 */       int l1 = getXWithOffset(2, 5);
/* 616 */       int i2 = getYWithOffset(2);
/* 617 */       int k1 = getZWithOffset(2, 5);
/*     */       
/* 619 */       if (!this.hasWitch && structureBoundingBoxIn.isVecInside((Vec3i)new BlockPos(l1, i2, k1))) {
/* 620 */         this.hasWitch = true;
/* 621 */         EntityWitch entitywitch = new EntityWitch(worldIn);
/* 622 */         entitywitch.setLocationAndAngles(l1 + 0.5D, i2, k1 + 0.5D, 0.0F, 0.0F);
/* 623 */         entitywitch.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(l1, i2, k1)), null);
/* 624 */         worldIn.spawnEntityInWorld((Entity)entitywitch);
/*     */       } 
/*     */ 
/*     */       
/* 628 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\world\gen\structure\ComponentScatteredFeaturePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */