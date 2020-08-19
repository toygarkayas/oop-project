package project;

public class ParkRecord {
	private Time enterTime,exitTime=null;
	private Vehicle vehicle;
	
	public ParkRecord(Time enter,Vehicle vehicle) {
		this.enterTime=enter;
		this.vehicle=vehicle;
	}
	public int getParkingDuration() {
		return exitTime.getDifference(enterTime);
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public Time getExit() {
		return exitTime;
	}
	
	public void setExit(Time x) {
		this.exitTime=x;
	}
	
	public String toString() {
		return "Enter time:"+enterTime+"\tExit time:"+exitTime;
	}
}
