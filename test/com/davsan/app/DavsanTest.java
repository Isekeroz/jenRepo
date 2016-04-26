package com.davsan.app;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.davsan.controller.MainController;
import com.davsan.dto.Urun;
import com.davsan.mongo.DavsanDbHelper;

public class DavsanTest 
{
	static MainController controller = MainController.getInstance(); 
	
	@Test
	public void testLogin()
	{
		boolean status = controller.checkLogin("davsan", "admin");
		assertTrue(status);
		controller.logout();
	}
	
	@Test
	public void testGirisYapildiMi()
	{
		controller.checkLogin("davsan", "admin");
		boolean giris = controller.isLoggedIn();
		assertTrue(giris);
		controller.logout();
	}
	
	@Test
	public void testSatisBasladiMi(){
		controller.checkLogin("davsan", "admin");
		controller.satisBaslat();
		
		boolean kontrol = controller.satisBasladiMi();
		assertTrue(kontrol);
		controller.logout();
	}
	
	@Test
	public void testSepetEkle()
	{
		controller.checkLogin("davsan", "admin");
		controller.satisBaslat();
		
		List<Urun> products = DavsanDbHelper.getInstance().getProducts();
		
		controller.sepeteEkle(products.get(0).getProductID());
		controller.sepeteEkle(products.get(2).getProductID());
		
		List<Urun> listSepet = controller.getSepet();
		
		assertEquals(listSepet.size(), 2);
		
		assertEquals(listSepet.get(0).getProductID(), products.get(0).getProductID());
		assertEquals(listSepet.get(1).getProductID(), products.get(2).getProductID());
		
		controller.logout();
	}
	
	@Test
	public void testTutarGoster()
	{
		controller.checkLogin("davsan", "admin");
		controller.satisBaslat();
		
		List<Urun> products = DavsanDbHelper.getInstance().getProducts();
		
		controller.sepeteEkle(products.get(3).getProductID());
		controller.sepeteEkle(products.get(4).getProductID());
		
		List <Urun> sepet = controller.getSepet();
		float fiyat=sepet.get(0).getPrice() + sepet.get(1).getPrice(); 
		float tutar = controller.tutarGoster();
		assertEquals(tutar,fiyat,0.002);
		controller.logout();
	}
	
	@Test
	public void testOdemeYap()
	{
		controller.checkLogin("davsan", "admin");
		controller.satisBaslat();
		
		List<Urun> products = DavsanDbHelper.getInstance().getProducts();
		
		controller.sepeteEkle(products.get(5).getProductID());
		controller.sepeteEkle(products.get(6).getProductID());
		
		Date d = new Date();
		controller.odemeYap("nakit", d , controller.tutarGoster());

		Date odemeTarih = controller.getOdemeTarihi();
		float fiyat = controller.getFiyat();
		String odemeTip = controller.getOdemeTipi();
		
		assertEquals(odemeTarih, d);
		assertEquals(fiyat,controller.tutarGoster(),0.002);
		assertEquals(odemeTip,"nakit");
		
		controller.logout();
	}
	
	@Test
	public void testSatisSonlandir()
	{
		controller.checkLogin("davsan", "admin");
		controller.satisBaslat();
		
		List<Urun> products = DavsanDbHelper.getInstance().getProducts();
		
		controller.sepeteEkle(products.get(1).getProductID());
		controller.sepeteEkle(products.get(2).getProductID());
		
		controller.odemeYap("nakit", new Date() , controller.tutarGoster());
		controller.satisSonlandir();
		
		assertTrue(!controller.satisBasladiMi());
		
		controller.logout();
	
	}	
}















