package io.netty.channel;

import io.netty.util.ReferenceCounted;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public interface FileRegion extends ReferenceCounted {
  long position();
  
  long transfered();
  
  long count();
  
  long transferTo(WritableByteChannel paramWritableByteChannel, long paramLong) throws IOException;
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4\Butteracking Client v4.jar!\io\netty\channel\FileRegion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */