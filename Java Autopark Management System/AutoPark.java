package project;

import java.util.ArrayList;

public class AutoPark {
	private ArrayList<SubscribedVehicle> subscribedVehicles;
	private ArrayList<ParkRecord> parkRecords;
	private Vehicle[] vehicles;
	private double hourlyFee,incomeDaily=0;
	private final int capacity;
	
	public AutoPark(double hourlyFee,int capacity) {
		this.hourlyFee=hourlyFee;
		this.capacity=capacity;
		this.parkRecords=new ArrayList<ParkRecord>();
		this.subscribedVehicles=new ArrayList<SubscribedVehicle>();
		this.vehicles=new Vehicle[capacity];
	}
	
	public SubscribedVehicle searchVehicle(String plate) {
		for(SubscribedVehicle x:subscribedVehicles) {
			if(x!=null) {
				if(x.getPlate()==plate)
					return x;				
			}
		}
		return null;
	}
	
	public boolean isParked(String plate) {
		for(int i=0;i<capacity;i++) {
			if(vehicles[i]!=null) {
				if(vehicles[i].getPlate().compareTo(plate)==0)
					return true;				
			}
		}
		return false;
	}
	
	public boolean addVehicle(SubscribedVehicle x) {
		if(x!=null) {
			if(this.searchVehicle(x.getPlate())==null) {
				subscribedVehicles.add(x);
				return true;
			}
		}
		return false;
	}
	
	public boolean vehicleEnters(String plate,Time enter,boolean isOffical) throws inValidDateException {
		
		if(!isParked(plate)) {
			if(this.searchVehicle(plate)!=null) {
				if(this.searchVehicle(plate).getSubscription().isValid()) {
					if(aracEkle(this.searchVehicle(plate))) {
						parkRecords.add(new ParkRecord(enter,this.searchVehicle(plate)));
						System.out.println("Subscribed vehicle " +this.searchVehicle(plate)+" enters the autopark.");
						return true;						
					}
					else
						return false;
				}
				else {
					if(aracEkle(this.searchVehicle(plate))) {
						parkRecords.add(new ParkRecord(enter,this.searchVehicle(plate)));
						System.out.println("Subscribed vehicle "+this.searchVehicle(plate)+" enters the autopark.But subscription is invalid.So vehicle will have to pay fee. Hourly fee:"+ this.hourlyFee);
						return true;				
					}
					else
						return false;
				}

			}
			else if(isOffical){
				OfficalVehicle o=new OfficalVehicle(plate);
				if(aracEkle(o)) {
					System.out.println("Offical vehicle " +plate+" enters the autopark.");
					return true;
				}
				else
					return false;
			}
			else {
				RegularVehicle r=new RegularVehicle(plate);
				if(aracEkle(r)) {
					parkRecords.add(new ParkRecord(enter,r));
					System.out.println("Regular vehicle " +plate+" enters the autopark.\tHourly fee:"+hourlyFee);
					return true;				
				}
				else
					return false;
			}
		}
		return false;
	}
	
	public boolean vehicleExits(String plate,Time exit) throws inValidDateException {
		if(this.isParked(plate)) {
			for(ParkRecord x:parkRecords) {
				if(x.getVehicle().getPlate()==plate && this.searchVehicle(plate)==null && x.getExit()==null) {
					x.setExit(exit);
					System.out.println(x+"\nYou are not subscribed so you have to pay fee.\tFee:"+x.getParkingDuration()*hourlyFee);
					incomeDaily+=x.getParkingDuration()*hourlyFee;
					this.aracCikar(plate);
					return true;
				}
				else if(this.searchVehicle(plate)!=null  && x.getExit()==null) {
					x.setExit(exit);
					if(this.searchVehicle(plate).getSubscription().isValid()) {
						System.out.println(x+"\nSubscribed vehicle "+this.searchVehicle(plate)+" exits.Have a good day."+"\tYour subscription ends on "+this.searchVehicle(plate).getSubscription().getEnd());
						this.aracCikar(plate);
						return true;
					}
					else {
							System.out.println(x+"\nInvalid subscription for vehicle "+this.searchVehicle(plate)+".So you have to pay fee.\tFee:"+x.getParkingDuration()*hourlyFee);
							incomeDaily+=x.getParkingDuration()*hourlyFee;
							this.aracCikar(plate);
							return true;						
					}
				}
			}
			if(this.searchVehicle(plate)==null) {
				System.out.println("Offical Vehicle "+plate+" exits.Have a good day.");
				this.aracCikar(plate);
				return true;
			}
		}
		System.out.println("Car is not in AutoPark");
		return false;
	}
	
	public void listVehicles() {
		System.out.println("\nPrinting cars in autopark..\n");
		for(int i=0;i<capacity;i++) {
			if(vehicles[i]!=null)
				System.out.println(vehicles[i].getPlate());
		}
		System.out.println("\n");
	}
	
	public boolean aracEkle(Vehicle x) {
		for(int i=0;i<capacity;i++) {
			if(vehicles[i]==null) {
				vehicles[i]=x;
				return true;
			}
		}
		System.out.println("AutoPark is full.");
		return false;
	}
	
	
	public boolean aracCikar(String plate) {
		for(int i=0;i<capacity;i++) {
			if(vehicles[i]!=null) {
				if(vehicles[i].getPlate()==plate) {
					vehicles[i]=null;
					return true;
				}			
			}
		}
		return false;
	}
	
	public void printIncome() {
		System.out.println("Daily income is:"+this.incomeDaily);
	}
	
	public double getIncomeDaily() {
		return incomeDaily;
	}
}
