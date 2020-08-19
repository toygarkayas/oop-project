package project;

public class SubscribedVehicle implements Vehicle{
	private String plate;
	private Subscription membership;
	
	public SubscribedVehicle(String plate) {
		this.plate=plate;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public Subscription getSubscription() {
		return membership;
	}
	
	public boolean isSpecial() {
		return false;
	}
	
	public void setSubscription(Subscription s) {
		this.membership=s;
	}
	
	public String toString() {
		return plate;
	}
}
