package io.netty.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface Timer {
  Timeout newTimeout(TimerTask paramTimerTask, long paramLong, TimeUnit paramTimeUnit);
  
  Set<Timeout> stop();
}


/* Location:              C:\User\\user\Desktop\Butteracking Client v4.1.jar!\io\nett\\util\Timer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */