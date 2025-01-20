/*    */ package client.gui.slider;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiPageButtonList;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.GuiSlider;
/*    */ 
/*    */ public class Slider
/*    */   extends GuiScreen {
/*  9 */   private GuiPageButtonList.GuiResponder guiResponder = new Instatiator();
/* 10 */   private GuiSlider.FormatHelper formatHelper = new Instatiator2();
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 15 */     this.buttonList.add(new GuiSlider(this.guiResponder, 1, width / 2 - 100, height / 2 - 20, "asdf", 0.0F, 0.5F, 255.0F, this.formatHelper));
/*    */     
/* 17 */     super.initGui();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\client\gui\slider\Slider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */