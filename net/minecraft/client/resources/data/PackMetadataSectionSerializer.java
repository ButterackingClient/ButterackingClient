/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ 
/*    */ public class PackMetadataSectionSerializer
/*    */   extends BaseMetadataSectionSerializer<PackMetadataSection>
/*    */   implements JsonSerializer<PackMetadataSection> {
/*    */   public PackMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
/* 17 */     JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
/* 18 */     IChatComponent ichatcomponent = (IChatComponent)p_deserialize_3_.deserialize(jsonobject.get("description"), IChatComponent.class);
/*    */     
/* 20 */     if (ichatcomponent == null) {
/* 21 */       throw new JsonParseException("Invalid/missing description!");
/*    */     }
/* 23 */     int i = JsonUtils.getInt(jsonobject, "pack_format");
/* 24 */     return new PackMetadataSection(ichatcomponent, i);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement serialize(PackMetadataSection p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 29 */     JsonObject jsonobject = new JsonObject();
/* 30 */     jsonobject.addProperty("pack_format", Integer.valueOf(p_serialize_1_.getPackFormat()));
/* 31 */     jsonobject.add("description", p_serialize_3_.serialize(p_serialize_1_.getPackDescription()));
/* 32 */     return (JsonElement)jsonobject;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 39 */     return "pack";
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\client\resources\data\PackMetadataSectionSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */