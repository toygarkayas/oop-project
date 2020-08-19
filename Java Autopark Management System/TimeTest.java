package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeTest {

	@Test
	public void testGetDiffrence() throws inValidTimeException {
		Time t1=new Time(10,30);
		Time t2=new Time(15,30);
		Time t3=new Time(20,20);
		assertEquals(5,t2.getDifference(t1));//5 saat kalan bir araç 5 saatlik ücret öder
		assertEquals(4,t3.getDifference(t2));//4 saat 50 dakika kalan bir araç 4 saatlik ücret öder
		assertEquals(9,t3.getDifference(t1));//9 saat 50 dakika kalan bir araç 9 saatlik ücret öder
	}

}
