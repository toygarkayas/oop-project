package project;

public class Time {
	private int hour,minute;
	
	public Time(int hour,int minute) throws inValidTimeException{
		if(hour > 23 || minute > 59)
			throw new inValidTimeException("Hatali zaman girisi");
		this.hour=hour;
		this.minute=minute;
	}
	
	public int getDifference(Time other) {
		if(this.minute > other.minute)
			return this.hour-other.hour;
		else if(this.minute==other.minute)
			return this.hour-other.hour;
		else
			return this.hour-other.hour-1;
	}
	
	public String toString() {
		return hour+"."+minute;
	}
}
