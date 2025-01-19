package org.newdawn.slick;

public interface Game {
  void init(GameContainer paramGameContainer) throws SlickException;
  
  void update(GameContainer paramGameContainer, int paramInt) throws SlickException;
  
  void render(GameContainer paramGameContainer, Graphics paramGraphics) throws SlickException;
  
  boolean closeRequested();
  
  String getTitle();
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\org\newdawn\slick\Game.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */