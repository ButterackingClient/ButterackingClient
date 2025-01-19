package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public interface ElementProcessor {
  void process(Loader paramLoader, Element paramElement, Diagram paramDiagram, Transform paramTransform) throws ParsingException;
  
  boolean handles(Element paramElement);
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\org\newdawn\slick\svg\inkscape\ElementProcessor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */