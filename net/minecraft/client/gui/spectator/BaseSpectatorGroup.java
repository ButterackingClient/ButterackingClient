/*    */ package net.minecraft.client.gui.spectator;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.spectator.categories.TeleportToPlayer;
/*    */ import net.minecraft.client.gui.spectator.categories.TeleportToTeam;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class BaseSpectatorGroup
/*    */   implements ISpectatorMenuView
/*    */ {
/* 13 */   private final List<ISpectatorMenuObject> field_178671_a = Lists.newArrayList();
/*    */   
/*    */   public BaseSpectatorGroup() {
/* 16 */     this.field_178671_a.add(new TeleportToPlayer());
/* 17 */     this.field_178671_a.add(new TeleportToTeam());
/*    */   }
/*    */   
/*    */   public List<ISpectatorMenuObject> func_178669_a() {
/* 21 */     return this.field_178671_a;
/*    */   }
/*    */   
/*    */   public IChatComponent func_178670_b() {
/* 25 */     return (IChatComponent)new ChatComponentText("Press a key to select a command, and again to use it.");
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\client\gui\spectator\BaseSpectatorGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */