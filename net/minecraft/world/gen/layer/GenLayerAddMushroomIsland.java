/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class GenLayerAddMushroomIsland extends GenLayer {
/*    */   public GenLayerAddMushroomIsland(long p_i2120_1_, GenLayer p_i2120_3_) {
/*  7 */     super(p_i2120_1_);
/*  8 */     this.parent = p_i2120_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 16 */     int i = areaX - 1;
/* 17 */     int j = areaY - 1;
/* 18 */     int k = areaWidth + 2;
/* 19 */     int l = areaHeight + 2;
/* 20 */     int[] aint = this.parent.getInts(i, j, k, l);
/* 21 */     int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 23 */     for (int i1 = 0; i1 < areaHeight; i1++) {
/* 24 */       for (int j1 = 0; j1 < areaWidth; j1++) {
/* 25 */         int k1 = aint[j1 + 0 + (i1 + 0) * k];
/* 26 */         int l1 = aint[j1 + 2 + (i1 + 0) * k];
/* 27 */         int i2 = aint[j1 + 0 + (i1 + 2) * k];
/* 28 */         int j2 = aint[j1 + 2 + (i1 + 2) * k];
/* 29 */         int k2 = aint[j1 + 1 + (i1 + 1) * k];
/* 30 */         initChunkSeed((j1 + areaX), (i1 + areaY));
/*    */         
/* 32 */         if (k2 == 0 && k1 == 0 && l1 == 0 && i2 == 0 && j2 == 0 && nextInt(100) == 0) {
/* 33 */           aint1[j1 + i1 * areaWidth] = BiomeGenBase.mushroomIsland.biomeID;
/*    */         } else {
/* 35 */           aint1[j1 + i1 * areaWidth] = k2;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 40 */     return aint1;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\world\gen\layer\GenLayerAddMushroomIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */