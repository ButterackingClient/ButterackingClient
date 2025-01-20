/*    */ package client.motionb;
/*    */ 
/*    */ import client.Client;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import org.apache.commons.lang3.math.NumberUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MotionBlurCommand
/*    */   extends CommandBase
/*    */ {
/* 16 */   private Minecraft mc = Minecraft.getMinecraft();
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) {
/* 20 */     if (args.length == 0) {
/* 21 */       sender.addChatMessage((IChatComponent)new ChatComponentText("Usage: /motionblur <0 - 10>."));
/*    */     } else {
/*    */       
/* 24 */       int amount = NumberUtils.toInt(args[0], -1);
/* 25 */       if (amount >= 0 && amount <= 10) {
/* 26 */         if (MotionBlurMod.isFastRenderEnabled()) {
/* 27 */           sender.addChatMessage((IChatComponent)new ChatComponentText("Motion blur does not work if Fast Render is enabled, please disable it in Options > Video Settings > Performance."));
/*    */         } else {
/*    */           
/* 30 */           if (this.mc.entityRenderer.getShaderGroup() != null) {
/* 31 */             this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
/*    */           }
/* 33 */           if (amount != 0) {
/* 34 */             MotionBlurMod.isEnabled = true;
/* 35 */             MotionBlurMod.blurAmount = amount;
/*    */             try {
/* 37 */               Client.getInstance().loadMb();
/*    */               
/* 39 */               sender.addChatMessage((IChatComponent)new ChatComponentText("Motion blur enabled."));
/*    */             }
/* 41 */             catch (Throwable ex) {
/* 42 */               sender.addChatMessage((IChatComponent)new ChatComponentText("Failed to enable Motion blur."));
/* 43 */               ex.printStackTrace();
/*    */             } 
/*    */           } else {
/*    */             
/* 47 */             MotionBlurMod.isEnabled = false;
/* 48 */             sender.addChatMessage((IChatComponent)new ChatComponentText("Motion blur disabled."));
/*    */           } 
/*    */         } 
/*    */       } else {
/*    */         
/* 53 */         sender.addChatMessage((IChatComponent)new ChatComponentText("Invalid amount."));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getCommandName() {
/* 59 */     return "motionblur";
/*    */   }
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 63 */     return "";
/*    */   }
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 67 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean canCommandSenderUseCommand(ICommandSender sender) {
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\client\motionb\MotionBlurCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */