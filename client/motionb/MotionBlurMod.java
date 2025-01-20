/*    */ package client.motionb;
/*    */ 
/*    */ import client.KeyEvent;
/*    */ import client.event.EventManager;
/*    */ import client.event.SubscribeEvent;
/*    */ import client.event.impl.ClientTickEvent;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.SimpleReloadableResourceManager;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MotionBlurMod
/*    */ {
/* 23 */   private Minecraft mc = Minecraft.getMinecraft();
/*    */   private Map domainResourceManagers;
/*    */   
/*    */   public void init() {
/* 27 */     EventManager.register(this);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onClientTick(ClientTickEvent event) {
/* 32 */     if (this.domainResourceManagers == null) {
/*    */       try {
/* 34 */         for (Field field : SimpleReloadableResourceManager.class.getDeclaredFields()) {
/* 35 */           if (field.getType() == Map.class) {
/* 36 */             field.setAccessible(true);
/* 37 */             this.domainResourceManagers = (Map)field.get(Minecraft.getMinecraft().getResourceManager());
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/* 42 */       } catch (Exception e) {
/* 43 */         throw new RuntimeException(e);
/*    */       } 
/*    */     }
/* 46 */     if (!this.domainResourceManagers.containsKey("motionblur")) {
/* 47 */       this.domainResourceManagers.put("motionblur", new MotionBlurResourceManager());
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onKey(KeyEvent event) {
/* 53 */     if (this.mc.thePlayer != null && isEnabled && Keyboard.isKeyDown(this.mc.gameSettings.keyBindTogglePerspective.getKeyCode())) {
/*    */       try {
/* 55 */         this.mc.entityRenderer.loadShader(new ResourceLocation("motionblur", "motionblur"));
/* 56 */         this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
/*    */       }
/* 58 */       catch (Exception exception) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean isFastRenderEnabled() {
/*    */     try {
/* 64 */       Field fastRender = GameSettings.class.getDeclaredField("ofFastRender");
/* 65 */       return fastRender.getBoolean((Minecraft.getMinecraft()).gameSettings);
/*    */     }
/* 67 */     catch (Exception var1) {
/* 68 */       return false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 73 */   public static double blurAmount = 0.75D;
/*    */   public static boolean isEnabled = false;
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\client\motionb\MotionBlurMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */