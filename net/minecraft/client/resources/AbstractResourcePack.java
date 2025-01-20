/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractResourcePack
/*    */   implements IResourcePack
/*    */ {
/* 25 */   private static final Logger resourceLog = LogManager.getLogger();
/*    */   public final File resourcePackFile;
/*    */   
/*    */   public AbstractResourcePack(File resourcePackFileIn) {
/* 29 */     this.resourcePackFile = resourcePackFileIn;
/*    */   }
/*    */   
/*    */   private static String locationToName(ResourceLocation location) {
/* 33 */     return String.format("%s/%s/%s", new Object[] { "assets", location.getResourceDomain(), location.getResourcePath() });
/*    */   }
/*    */   
/*    */   protected static String getRelativeName(File p_110595_0_, File p_110595_1_) {
/* 37 */     return p_110595_0_.toURI().relativize(p_110595_1_.toURI()).getPath();
/*    */   }
/*    */   
/*    */   public InputStream getInputStream(ResourceLocation location) throws IOException {
/* 41 */     return getInputStreamByName(locationToName(location));
/*    */   }
/*    */   
/*    */   public boolean resourceExists(ResourceLocation location) {
/* 45 */     return hasResourceName(locationToName(location));
/*    */   }
/*    */   
/*    */   protected abstract InputStream getInputStreamByName(String paramString) throws IOException;
/*    */   
/*    */   protected abstract boolean hasResourceName(String paramString);
/*    */   
/*    */   protected void logNameNotLowercase(String name) {
/* 53 */     resourceLog.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", new Object[] { name, this.resourcePackFile });
/*    */   }
/*    */   
/*    */   public <T extends net.minecraft.client.resources.data.IMetadataSection> T getPackMetadata(IMetadataSerializer metadataSerializer, String metadataSectionName) throws IOException {
/* 57 */     return readMetadata(metadataSerializer, getInputStreamByName("pack.mcmeta"), metadataSectionName);
/*    */   }
/*    */   
/*    */   static <T extends net.minecraft.client.resources.data.IMetadataSection> T readMetadata(IMetadataSerializer p_110596_0_, InputStream p_110596_1_, String p_110596_2_) {
/* 61 */     JsonObject jsonobject = null;
/* 62 */     BufferedReader bufferedreader = null;
/*    */     
/*    */     try {
/* 65 */       bufferedreader = new BufferedReader(new InputStreamReader(p_110596_1_, Charsets.UTF_8));
/* 66 */       jsonobject = (new JsonParser()).parse(bufferedreader).getAsJsonObject();
/* 67 */     } catch (RuntimeException runtimeexception) {
/* 68 */       throw new JsonParseException(runtimeexception);
/*    */     } finally {
/* 70 */       IOUtils.closeQuietly(bufferedreader);
/*    */     } 
/*    */     
/* 73 */     return (T)p_110596_0_.parseMetadataSection(p_110596_2_, jsonobject);
/*    */   }
/*    */   
/*    */   public BufferedImage getPackImage() throws IOException {
/* 77 */     return TextureUtil.readBufferedImage(getInputStreamByName("pack.png"));
/*    */   }
/*    */   
/*    */   public String getPackName() {
/* 81 */     return this.resourcePackFile.getName();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\net\minecraft\client\resources\AbstractResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */