/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.IntHashMap;
/*     */ 
/*     */ public class GuiPageButtonList
/*     */   extends GuiListExtended
/*     */ {
/*  14 */   private final List<GuiEntry> field_178074_u = Lists.newArrayList();
/*  15 */   private final IntHashMap<Gui> field_178073_v = new IntHashMap();
/*  16 */   private final List<GuiTextField> field_178072_w = Lists.newArrayList();
/*     */   private final GuiListEntry[][] field_178078_x;
/*     */   private int field_178077_y;
/*     */   private GuiResponder field_178076_z;
/*     */   private Gui field_178075_A;
/*     */   
/*     */   public GuiPageButtonList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn, GuiResponder p_i45536_7_, GuiListEntry[]... p_i45536_8_) {
/*  23 */     super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
/*  24 */     this.field_178076_z = p_i45536_7_;
/*  25 */     this.field_178078_x = p_i45536_8_;
/*  26 */     this.field_148163_i = false;
/*  27 */     func_178069_s();
/*  28 */     func_178055_t(); } private void func_178069_s() {
/*     */     byte b;
/*     */     int i;
/*     */     GuiListEntry[][] arrayOfGuiListEntry;
/*  32 */     for (i = (arrayOfGuiListEntry = this.field_178078_x).length, b = 0; b < i; ) { GuiListEntry[] aguipagebuttonlist$guilistentry = arrayOfGuiListEntry[b];
/*  33 */       for (int j = 0; j < aguipagebuttonlist$guilistentry.length; j += 2) {
/*  34 */         GuiListEntry guipagebuttonlist$guilistentry = aguipagebuttonlist$guilistentry[j];
/*  35 */         GuiListEntry guipagebuttonlist$guilistentry1 = (j < aguipagebuttonlist$guilistentry.length - 1) ? aguipagebuttonlist$guilistentry[j + 1] : null;
/*  36 */         Gui gui = func_178058_a(guipagebuttonlist$guilistentry, 0, (guipagebuttonlist$guilistentry1 == null));
/*  37 */         Gui gui1 = func_178058_a(guipagebuttonlist$guilistentry1, 160, (guipagebuttonlist$guilistentry == null));
/*  38 */         GuiEntry guipagebuttonlist$guientry = new GuiEntry(gui, gui1);
/*  39 */         this.field_178074_u.add(guipagebuttonlist$guientry);
/*     */         
/*  41 */         if (guipagebuttonlist$guilistentry != null && gui != null) {
/*  42 */           this.field_178073_v.addKey(guipagebuttonlist$guilistentry.func_178935_b(), gui);
/*     */           
/*  44 */           if (gui instanceof GuiTextField) {
/*  45 */             this.field_178072_w.add((GuiTextField)gui);
/*     */           }
/*     */         } 
/*     */         
/*  49 */         if (guipagebuttonlist$guilistentry1 != null && gui1 != null) {
/*  50 */           this.field_178073_v.addKey(guipagebuttonlist$guilistentry1.func_178935_b(), gui1);
/*     */           
/*  52 */           if (gui1 instanceof GuiTextField)
/*  53 */             this.field_178072_w.add((GuiTextField)gui1); 
/*     */         } 
/*     */       } 
/*     */       b++; }
/*     */   
/*     */   }
/*     */   
/*     */   private void func_178055_t() {
/*  61 */     this.field_178074_u.clear();
/*     */     
/*  63 */     for (int i = 0; i < (this.field_178078_x[this.field_178077_y]).length; i += 2) {
/*  64 */       GuiListEntry guipagebuttonlist$guilistentry = this.field_178078_x[this.field_178077_y][i];
/*  65 */       GuiListEntry guipagebuttonlist$guilistentry1 = (i < (this.field_178078_x[this.field_178077_y]).length - 1) ? this.field_178078_x[this.field_178077_y][i + 1] : null;
/*  66 */       Gui gui = (Gui)this.field_178073_v.lookup(guipagebuttonlist$guilistentry.func_178935_b());
/*  67 */       Gui gui1 = (guipagebuttonlist$guilistentry1 != null) ? (Gui)this.field_178073_v.lookup(guipagebuttonlist$guilistentry1.func_178935_b()) : null;
/*  68 */       GuiEntry guipagebuttonlist$guientry = new GuiEntry(gui, gui1);
/*  69 */       this.field_178074_u.add(guipagebuttonlist$guientry);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_181156_c(int p_181156_1_) {
/*  74 */     if (p_181156_1_ != this.field_178077_y) {
/*  75 */       int i = this.field_178077_y;
/*  76 */       this.field_178077_y = p_181156_1_;
/*  77 */       func_178055_t();
/*  78 */       func_178060_e(i, p_181156_1_);
/*  79 */       this.amountScrolled = 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int func_178059_e() {
/*  84 */     return this.field_178077_y;
/*     */   }
/*     */   
/*     */   public int func_178057_f() {
/*  88 */     return this.field_178078_x.length;
/*     */   }
/*     */   
/*     */   public Gui func_178056_g() {
/*  92 */     return this.field_178075_A;
/*     */   }
/*     */   
/*     */   public void func_178071_h() {
/*  96 */     if (this.field_178077_y > 0) {
/*  97 */       func_181156_c(this.field_178077_y - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_178064_i() {
/* 102 */     if (this.field_178077_y < this.field_178078_x.length - 1) {
/* 103 */       func_181156_c(this.field_178077_y + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public Gui func_178061_c(int p_178061_1_) {
/* 108 */     return (Gui)this.field_178073_v.lookup(p_178061_1_); } private void func_178060_e(int p_178060_1_, int p_178060_2_) {
/*     */     byte b;
/*     */     int i;
/*     */     GuiListEntry[] arrayOfGuiListEntry;
/* 112 */     for (i = (arrayOfGuiListEntry = this.field_178078_x[p_178060_1_]).length, b = 0; b < i; ) { GuiListEntry guipagebuttonlist$guilistentry = arrayOfGuiListEntry[b];
/* 113 */       if (guipagebuttonlist$guilistentry != null) {
/* 114 */         func_178066_a((Gui)this.field_178073_v.lookup(guipagebuttonlist$guilistentry.func_178935_b()), false);
/*     */       }
/*     */       b++; }
/*     */     
/* 118 */     for (i = (arrayOfGuiListEntry = this.field_178078_x[p_178060_2_]).length, b = 0; b < i; ) { GuiListEntry guipagebuttonlist$guilistentry1 = arrayOfGuiListEntry[b];
/* 119 */       if (guipagebuttonlist$guilistentry1 != null)
/* 120 */         func_178066_a((Gui)this.field_178073_v.lookup(guipagebuttonlist$guilistentry1.func_178935_b()), true); 
/*     */       b++; }
/*     */   
/*     */   }
/*     */   
/*     */   private void func_178066_a(Gui p_178066_1_, boolean p_178066_2_) {
/* 126 */     if (p_178066_1_ instanceof GuiButton) {
/* 127 */       ((GuiButton)p_178066_1_).visible = p_178066_2_;
/* 128 */     } else if (p_178066_1_ instanceof GuiTextField) {
/* 129 */       ((GuiTextField)p_178066_1_).setVisible(p_178066_2_);
/* 130 */     } else if (p_178066_1_ instanceof GuiLabel) {
/* 131 */       ((GuiLabel)p_178066_1_).visible = p_178066_2_;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Gui func_178058_a(GuiListEntry p_178058_1_, int p_178058_2_, boolean p_178058_3_) {
/* 136 */     return (p_178058_1_ instanceof GuiSlideEntry) ? func_178067_a(this.width / 2 - 155 + p_178058_2_, 0, (GuiSlideEntry)p_178058_1_) : ((p_178058_1_ instanceof GuiButtonEntry) ? func_178065_a(this.width / 2 - 155 + p_178058_2_, 0, (GuiButtonEntry)p_178058_1_) : ((p_178058_1_ instanceof EditBoxEntry) ? func_178068_a(this.width / 2 - 155 + p_178058_2_, 0, (EditBoxEntry)p_178058_1_) : ((p_178058_1_ instanceof GuiLabelEntry) ? func_178063_a(this.width / 2 - 155 + p_178058_2_, 0, (GuiLabelEntry)p_178058_1_, p_178058_3_) : null)));
/*     */   }
/*     */   
/*     */   public void func_181155_a(boolean p_181155_1_) {
/* 140 */     for (GuiEntry guipagebuttonlist$guientry : this.field_178074_u) {
/* 141 */       if (guipagebuttonlist$guientry.field_178029_b instanceof GuiButton) {
/* 142 */         ((GuiButton)guipagebuttonlist$guientry.field_178029_b).enabled = p_181155_1_;
/*     */       }
/*     */       
/* 145 */       if (guipagebuttonlist$guientry.field_178030_c instanceof GuiButton) {
/* 146 */         ((GuiButton)guipagebuttonlist$guientry.field_178030_c).enabled = p_181155_1_;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent) {
/* 152 */     boolean flag = super.mouseClicked(mouseX, mouseY, mouseEvent);
/* 153 */     int i = getSlotIndexFromScreenCoords(mouseX, mouseY);
/*     */     
/* 155 */     if (i >= 0) {
/* 156 */       GuiEntry guipagebuttonlist$guientry = getListEntry(i);
/*     */       
/* 158 */       if (this.field_178075_A != guipagebuttonlist$guientry.field_178028_d && this.field_178075_A != null && this.field_178075_A instanceof GuiTextField) {
/* 159 */         ((GuiTextField)this.field_178075_A).setFocused(false);
/*     */       }
/*     */       
/* 162 */       this.field_178075_A = guipagebuttonlist$guientry.field_178028_d;
/*     */     } 
/*     */     
/* 165 */     return flag;
/*     */   }
/*     */   
/*     */   private GuiSlider func_178067_a(int p_178067_1_, int p_178067_2_, GuiSlideEntry p_178067_3_) {
/* 169 */     GuiSlider guislider = new GuiSlider(this.field_178076_z, p_178067_3_.func_178935_b(), p_178067_1_, p_178067_2_, p_178067_3_.func_178936_c(), p_178067_3_.func_178943_e(), p_178067_3_.func_178944_f(), p_178067_3_.func_178942_g(), p_178067_3_.func_178945_a());
/* 170 */     guislider.visible = p_178067_3_.func_178934_d();
/* 171 */     return guislider;
/*     */   }
/*     */   
/*     */   private GuiListButton func_178065_a(int p_178065_1_, int p_178065_2_, GuiButtonEntry p_178065_3_) {
/* 175 */     GuiListButton guilistbutton = new GuiListButton(this.field_178076_z, p_178065_3_.func_178935_b(), p_178065_1_, p_178065_2_, p_178065_3_.func_178936_c(), p_178065_3_.func_178940_a());
/* 176 */     guilistbutton.visible = p_178065_3_.func_178934_d();
/* 177 */     return guilistbutton;
/*     */   }
/*     */   
/*     */   private GuiTextField func_178068_a(int p_178068_1_, int p_178068_2_, EditBoxEntry p_178068_3_) {
/* 181 */     GuiTextField guitextfield = new GuiTextField(p_178068_3_.func_178935_b(), this.mc.fontRendererObj, p_178068_1_, p_178068_2_, 150, 20);
/* 182 */     guitextfield.setText(p_178068_3_.func_178936_c());
/* 183 */     guitextfield.func_175207_a(this.field_178076_z);
/* 184 */     guitextfield.setVisible(p_178068_3_.func_178934_d());
/* 185 */     guitextfield.setValidator(p_178068_3_.func_178950_a());
/* 186 */     return guitextfield;
/*     */   }
/*     */ 
/*     */   
/*     */   private GuiLabel func_178063_a(int p_178063_1_, int p_178063_2_, GuiLabelEntry p_178063_3_, boolean p_178063_4_) {
/*     */     GuiLabel guilabel;
/* 192 */     if (p_178063_4_) {
/* 193 */       guilabel = new GuiLabel(this.mc.fontRendererObj, p_178063_3_.func_178935_b(), p_178063_1_, p_178063_2_, this.width - p_178063_1_ * 2, 20, -1);
/*     */     } else {
/* 195 */       guilabel = new GuiLabel(this.mc.fontRendererObj, p_178063_3_.func_178935_b(), p_178063_1_, p_178063_2_, 150, 20, -1);
/*     */     } 
/*     */     
/* 198 */     guilabel.visible = p_178063_3_.func_178934_d();
/* 199 */     guilabel.func_175202_a(p_178063_3_.func_178936_c());
/* 200 */     guilabel.setCentered();
/* 201 */     return guilabel;
/*     */   }
/*     */   
/*     */   public void func_178062_a(char p_178062_1_, int p_178062_2_) {
/* 205 */     if (this.field_178075_A instanceof GuiTextField) {
/* 206 */       GuiTextField guitextfield = (GuiTextField)this.field_178075_A;
/*     */       
/* 208 */       if (!GuiScreen.isKeyComboCtrlV(p_178062_2_)) {
/* 209 */         if (p_178062_2_ == 15) {
/* 210 */           guitextfield.setFocused(false);
/* 211 */           int k = this.field_178072_w.indexOf(this.field_178075_A);
/*     */           
/* 213 */           if (GuiScreen.isShiftKeyDown()) {
/* 214 */             if (k == 0) {
/* 215 */               k = this.field_178072_w.size() - 1;
/*     */             } else {
/* 217 */               k--;
/*     */             } 
/* 219 */           } else if (k == this.field_178072_w.size() - 1) {
/* 220 */             k = 0;
/*     */           } else {
/* 222 */             k++;
/*     */           } 
/*     */           
/* 225 */           this.field_178075_A = this.field_178072_w.get(k);
/* 226 */           guitextfield = (GuiTextField)this.field_178075_A;
/* 227 */           guitextfield.setFocused(true);
/* 228 */           int l = guitextfield.yPosition + this.slotHeight;
/* 229 */           int i1 = guitextfield.yPosition;
/*     */           
/* 231 */           if (l > this.bottom) {
/* 232 */             this.amountScrolled += (l - this.bottom);
/* 233 */           } else if (i1 < this.top) {
/* 234 */             this.amountScrolled = i1;
/*     */           } 
/*     */         } else {
/* 237 */           guitextfield.textboxKeyTyped(p_178062_1_, p_178062_2_);
/*     */         } 
/*     */       } else {
/* 240 */         String s = GuiScreen.getClipboardString();
/* 241 */         String[] astring = s.split(";");
/* 242 */         int i = this.field_178072_w.indexOf(this.field_178075_A);
/* 243 */         int j = i; byte b; int k;
/*     */         String[] arrayOfString1;
/* 245 */         for (k = (arrayOfString1 = astring).length, b = 0; b < k; ) { String s1 = arrayOfString1[b];
/* 246 */           ((GuiTextField)this.field_178072_w.get(j)).setText(s1);
/*     */           
/* 248 */           if (j == this.field_178072_w.size() - 1) {
/* 249 */             j = 0;
/*     */           } else {
/* 251 */             j++;
/*     */           } 
/*     */           
/* 254 */           if (j == i) {
/*     */             break;
/*     */           }
/*     */           b++; }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiEntry getListEntry(int index) {
/* 266 */     return this.field_178074_u.get(index);
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 270 */     return this.field_178074_u.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListWidth() {
/* 277 */     return 400;
/*     */   }
/*     */   
/*     */   protected int getScrollBarX() {
/* 281 */     return super.getScrollBarX() + 32;
/*     */   }
/*     */   
/*     */   public static class EditBoxEntry extends GuiListEntry {
/*     */     private final Predicate<String> field_178951_a;
/*     */     
/*     */     public EditBoxEntry(int p_i45534_1_, String p_i45534_2_, boolean p_i45534_3_, Predicate<String> p_i45534_4_) {
/* 288 */       super(p_i45534_1_, p_i45534_2_, p_i45534_3_);
/* 289 */       this.field_178951_a = (Predicate<String>)Objects.firstNonNull(p_i45534_4_, Predicates.alwaysTrue());
/*     */     }
/*     */     
/*     */     public Predicate<String> func_178950_a() {
/* 293 */       return this.field_178951_a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GuiButtonEntry extends GuiListEntry {
/*     */     private final boolean field_178941_a;
/*     */     
/*     */     public GuiButtonEntry(int p_i45535_1_, String p_i45535_2_, boolean p_i45535_3_, boolean p_i45535_4_) {
/* 301 */       super(p_i45535_1_, p_i45535_2_, p_i45535_3_);
/* 302 */       this.field_178941_a = p_i45535_4_;
/*     */     }
/*     */     
/*     */     public boolean func_178940_a() {
/* 306 */       return this.field_178941_a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GuiEntry implements GuiListExtended.IGuiListEntry {
/* 311 */     private final Minecraft field_178031_a = Minecraft.getMinecraft();
/*     */     private final Gui field_178029_b;
/*     */     private final Gui field_178030_c;
/*     */     private Gui field_178028_d;
/*     */     
/*     */     public GuiEntry(Gui p_i45533_1_, Gui p_i45533_2_) {
/* 317 */       this.field_178029_b = p_i45533_1_;
/* 318 */       this.field_178030_c = p_i45533_2_;
/*     */     }
/*     */     
/*     */     public Gui func_178022_a() {
/* 322 */       return this.field_178029_b;
/*     */     }
/*     */     
/*     */     public Gui func_178021_b() {
/* 326 */       return this.field_178030_c;
/*     */     }
/*     */     
/*     */     public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
/* 330 */       func_178017_a(this.field_178029_b, y, mouseX, mouseY, false);
/* 331 */       func_178017_a(this.field_178030_c, y, mouseX, mouseY, false);
/*     */     }
/*     */     
/*     */     private void func_178017_a(Gui p_178017_1_, int p_178017_2_, int p_178017_3_, int p_178017_4_, boolean p_178017_5_) {
/* 335 */       if (p_178017_1_ != null) {
/* 336 */         if (p_178017_1_ instanceof GuiButton) {
/* 337 */           func_178024_a((GuiButton)p_178017_1_, p_178017_2_, p_178017_3_, p_178017_4_, p_178017_5_);
/* 338 */         } else if (p_178017_1_ instanceof GuiTextField) {
/* 339 */           func_178027_a((GuiTextField)p_178017_1_, p_178017_2_, p_178017_5_);
/* 340 */         } else if (p_178017_1_ instanceof GuiLabel) {
/* 341 */           func_178025_a((GuiLabel)p_178017_1_, p_178017_2_, p_178017_3_, p_178017_4_, p_178017_5_);
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     private void func_178024_a(GuiButton p_178024_1_, int p_178024_2_, int p_178024_3_, int p_178024_4_, boolean p_178024_5_) {
/* 347 */       p_178024_1_.yPosition = p_178024_2_;
/*     */       
/* 349 */       if (!p_178024_5_) {
/* 350 */         p_178024_1_.drawButton(this.field_178031_a, p_178024_3_, p_178024_4_);
/*     */       }
/*     */     }
/*     */     
/*     */     private void func_178027_a(GuiTextField p_178027_1_, int p_178027_2_, boolean p_178027_3_) {
/* 355 */       p_178027_1_.yPosition = p_178027_2_;
/*     */       
/* 357 */       if (!p_178027_3_) {
/* 358 */         p_178027_1_.drawTextBox();
/*     */       }
/*     */     }
/*     */     
/*     */     private void func_178025_a(GuiLabel p_178025_1_, int p_178025_2_, int p_178025_3_, int p_178025_4_, boolean p_178025_5_) {
/* 363 */       p_178025_1_.field_146174_h = p_178025_2_;
/*     */       
/* 365 */       if (!p_178025_5_) {
/* 366 */         p_178025_1_.drawLabel(this.field_178031_a, p_178025_3_, p_178025_4_);
/*     */       }
/*     */     }
/*     */     
/*     */     public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
/* 371 */       func_178017_a(this.field_178029_b, p_178011_3_, 0, 0, true);
/* 372 */       func_178017_a(this.field_178030_c, p_178011_3_, 0, 0, true);
/*     */     }
/*     */     
/*     */     public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
/* 376 */       boolean flag = func_178026_a(this.field_178029_b, p_148278_2_, p_148278_3_, p_148278_4_);
/* 377 */       boolean flag1 = func_178026_a(this.field_178030_c, p_148278_2_, p_148278_3_, p_148278_4_);
/* 378 */       return !(!flag && !flag1);
/*     */     }
/*     */     
/*     */     private boolean func_178026_a(Gui p_178026_1_, int p_178026_2_, int p_178026_3_, int p_178026_4_) {
/* 382 */       if (p_178026_1_ == null)
/* 383 */         return false; 
/* 384 */       if (p_178026_1_ instanceof GuiButton) {
/* 385 */         return func_178023_a((GuiButton)p_178026_1_, p_178026_2_, p_178026_3_, p_178026_4_);
/*     */       }
/* 387 */       if (p_178026_1_ instanceof GuiTextField) {
/* 388 */         func_178018_a((GuiTextField)p_178026_1_, p_178026_2_, p_178026_3_, p_178026_4_);
/*     */       }
/*     */       
/* 391 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_178023_a(GuiButton p_178023_1_, int p_178023_2_, int p_178023_3_, int p_178023_4_) {
/* 396 */       boolean flag = p_178023_1_.mousePressed(this.field_178031_a, p_178023_2_, p_178023_3_);
/*     */       
/* 398 */       if (flag) {
/* 399 */         this.field_178028_d = p_178023_1_;
/*     */       }
/*     */       
/* 402 */       return flag;
/*     */     }
/*     */     
/*     */     private void func_178018_a(GuiTextField p_178018_1_, int p_178018_2_, int p_178018_3_, int p_178018_4_) {
/* 406 */       p_178018_1_.mouseClicked(p_178018_2_, p_178018_3_, p_178018_4_);
/*     */       
/* 408 */       if (p_178018_1_.isFocused()) {
/* 409 */         this.field_178028_d = p_178018_1_;
/*     */       }
/*     */     }
/*     */     
/*     */     public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
/* 414 */       func_178016_b(this.field_178029_b, x, y, mouseEvent);
/* 415 */       func_178016_b(this.field_178030_c, x, y, mouseEvent);
/*     */     }
/*     */     
/*     */     private void func_178016_b(Gui p_178016_1_, int p_178016_2_, int p_178016_3_, int p_178016_4_) {
/* 419 */       if (p_178016_1_ != null && 
/* 420 */         p_178016_1_ instanceof GuiButton) {
/* 421 */         func_178019_b((GuiButton)p_178016_1_, p_178016_2_, p_178016_3_, p_178016_4_);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178019_b(GuiButton p_178019_1_, int p_178019_2_, int p_178019_3_, int p_178019_4_) {
/* 427 */       p_178019_1_.mouseReleased(p_178019_2_, p_178019_3_);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GuiLabelEntry extends GuiListEntry {
/*     */     public GuiLabelEntry(int p_i45532_1_, String p_i45532_2_, boolean p_i45532_3_) {
/* 433 */       super(p_i45532_1_, p_i45532_2_, p_i45532_3_);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GuiListEntry {
/*     */     private final int field_178939_a;
/*     */     private final String field_178937_b;
/*     */     private final boolean field_178938_c;
/*     */     
/*     */     public GuiListEntry(int p_i45531_1_, String p_i45531_2_, boolean p_i45531_3_) {
/* 443 */       this.field_178939_a = p_i45531_1_;
/* 444 */       this.field_178937_b = p_i45531_2_;
/* 445 */       this.field_178938_c = p_i45531_3_;
/*     */     }
/*     */     
/*     */     public int func_178935_b() {
/* 449 */       return this.field_178939_a;
/*     */     }
/*     */     
/*     */     public String func_178936_c() {
/* 453 */       return this.field_178937_b;
/*     */     }
/*     */     
/*     */     public boolean func_178934_d() {
/* 457 */       return this.field_178938_c;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface GuiResponder {
/*     */     void func_175321_a(int param1Int, boolean param1Boolean);
/*     */     
/*     */     void onTick(int param1Int, float param1Float);
/*     */     
/*     */     void func_175319_a(int param1Int, String param1String);
/*     */   }
/*     */   
/*     */   public static class GuiSlideEntry extends GuiListEntry {
/*     */     private final GuiSlider.FormatHelper field_178949_a;
/*     */     private final float field_178947_b;
/*     */     private final float field_178948_c;
/*     */     private final float field_178946_d;
/*     */     
/*     */     public GuiSlideEntry(int p_i45530_1_, String p_i45530_2_, boolean p_i45530_3_, GuiSlider.FormatHelper p_i45530_4_, float p_i45530_5_, float p_i45530_6_, float p_i45530_7_) {
/* 476 */       super(p_i45530_1_, p_i45530_2_, p_i45530_3_);
/* 477 */       this.field_178949_a = p_i45530_4_;
/* 478 */       this.field_178947_b = p_i45530_5_;
/* 479 */       this.field_178948_c = p_i45530_6_;
/* 480 */       this.field_178946_d = p_i45530_7_;
/*     */     }
/*     */     
/*     */     public GuiSlider.FormatHelper func_178945_a() {
/* 484 */       return this.field_178949_a;
/*     */     }
/*     */     
/*     */     public float func_178943_e() {
/* 488 */       return this.field_178947_b;
/*     */     }
/*     */     
/*     */     public float func_178944_f() {
/* 492 */       return this.field_178948_c;
/*     */     }
/*     */     
/*     */     public float func_178942_g() {
/* 496 */       return this.field_178946_d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\client\gui\GuiPageButtonList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */