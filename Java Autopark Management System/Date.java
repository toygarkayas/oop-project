package project;

public class Date {
	private int day,month,year;
	private static int currentDay=17,currentMonth=5,currentYear=2019;
	
	public Date(int day,int month,int year) throws inValidDateException{
		if(day > 31 || month > 12)
			throw new inValidDateException("Hatali tarih girisi");
		this.day=day;
		this.month=month;
		this.year=year;
	}
	
	public Date() {
	}
	
	public boolean isAfterThan(Date other) {
		if(other.year == this.year) {
			if(other.month == this.month) {
				if(this.day > other.day)
					return true;
				else
					return false;
			}
			else if(this.month > other.month)
				return true;
			else
				return false;
		}
		else if(this.year > other.year)
			return true;
		else
			return false;
	}
	
	public boolean isEqualsWith(Date other) {
		if(this.day==other.day && this.month==other.month && this.year==other.year)
			return true;
		return false;
	}
	
	public boolean isBeforeThan(Date other) {
		return !(this.isAfterThan(other)) && !(this.isEqualsWith(other));
	}
	
	public Date getToday() throws inValidDateException {
		Date d=new Date(currentDay,currentMonth,currentYear);
		return d;
	}
	
	public String toString() {
		return this.day+"."+this.month+"."+this.year;
	}
}
