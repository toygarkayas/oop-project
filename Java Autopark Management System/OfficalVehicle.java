package project;

public class OfficalVehicle implements Vehicle{
	private String plate;
	
	public OfficalVehicle(String plate) {
		this.plate=plate;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public Subscription getSubscription() {
		return null;
	}
	
	public boolean isSpecial() {
		return true;
	}
	
	public String toString() {
		return plate;
	}
}
