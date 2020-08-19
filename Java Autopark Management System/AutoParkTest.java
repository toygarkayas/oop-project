package project;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoParkTest {
	private AutoPark oto;
	private Date d1, d2, d3;
	private Subscription sub1, sub2;
	private Time t1, t2, t3,t4;
	private SubscribedVehicle x,y;
	private RegularVehicle z;
	private OfficalVehicle v;
	
	@BeforeEach
	public void setUp() throws inValidTimeException,inValidDateException {
		t1=new Time(10,30);
		t2=new Time(15,30);
		t3=new Time(20,20);
		t4=new Time(22,30);
		d1=new Date(10,5,2019);
		d2=new Date(18,5,2019);
		d3=new Date(16,5,2019);
		sub1=new Subscription(d1,d2);
		sub2=new Subscription(d1,d3);
		x=new SubscribedVehicle("34 TG 4928");
		y=new SubscribedVehicle("34 AF 4928");
		z=new RegularVehicle("22 AA 2222");
		v=new OfficalVehicle("POLIS 22 POLIS");
		x.setSubscription(sub1); 							//current day 17.05.2019 oldugundan sub ge�erli subscription sub2 ise ge�ersiz.
		y.setSubscription(sub2);
		sub1.setVehicle(x);
		sub2.setVehicle(y);
		oto=new AutoPark(5,3);
	}
	

	@Test
	public void testAddVehicle() {
		assertTrue(oto.addVehicle(x));// abone listesine eklendi.	
		assertFalse(oto.addVehicle(x));// ara� zaten abone listesinde.
		assertTrue(oto.addVehicle(y));//abone listesine eklendi.
	}
	
	@Test
	public void testSearchVehicle() {
		oto.addVehicle(x);
		oto.addVehicle(y);
		assertSame(x,oto.searchVehicle(x.getPlate()));//az �nce aboneler aras�na eklendi.
		assertNull(oto.searchVehicle("XXXXXXXXXXXXX"));//b�yle bir plakaya sahip ara� aboneli�i yok
	}
	
	@Test
	public void testIsParked() throws inValidDateException{
		System.out.println("TEST IS PARKED BEGIN\n");
		oto.vehicleEnters(x.getPlate(), t1, x.isSpecial());
		assertTrue(oto.isParked(x.getPlate()));//az �nce eklenen ara�
		assertFalse(oto.isParked("XXXXXXXXX"));//otoparkta olmayan bir ara�
		System.out.println("\n*******************************\n");
	}
	
	@Test
	public void testVehicleEnters() throws  inValidDateException{
		System.out.println("TEST VEHICLE ENTERS BEGIN\n");
		assertTrue(oto.addVehicle(x));	//abone olan ara�lar�n otoparka abone olarak eklenmesi
		assertTrue(oto.addVehicle(y));	//abone olan ara�lar�n otoparka abone olarak eklenmesi
		assertTrue(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertFalse(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertTrue(oto.vehicleEnters(z.getPlate(), t1, z.isSpecial()));
		assertTrue(oto.vehicleEnters(v.getPlate(), t1, v.isSpecial()));//
		assertFalse(oto.vehicleEnters(y.getPlate(), t1, y.isSpecial()));//otopark full kapasite 3 olarak ayarlanm��t�.
		System.out.println("\n*******************************\n");
	}
	
	@Test
	public void testVehicleExits() throws inValidDateException {
		System.out.println("TEST VEHICLE EXITS BEGIN\n");
		assertFalse(oto.vehicleExits("XXXXXXXXXX", t2));//otoparkta olmayan ara� ��kamaz
		assertTrue(oto.addVehicle(x));	//abone olan ara�lar�n otoparka abone olarak eklenmesi
		assertTrue(oto.addVehicle(y));	//abone olan ara�lar�n otoparka abone olarak eklenmesi
		assertTrue(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertTrue(oto.vehicleEnters(y.getPlate(), t1, y.isSpecial()));
		assertTrue(oto.vehicleEnters(z.getPlate(), t1, z.isSpecial()));
		assertTrue(oto.vehicleExits(x.getPlate(), t2));//subscribed arac�n ��k���	
		assertTrue(oto.vehicleExits(z.getPlate(), t2));//regular arac�n ��k���
		assertTrue(oto.vehicleExits(y.getPlate(), t3));//invalid subscription a sahip arac�n ��k��� (regular ara� gibi de�erlendirilir.)
		assertEquals(70,oto.getIncomeDaily());// 1 regular ara� 5 saatlik �cret + 1 invalid sub ara� 9 saat = (9+5)*5=70 
		System.out.println("\n*******************************\n");
	}
	
	@Test 
	public void testVehicleExits2()throws inValidDateException{
		System.out.println("TEST VEHICLE EXITSV2 BEGIN\n");
		assertTrue(oto.addVehicle(x));	//abone olan ara�lar�n otoparka abone olarak eklenmesi
		assertTrue(oto.addVehicle(y));	//abone olan ara�lar�n otoparka abone olarak eklenmesi
		assertTrue(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertTrue(oto.vehicleEnters(z.getPlate(), t1, z.isSpecial()));
		assertTrue(oto.vehicleEnters(v.getPlate(), t1, v.isSpecial()));
		assertFalse(oto.vehicleEnters(y.getPlate(), t1, y.isSpecial()));//ilk denemede otopark dolu giri� yap�lamad�
		assertTrue(oto.vehicleExits(x.getPlate(), t2));//subscribed arac�n ��k���	
		assertTrue(oto.vehicleExits(z.getPlate(), t2));//regular arac�n ��k���
		assertTrue(oto.vehicleExits(v.getPlate(), t3));//offical arac�n ��k���
		assertTrue(oto.vehicleEnters(y.getPlate(), t2, y.isSpecial()));//otopark bo� giri� yap�ld�
		assertTrue(oto.vehicleExits(y.getPlate(),t3));
		assertEquals(45,oto.getIncomeDaily());
		assertTrue(oto.vehicleEnters(z.getPlate(), t3, z.isSpecial()));//regular arac�n bug�n 2.giri�i
		assertTrue(oto.vehicleExits(z.getPlate(), t4));
		assertEquals(55,oto.getIncomeDaily());
		System.out.println("\n*******************************\n");
	}
	

	

	
}
