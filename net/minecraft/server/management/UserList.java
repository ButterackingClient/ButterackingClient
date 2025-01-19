/*     */ package net.minecraft.server.management;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserList<K, V extends UserListEntry<K>>
/*     */ {
/*  32 */   protected static final Logger logger = LogManager.getLogger();
/*     */   protected final Gson gson;
/*     */   private final File saveFile;
/*  35 */   private final Map<String, V> values = Maps.newHashMap();
/*     */   
/*  37 */   private static final ParameterizedType saveFileFormat = new ParameterizedType() {
/*     */       public Type[] getActualTypeArguments() {
/*  39 */         return new Type[] { UserListEntry.class };
/*     */       }
/*     */       
/*     */       public Type getRawType() {
/*  43 */         return List.class;
/*     */       }
/*     */       
/*     */       public Type getOwnerType() {
/*  47 */         return null;
/*     */       }
/*     */     };
/*     */   private boolean lanServer = true;
/*     */   public UserList(File saveFile) {
/*  52 */     this.saveFile = saveFile;
/*  53 */     GsonBuilder gsonbuilder = (new GsonBuilder()).setPrettyPrinting();
/*  54 */     gsonbuilder.registerTypeHierarchyAdapter(UserListEntry.class, new Serializer(null));
/*  55 */     this.gson = gsonbuilder.create();
/*     */   }
/*     */   
/*     */   public boolean isLanServer() {
/*  59 */     return this.lanServer;
/*     */   }
/*     */   
/*     */   public void setLanServer(boolean state) {
/*  63 */     this.lanServer = state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntry(V entry) {
/*  70 */     this.values.put(getObjectKey(entry.getValue()), entry);
/*     */     
/*     */     try {
/*  73 */       writeChanges();
/*  74 */     } catch (IOException ioexception) {
/*  75 */       logger.warn("Could not save the list after adding a user.", ioexception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public V getEntry(K obj) {
/*  80 */     removeExpired();
/*  81 */     return this.values.get(getObjectKey(obj));
/*     */   }
/*     */   
/*     */   public void removeEntry(K entry) {
/*  85 */     this.values.remove(getObjectKey(entry));
/*     */     
/*     */     try {
/*  88 */       writeChanges();
/*  89 */     } catch (IOException ioexception) {
/*  90 */       logger.warn("Could not save the list after removing a user.", ioexception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getKeys() {
/*  95 */     return (String[])this.values.keySet().toArray((Object[])new String[this.values.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getObjectKey(K obj) {
/* 102 */     return obj.toString();
/*     */   }
/*     */   
/*     */   protected boolean hasEntry(K entry) {
/* 106 */     return this.values.containsKey(getObjectKey(entry));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeExpired() {
/* 113 */     List<K> list = Lists.newArrayList();
/*     */     
/* 115 */     for (UserListEntry<K> userListEntry : this.values.values()) {
/* 116 */       if (userListEntry.hasBanExpired()) {
/* 117 */         list.add(userListEntry.getValue());
/*     */       }
/*     */     } 
/*     */     
/* 121 */     for (K k : list) {
/* 122 */       this.values.remove(k);
/*     */     }
/*     */   }
/*     */   
/*     */   protected UserListEntry<K> createEntry(JsonObject entryData) {
/* 127 */     return new UserListEntry<>(null, entryData);
/*     */   }
/*     */   
/*     */   protected Map<String, V> getValues() {
/* 131 */     return this.values;
/*     */   }
/*     */   
/*     */   public void writeChanges() throws IOException {
/* 135 */     Collection<V> collection = this.values.values();
/* 136 */     String s = this.gson.toJson(collection);
/* 137 */     BufferedWriter bufferedwriter = null;
/*     */     
/*     */     try {
/* 140 */       bufferedwriter = Files.newWriter(this.saveFile, Charsets.UTF_8);
/* 141 */       bufferedwriter.write(s);
/*     */     } finally {
/* 143 */       IOUtils.closeQuietly(bufferedwriter);
/*     */     } 
/*     */   }
/*     */   
/*     */   class Serializer
/*     */     implements JsonDeserializer<UserListEntry<K>>, JsonSerializer<UserListEntry<K>> {
/*     */     private Serializer() {}
/*     */     
/*     */     public JsonElement serialize(UserListEntry<K> p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 152 */       JsonObject jsonobject = new JsonObject();
/* 153 */       p_serialize_1_.onSerialization(jsonobject);
/* 154 */       return (JsonElement)jsonobject;
/*     */     }
/*     */     
/*     */     public UserListEntry<K> deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
/* 158 */       if (p_deserialize_1_.isJsonObject()) {
/* 159 */         JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
/* 160 */         UserListEntry<K> userlistentry = UserList.this.createEntry(jsonobject);
/* 161 */         return userlistentry;
/*     */       } 
/* 163 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraft\server\management\UserList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */