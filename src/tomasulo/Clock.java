package tomasulo;

import java.time.Instant;

public class Clock {
 static int ticks =0;
public static int getTicks() {
	Instant now = Instant.now();
	if((now.getEpochSecond()%2)==0) 
		ticks++;
	return ticks;
}

}
