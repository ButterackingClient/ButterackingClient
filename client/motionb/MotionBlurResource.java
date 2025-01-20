/*    */ package client.motionb;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.util.Locale;
/*    */ import net.minecraft.client.resources.IResource;
/*    */ import net.minecraft.client.resources.data.IMetadataSection;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ 
/*    */ public class MotionBlurResource
/*    */   implements IResource
/*    */ {
/*    */   private static final String JSON = "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}";
/*    */   
/*    */   public InputStream getInputStream() {
/* 16 */     double amount = 0.7D + MotionBlurMod.blurAmount / 100.0D * 3.0D - 0.01D;
/* 17 */     return IOUtils.toInputStream(String.format(Locale.ENGLISH, "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}", new Object[] { Double.valueOf(amount), Double.valueOf(amount), Double.valueOf(amount) }));
/*    */   }
/*    */   
/*    */   public boolean hasMetadata() {
/* 21 */     return false;
/*    */   }
/*    */   
/*    */   public IMetadataSection getMetadata(String metadata) {
/* 25 */     return null;
/*    */   }
/*    */   
/*    */   public ResourceLocation getResourceLocation() {
/* 29 */     return null;
/*    */   }
/*    */   
/*    */   public String getResourcePackName() {
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\client\motionb\MotionBlurResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */