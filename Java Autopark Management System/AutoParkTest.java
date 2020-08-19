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
		x.setSubscription(sub1); 							//current day 17.05.2019 oldugundan sub geçerli subscription sub2 ise geçersiz.
		y.setSubscription(sub2);
		sub1.setVehicle(x);
		sub2.setVehicle(y);
		oto=new AutoPark(5,3);
	}
	

	@Test
	public void testAddVehicle() {
		assertTrue(oto.addVehicle(x));// abone listesine eklendi.	
		assertFalse(oto.addVehicle(x));// araç zaten abone listesinde.
		assertTrue(oto.addVehicle(y));//abone listesine eklendi.
	}
	
	@Test
	public void testSearchVehicle() {
		oto.addVehicle(x);
		oto.addVehicle(y);
		assertSame(x,oto.searchVehicle(x.getPlate()));//az önce aboneler arasýna eklendi.
		assertNull(oto.searchVehicle("XXXXXXXXXXXXX"));//böyle bir plakaya sahip araç aboneliði yok
	}
	
	@Test
	public void testIsParked() throws inValidDateException{
		System.out.println("TEST IS PARKED BEGIN\n");
		oto.vehicleEnters(x.getPlate(), t1, x.isSpecial());
		assertTrue(oto.isParked(x.getPlate()));//az önce eklenen araç
		assertFalse(oto.isParked("XXXXXXXXX"));//otoparkta olmayan bir araç
		System.out.println("\n*******************************\n");
	}
	
	@Test
	public void testVehicleEnters() throws  inValidDateException{
		System.out.println("TEST VEHICLE ENTERS BEGIN\n");
		assertTrue(oto.addVehicle(x));	//abone olan araçlarýn otoparka abone olarak eklenmesi
		assertTrue(oto.addVehicle(y));	//abone olan araçlarýn otoparka abone olarak eklenmesi
		assertTrue(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertFalse(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertTrue(oto.vehicleEnters(z.getPlate(), t1, z.isSpecial()));
		assertTrue(oto.vehicleEnters(v.getPlate(), t1, v.isSpecial()));//
		assertFalse(oto.vehicleEnters(y.getPlate(), t1, y.isSpecial()));//otopark full kapasite 3 olarak ayarlanmýþtý.
		System.out.println("\n*******************************\n");
	}
	
	@Test
	public void testVehicleExits() throws inValidDateException {
		System.out.println("TEST VEHICLE EXITS BEGIN\n");
		assertFalse(oto.vehicleExits("XXXXXXXXXX", t2));//otoparkta olmayan araç çýkamaz
		assertTrue(oto.addVehicle(x));	//abone olan araçlarýn otoparka abone olarak eklenmesi
		assertTrue(oto.addVehicle(y));	//abone olan araçlarýn otoparka abone olarak eklenmesi
		assertTrue(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertTrue(oto.vehicleEnters(y.getPlate(), t1, y.isSpecial()));
		assertTrue(oto.vehicleEnters(z.getPlate(), t1, z.isSpecial()));
		assertTrue(oto.vehicleExits(x.getPlate(), t2));//subscribed aracýn çýkýþý	
		assertTrue(oto.vehicleExits(z.getPlate(), t2));//regular aracýn çýkýþý
		assertTrue(oto.vehicleExits(y.getPlate(), t3));//invalid subscription a sahip aracýn çýkýþý (regular araç gibi deðerlendirilir.)
		assertEquals(70,oto.getIncomeDaily());// 1 regular araç 5 saatlik ücret + 1 invalid sub araç 9 saat = (9+5)*5=70 
		System.out.println("\n*******************************\n");
	}
	
	@Test 
	public void testVehicleExits2()throws inValidDateException{
		System.out.println("TEST VEHICLE EXITSV2 BEGIN\n");
		assertTrue(oto.addVehicle(x));	//abone olan araçlarýn otoparka abone olarak eklenmesi
		assertTrue(oto.addVehicle(y));	//abone olan araçlarýn otoparka abone olarak eklenmesi
		assertTrue(oto.vehicleEnters(x.getPlate(), t1, x.isSpecial()));
		assertTrue(oto.vehicleEnters(z.getPlate(), t1, z.isSpecial()));
		assertTrue(oto.vehicleEnters(v.getPlate(), t1, v.isSpecial()));
		assertFalse(oto.vehicleEnters(y.getPlate(), t1, y.isSpecial()));//ilk denemede otopark dolu giriþ yapýlamadý
		assertTrue(oto.vehicleExits(x.getPlate(), t2));//subscribed aracýn çýkýþý	
		assertTrue(oto.vehicleExits(z.getPlate(), t2));//regular aracýn çýkýþý
		assertTrue(oto.vehicleExits(v.getPlate(), t3));//offical aracýn çýkýþý
		assertTrue(oto.vehicleEnters(y.getPlate(), t2, y.isSpecial()));//otopark boþ giriþ yapýldý
		assertTrue(oto.vehicleExits(y.getPlate(),t3));
		assertEquals(45,oto.getIncomeDaily());
		assertTrue(oto.vehicleEnters(z.getPlate(), t3, z.isSpecial()));//regular aracýn bugün 2.giriþi
		assertTrue(oto.vehicleExits(z.getPlate(), t4));
		assertEquals(55,oto.getIncomeDaily());
		System.out.println("\n*******************************\n");
	}
	

	

	
}
