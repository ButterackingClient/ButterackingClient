/*    */ package net.optifine;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.optifine.config.ConnectedParser;
/*    */ 
/*    */ public class CustomPanoramaProperties
/*    */ {
/*    */   private String path;
/*    */   private ResourceLocation[] panoramaLocations;
/* 11 */   private int weight = 1;
/* 12 */   private int blur1 = 64;
/* 13 */   private int blur2 = 3;
/* 14 */   private int blur3 = 3;
/* 15 */   private int overlay1Top = -2130706433;
/* 16 */   private int overlay1Bottom = 16777215;
/* 17 */   private int overlay2Top = 0;
/* 18 */   private int overlay2Bottom = Integer.MIN_VALUE;
/*    */   
/*    */   public CustomPanoramaProperties(String path, Properties props) {
/* 21 */     ConnectedParser connectedparser = new ConnectedParser("CustomPanorama");
/* 22 */     this.path = path;
/* 23 */     this.panoramaLocations = new ResourceLocation[6];
/*    */     
/* 25 */     for (int i = 0; i < this.panoramaLocations.length; i++) {
/* 26 */       this.panoramaLocations[i] = new ResourceLocation(String.valueOf(path) + "/panorama_" + i + ".png");
/*    */     }
/*    */     
/* 29 */     this.weight = connectedparser.parseInt(props.getProperty("weight"), 1);
/* 30 */     this.blur1 = connectedparser.parseInt(props.getProperty("blur1"), 64);
/* 31 */     this.blur2 = connectedparser.parseInt(props.getProperty("blur2"), 3);
/* 32 */     this.blur3 = connectedparser.parseInt(props.getProperty("blur3"), 3);
/* 33 */     this.overlay1Top = ConnectedParser.parseColor4(props.getProperty("overlay1.top"), -2130706433);
/* 34 */     this.overlay1Bottom = ConnectedParser.parseColor4(props.getProperty("overlay1.bottom"), 16777215);
/* 35 */     this.overlay2Top = ConnectedParser.parseColor4(props.getProperty("overlay2.top"), 0);
/* 36 */     this.overlay2Bottom = ConnectedParser.parseColor4(props.getProperty("overlay2.bottom"), -2147483648);
/*    */   }
/*    */   
/*    */   public ResourceLocation[] getPanoramaLocations() {
/* 40 */     return this.panoramaLocations;
/*    */   }
/*    */   
/*    */   public int getWeight() {
/* 44 */     return this.weight;
/*    */   }
/*    */   
/*    */   public int getBlur1() {
/* 48 */     return this.blur1;
/*    */   }
/*    */   
/*    */   public int getBlur2() {
/* 52 */     return this.blur2;
/*    */   }
/*    */   
/*    */   public int getBlur3() {
/* 56 */     return this.blur3;
/*    */   }
/*    */   
/*    */   public int getOverlay1Top() {
/* 60 */     return this.overlay1Top;
/*    */   }
/*    */   
/*    */   public int getOverlay1Bottom() {
/* 64 */     return this.overlay1Bottom;
/*    */   }
/*    */   
/*    */   public int getOverlay2Top() {
/* 68 */     return this.overlay2Top;
/*    */   }
/*    */   
/*    */   public int getOverlay2Bottom() {
/* 72 */     return this.overlay2Bottom;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 76 */     return this.path + ", weight: " + this.weight + ", blur: " + this.blur1 + " " + this.blur2 + " " + this.blur3 + ", overlay: " + this.overlay1Top + " " + this.overlay1Bottom + " " + this.overlay2Top + " " + this.overlay2Bottom;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\optifine\CustomPanoramaProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */