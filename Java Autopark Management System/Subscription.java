package project;

public class Subscription {
	private Date begin,end;
	private SubscribedVehicle vehicle;
	
	public Subscription(Date begin,Date end) {
		this.begin=begin;
		this.end=end;
	}
	
	public boolean isValid() throws inValidDateException{
		Date today=new Date();
		today=today.getToday();
		if(begin.isEqualsWith(end)|| begin.isAfterThan(end))
			return false;
		return today.isBeforeThan(end);
	}
	
	public void setVehicle(SubscribedVehicle s) {
		this.vehicle=s;
	}

	public Date getBegin() {
		return begin;
	}

	public Date getEnd() {
		return end;
	}

	public SubscribedVehicle getVehicle() {
		return vehicle;
	}
}
