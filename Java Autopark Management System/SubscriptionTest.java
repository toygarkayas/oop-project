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
		
		//MEVCUT TARÝH DATE CLASS INDAN BÜTÜN OBJELER ÝÇÝN STATÝK OLARAK 17.05.2019 AYARLANMIÞTIR.BU TARÝHTEN SONRAKÝ TARÝHE SAHÝP SUBSCRIPTIONLAR INVALID SAYILIR.
		
		assertTrue(s2.isValid());
		assertFalse(s1.isValid());
		
	}

}
