/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ 
/*     */ public class GuiSlider extends GuiButton {
/*   8 */   private float sliderPosition = 1.0F;
/*     */   public boolean isMouseDown;
/*     */   private String name;
/*     */   private final float min;
/*     */   private final float max;
/*     */   private final GuiPageButtonList.GuiResponder responder;
/*     */   private FormatHelper formatHelper;
/*     */   
/*     */   public GuiSlider(GuiPageButtonList.GuiResponder guiResponder, int idIn, int x, int y, String name, float min, float max, float defaultValue, FormatHelper formatter) {
/*  17 */     super(idIn, x, y, 150, 20, "");
/*  18 */     this.name = name;
/*  19 */     this.min = min;
/*  20 */     this.max = max;
/*  21 */     this.sliderPosition = (defaultValue - min) / (max - min);
/*  22 */     this.formatHelper = formatter;
/*  23 */     this.responder = guiResponder;
/*  24 */     this.displayString = getDisplayString();
/*     */   }
/*     */   
/*     */   public float func_175220_c() {
/*  28 */     return this.min + (this.max - this.min) * this.sliderPosition;
/*     */   }
/*     */   
/*     */   public void func_175218_a(float p_175218_1_, boolean p_175218_2_) {
/*  32 */     this.sliderPosition = (p_175218_1_ - this.min) / (this.max - this.min);
/*  33 */     this.displayString = getDisplayString();
/*     */     
/*  35 */     if (p_175218_2_) {
/*  36 */       this.responder.onTick(this.id, func_175220_c());
/*     */     }
/*     */   }
/*     */   
/*     */   public float func_175217_d() {
/*  41 */     return this.sliderPosition;
/*     */   }
/*     */   
/*     */   private String getDisplayString() {
/*  45 */     return (this.formatHelper == null) ? (String.valueOf(I18n.format(this.name, new Object[0])) + ": " + func_175220_c()) : this.formatHelper.getText(this.id, I18n.format(this.name, new Object[0]), func_175220_c());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getHoverState(boolean mouseOver) {
/*  53 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
/*  60 */     if (this.visible) {
/*  61 */       if (this.isMouseDown) {
/*  62 */         this.sliderPosition = (mouseX - this.xPosition + 4) / (this.width - 8);
/*     */         
/*  64 */         if (this.sliderPosition < 0.0F) {
/*  65 */           this.sliderPosition = 0.0F;
/*     */         }
/*     */         
/*  68 */         if (this.sliderPosition > 1.0F) {
/*  69 */           this.sliderPosition = 1.0F;
/*     */         }
/*     */         
/*  72 */         this.displayString = getDisplayString();
/*  73 */         this.responder.onTick(this.id, func_175220_c());
/*     */       } 
/*     */       
/*  76 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  77 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderPosition * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
/*  78 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderPosition * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_175219_a(float p_175219_1_) {
/*  83 */     this.sliderPosition = p_175219_1_;
/*  84 */     this.displayString = getDisplayString();
/*  85 */     this.responder.onTick(this.id, func_175220_c());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/*  93 */     if (super.mousePressed(mc, mouseX, mouseY)) {
/*  94 */       this.sliderPosition = (mouseX - this.xPosition + 4) / (this.width - 8);
/*     */       
/*  96 */       if (this.sliderPosition < 0.0F) {
/*  97 */         this.sliderPosition = 0.0F;
/*     */       }
/*     */       
/* 100 */       if (this.sliderPosition > 1.0F) {
/* 101 */         this.sliderPosition = 1.0F;
/*     */       }
/*     */       
/* 104 */       this.displayString = getDisplayString();
/* 105 */       this.responder.onTick(this.id, func_175220_c());
/* 106 */       this.isMouseDown = true;
/* 107 */       return true;
/*     */     } 
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY) {
/* 117 */     this.isMouseDown = false;
/*     */   }
/*     */   
/*     */   public static interface FormatHelper {
/*     */     String getText(int param1Int, String param1String, float param1Float);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\client\gui\GuiSlider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */