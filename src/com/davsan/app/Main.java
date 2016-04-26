package com.davsan.app;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.davsan.config.DavsanConfig;
import com.davsan.controller.MainController;
import com.davsan.dto.Kullanici;
import com.davsan.dto.SatinAlma;
import com.davsan.dto.Urun;

public class Main {

	public static void main(String[] args) 
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(DavsanConfig.class);
		
		MainController controller = context.getBean(MainController.class);
//		
		boolean status = controller.checkLogin("davsan", "admin");
//		
		if(!status)
		{
			System.err.println("Giriş başarısız");
			return;
		}
		else
			System.out.println("Giriş başarılı, iyi eğlnceler :P");
		
		controller.satisBaslat();
		
		List<Urun> urunler = controller.getUrunler();
		
		controller.sepeteEkle(urunler.get(0).getProductID());
		controller.sepeteEkle(urunler.get(2).getProductID());
		controller.sepeteEkle(urunler.get(4).getProductID());
		
		System.out.println(controller.tutarGoster());
		
		controller.odemeYap("nakit", new Date() ,controller.tutarGoster());
		
		controller.satisSonlandir();

		controller.logout();
	}
}
