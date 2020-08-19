package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParkRecordTest {

	@Test
	public void testParkRecord() throws inValidTimeException {
		Time t1=new Time(10,15);
		Time t2=new Time(14,14);
		RegularVehicle r=new RegularVehicle("34 AF 34");
		ParkRecord p=new ParkRecord(t1,r);
		p.setExit(t2);
		assertEquals(3,p.getParkingDuration());
	}

}
