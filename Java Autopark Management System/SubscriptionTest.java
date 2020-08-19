package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SubscriptionTest {

	@Test
	void testIsValid() throws inValidDateException {
		Date d1=new Date(10,5,2019);
		Date d2=new Date(15,5,2019);
		Date d3=new Date(19,5,2019);
		
		Subscription s1=new Subscription(d1,d2);
		Subscription s2=new Subscription(d1,d3);
		
		//MEVCUT TAR�H DATE CLASS INDAN B�T�N OBJELER ���N STAT�K OLARAK 17.05.2019 AYARLANMI�TIR.BU TAR�HTEN SONRAK� TAR�HE SAH�P SUBSCRIPTIONLAR INVALID SAYILIR.
		
		assertTrue(s2.isValid());
		assertFalse(s1.isValid());
		
	}

}
