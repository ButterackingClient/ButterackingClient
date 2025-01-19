package net.minecraft.util;

public interface IRegistry<K, V> extends Iterable<V> {
  V getObject(K paramK);
  
  void putObject(K paramK, V paramV);
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\net\minecraf\\util\IRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */