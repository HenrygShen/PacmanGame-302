package group23.pacman.model;

public class Timer {
	
	private int min_ones;
	private int sec_tens;
	private int sec_ones;
	
	private int countValue;
	private int currentTime;
	
	public Timer(int countValue) {
		
		this.countValue = countValue;
		this.currentTime = countValue;
	}
	
	public void resetCounter() {
		
		this.currentTime = countValue;
	}
	
	public void countDown(int increment) {
		
		this.currentTime -= increment;
		
	}
	
	public int getMinOnes() {
		
		this.min_ones = currentTime/60;
		return this.min_ones + 48;
	}
	
	public int getSecTens() {
		
		this.sec_tens = (currentTime%60)/10 ;
		return this.sec_tens + 48;
	}
	
	public int getSecOnes() {
		
		this.sec_ones = (currentTime%10);
		return this.sec_ones + 48;
	}
	
	public void endTimer() {
		currentTime =0;
	}
	
	public boolean timedOut() {
		
		this.min_ones = currentTime/60;
		this.sec_tens = (currentTime%60)/10;
		this.sec_ones = (currentTime%10);
		
		return (min_ones == 0 && sec_tens == 0 && sec_ones == 0);
	}
	
}
