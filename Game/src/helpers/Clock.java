package helpers;

import org.lwjgl.Sys;

public class Clock {
	public static boolean paused = false;
	public static long lastFrame , totalTime;
	public static float d = 0 , multiplier = 1;
	
	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public static float getDelta() {
		long currenttime = getTime();
		int delta = (int) (currenttime - lastFrame);
		lastFrame = getTime();
		if(delta * 0.01f > 0.5f) {
			return 0.5f;
		}
		return delta * 0.01f;
	}
	
	public static float Delta() {
		if(paused)	return 0;
		else return d * multiplier;
	}
	
	public static float totalTime() {
		return totalTime;
	}
	
	public static float Multiplier() {
		return multiplier;
	}
	
	
	public static void update() {   
		d = getDelta();
		totalTime += d;
		
	}
	
	
	public static void changeMultiplier(float change) {
		if(multiplier + change < -1 && multiplier + change > 7) {
			
		}	else {
			multiplier += change;
		}
	}
	
	public static void Pause() {
		if(paused)	paused = false;
		else paused = true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
