package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTest {

	private Date d1,d2,d3,d4,d5;
	@BeforeEach
	void setUp() throws inValidDateException {
		d1=new Date(16,05,2019);
		d2=new Date(17,05,2019);
		d3=new Date(17,05,2010);
		d4=new Date(17,05,2019);
		d5=new Date(11,12,2020);
	}

	@Test
	void testIsAfterThan() {
		assertTrue(d2.isAfterThan(d1));
		assertTrue(d5.isAfterThan(d4));
		assertFalse(d3.isAfterThan(d1));
		assertFalse(d1.isAfterThan(d4));
	}
	
	@Test
	void testIsBeforeThan() {
		assertFalse(d2.isBeforeThan(d1));
		assertFalse(d5.isBeforeThan(d4));
		assertTrue(d3.isBeforeThan(d1));
		assertTrue(d1.isBeforeThan(d4));
	}

	@Test
	void testIsEquals() {
		assertTrue(d2.isEqualsWith(d4));
		assertFalse(d2.isEqualsWith(d1));
	}
	
	@Test
	void testGetToday() throws inValidDateException {
		assertTrue(d2.isEqualsWith(d2.getToday()));
		assertTrue(d2.isEqualsWith(d4.getToday()));
		assertFalse(d1.isEqualsWith(d2.getToday()));
		
	}
}
