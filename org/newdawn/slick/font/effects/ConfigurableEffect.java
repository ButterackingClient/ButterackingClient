package org.newdawn.slick.font.effects;

import java.util.List;

public interface ConfigurableEffect extends Effect {
  List getValues();
  
  void setValues(List paramList);
  
  public static interface Value {
    String getName();
    
    void setString(String param1String);
    
    String getString();
    
    Object getObject();
    
    void showDialog();
  }
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\org\newdawn\slick\font\effects\ConfigurableEffect.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */