/*    */ package net.minecraft.block.material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapColor
/*    */ {
/*  7 */   public static final MapColor[] mapColorArray = new MapColor[64];
/*  8 */   public static final MapColor airColor = new MapColor(0, 0);
/*  9 */   public static final MapColor grassColor = new MapColor(1, 8368696);
/* 10 */   public static final MapColor sandColor = new MapColor(2, 16247203);
/* 11 */   public static final MapColor clothColor = new MapColor(3, 13092807);
/* 12 */   public static final MapColor tntColor = new MapColor(4, 16711680);
/* 13 */   public static final MapColor iceColor = new MapColor(5, 10526975);
/* 14 */   public static final MapColor ironColor = new MapColor(6, 10987431);
/* 15 */   public static final MapColor foliageColor = new MapColor(7, 31744);
/* 16 */   public static final MapColor snowColor = new MapColor(8, 16777215);
/* 17 */   public static final MapColor clayColor = new MapColor(9, 10791096);
/* 18 */   public static final MapColor dirtColor = new MapColor(10, 9923917);
/* 19 */   public static final MapColor stoneColor = new MapColor(11, 7368816);
/* 20 */   public static final MapColor waterColor = new MapColor(12, 4210943);
/* 21 */   public static final MapColor woodColor = new MapColor(13, 9402184);
/* 22 */   public static final MapColor quartzColor = new MapColor(14, 16776437);
/* 23 */   public static final MapColor adobeColor = new MapColor(15, 14188339);
/* 24 */   public static final MapColor magentaColor = new MapColor(16, 11685080);
/* 25 */   public static final MapColor lightBlueColor = new MapColor(17, 6724056);
/* 26 */   public static final MapColor yellowColor = new MapColor(18, 15066419);
/* 27 */   public static final MapColor limeColor = new MapColor(19, 8375321);
/* 28 */   public static final MapColor pinkColor = new MapColor(20, 15892389);
/* 29 */   public static final MapColor grayColor = new MapColor(21, 5000268);
/* 30 */   public static final MapColor silverColor = new MapColor(22, 10066329);
/* 31 */   public static final MapColor cyanColor = new MapColor(23, 5013401);
/* 32 */   public static final MapColor purpleColor = new MapColor(24, 8339378);
/* 33 */   public static final MapColor blueColor = new MapColor(25, 3361970);
/* 34 */   public static final MapColor brownColor = new MapColor(26, 6704179);
/* 35 */   public static final MapColor greenColor = new MapColor(27, 6717235);
/* 36 */   public static final MapColor redColor = new MapColor(28, 10040115);
/* 37 */   public static final MapColor blackColor = new MapColor(29, 1644825);
/* 38 */   public static final MapColor goldColor = new MapColor(30, 16445005);
/* 39 */   public static final MapColor diamondColor = new MapColor(31, 6085589);
/* 40 */   public static final MapColor lapisColor = new MapColor(32, 4882687);
/* 41 */   public static final MapColor emeraldColor = new MapColor(33, 55610);
/* 42 */   public static final MapColor obsidianColor = new MapColor(34, 8476209);
/* 43 */   public static final MapColor netherrackColor = new MapColor(35, 7340544);
/*    */ 
/*    */ 
/*    */   
/*    */   public int colorValue;
/*    */ 
/*    */ 
/*    */   
/*    */   public final int colorIndex;
/*    */ 
/*    */ 
/*    */   
/*    */   private MapColor(int index, int color) {
/* 56 */     if (index >= 0 && index <= 63) {
/* 57 */       this.colorIndex = index;
/* 58 */       this.colorValue = color;
/* 59 */       mapColorArray[index] = this;
/*    */     } else {
/* 61 */       throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getMapColor(int p_151643_1_) {
/* 66 */     int i = 220;
/*    */     
/* 68 */     if (p_151643_1_ == 3) {
/* 69 */       i = 135;
/*    */     }
/*    */     
/* 72 */     if (p_151643_1_ == 2) {
/* 73 */       i = 255;
/*    */     }
/*    */     
/* 76 */     if (p_151643_1_ == 1) {
/* 77 */       i = 220;
/*    */     }
/*    */     
/* 80 */     if (p_151643_1_ == 0) {
/* 81 */       i = 180;
/*    */     }
/*    */     
/* 84 */     int j = (this.colorValue >> 16 & 0xFF) * i / 255;
/* 85 */     int k = (this.colorValue >> 8 & 0xFF) * i / 255;
/* 86 */     int l = (this.colorValue & 0xFF) * i / 255;
/* 87 */     return 0xFF000000 | j << 16 | k << 8 | l;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\block\material\MapColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */