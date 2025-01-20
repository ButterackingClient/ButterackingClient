/*    */ package net.optifine.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiOptionButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.GuiYesNo;
/*    */ import net.minecraft.client.gui.GuiYesNoCallback;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class GuiOtherSettingsOF extends GuiScreen implements GuiYesNoCallback {
/*    */   private GuiScreen prevScreen;
/*    */   protected String title;
/*    */   private GameSettings settings;
/* 15 */   private static GameSettings.Options[] enumOptions = new GameSettings.Options[] { GameSettings.Options.LAGOMETER, GameSettings.Options.PROFILER, GameSettings.Options.SHOW_FPS, GameSettings.Options.ADVANCED_TOOLTIPS, GameSettings.Options.WEATHER, GameSettings.Options.TIME, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.FULLSCREEN_MODE, GameSettings.Options.ANAGLYPH, GameSettings.Options.AUTOSAVE_TICKS, GameSettings.Options.SCREENSHOT_SIZE, GameSettings.Options.SHOW_GL_ERRORS };
/* 16 */   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());
/*    */   
/*    */   public GuiOtherSettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
/* 19 */     this.prevScreen = guiscreen;
/* 20 */     this.settings = gamesettings;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 28 */     this.title = I18n.format("of.options.otherTitle", new Object[0]);
/* 29 */     this.buttonList.clear();
/*    */     
/* 31 */     for (int i = 0; i < enumOptions.length; i++) {
/* 32 */       GameSettings.Options gamesettings$options = enumOptions[i];
/* 33 */       int j = width / 2 - 155 + i % 2 * 160;
/* 34 */       int k = height / 6 + 21 * i / 2 - 12;
/*    */       
/* 36 */       if (!gamesettings$options.getEnumFloat()) {
/* 37 */         this.buttonList.add(new GuiOptionButtonOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.settings.getKeyBinding(gamesettings$options)));
/*    */       } else {
/* 39 */         this.buttonList.add(new GuiOptionSliderOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     this.buttonList.add(new GuiButton(210, width / 2 - 100, height / 6 + 168 + 11 - 44, I18n.format("of.options.other.reset", new Object[0])));
/* 44 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guibutton) {
/* 51 */     if (guibutton.enabled) {
/* 52 */       if (guibutton.id < 200 && guibutton instanceof GuiOptionButton) {
/* 53 */         this.settings.setOptionValue(((GuiOptionButton)guibutton).returnEnumOptions(), 1);
/* 54 */         guibutton.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(guibutton.id));
/*    */       } 
/*    */       
/* 57 */       if (guibutton.id == 200) {
/* 58 */         this.mc.gameSettings.saveOptions();
/* 59 */         this.mc.displayGuiScreen(this.prevScreen);
/*    */       } 
/*    */       
/* 62 */       if (guibutton.id == 210) {
/* 63 */         this.mc.gameSettings.saveOptions();
/* 64 */         GuiYesNo guiyesno = new GuiYesNo(this, I18n.format("of.message.other.reset", new Object[0]), "", 9999);
/* 65 */         this.mc.displayGuiScreen((GuiScreen)guiyesno);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void confirmClicked(boolean flag, int i) {
/* 71 */     if (flag) {
/* 72 */       this.mc.gameSettings.resetSettings();
/*    */     }
/*    */     
/* 75 */     this.mc.displayGuiScreen(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int x, int y, float f) {
/* 82 */     drawDefaultBackground();
/* 83 */     drawCenteredString(this.fontRendererObj, this.title, width / 2, 15, 16777215);
/* 84 */     super.drawScreen(x, y, f);
/* 85 */     this.tooltipManager.drawTooltips(x, y, this.buttonList);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\optifine\gui\GuiOtherSettingsOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */