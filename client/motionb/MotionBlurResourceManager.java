/*    */ package client.motionb;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.resources.IResource;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class MotionBlurResourceManager implements IResourceManager {
/*    */   public Set<String> getResourceDomains() {
/* 12 */     return null;
/*    */   }
/*    */   
/*    */   public IResource getResource(ResourceLocation location) throws IOException {
/* 16 */     return new MotionBlurResource();
/*    */   }
/*    */   
/*    */   public List<IResource> getAllResources(ResourceLocation location) throws IOException {
/* 20 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\client\motionb\MotionBlurResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */