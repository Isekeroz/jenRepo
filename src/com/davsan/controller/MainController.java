package com.davsan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import com.davsan.dto.Kullanici;
import com.davsan.dto.SatinAlma;
import com.davsan.dto.Urun;
import com.davsan.mongo.DavsanDbHelper;

public class MainController
{
	private static MainController _instance = null;
	private List<Kullanici> users = null;
	private List<Urun> productList = new ArrayList<Urun>();
	
	private Kullanici loginUser = null;
	private SatinAlma transaction = null;
	
	private MainController() 
	{
		users = DavsanDbHelper.getInstance().getUsers();
		productList = DavsanDbHelper.getInstance().getProducts();
	}
	
	public static MainController getInstance()
	{
		if(_instance == null)
			_instance = new MainController();
		
		return _instance;
	}
	
	public boolean checkLogin(String userName, String password) 
	{
		if(userName == null || password == null || userName.isEmpty() || password.isEmpty())
			return false;
		
		for(Kullanici k : users)
		{
			if(k.getUserName().equals(userName) && k.getPassword().equals(password))
			{
				loginUser = k;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean satisBasladiMi()
	{
		return (transaction != null);
	}
	
	public void satisBaslat()
	{
		if(loginUser != null)
			transaction = new SatinAlma(loginUser);
		else
			System.err.println("Lütfen giriş yapınız");
	}
	
	public boolean isLoggedIn()
	{
		return (loginUser != null);
	}
	
	public void logout()
	{
		loginUser = null;
		transaction = null;
		
		System.out.println("Güvenli çıkış yapıldı");
	}
	
	public void sepeteEkle(ObjectId productID)
	{
		if(transaction != null)
		{
			for(Urun u : productList)
			{
				if(u.getProductID().equals(productID))
				{	
					transaction.urunEkle(u);
					System.out.println("'" + u.getProductName() + "' sepete eklendi");
					return;
				}
			}
		}
		else
			System.err.println("Lütfen yeni satış başlatınız");
	}
	
	public float tutarGoster()
	{
		if(transaction != null)
			return transaction.getTutar();
		else
			System.err.println("Devam eden bir satış olmalı");

		return 0;
	}
	
	public void odemeYap(String ot, Date tarih, float tutar)
	{
		if(transaction != null)
		{
			transaction.ode(ot, tarih, tutar);
			System.out.println("Ödeme alındı");
		}
		else
			System.err.println("Devam eden bir satış olmalı");
	}
	
	public void satisSonlandir()
	{
		if(transaction != null)
		{
			DavsanDbHelper.getInstance().save(transaction);
			transaction = null;
			System.out.println("Satış sonlandırıldı");
		}
		else
			System.out.println("Devam eden bir satış olmalı");
	}

	public List<Urun> getSepet()
	{
		if(transaction != null)
			return transaction.getSelectedPro();
		else
			System.err.println("Devam eden bir satış olmalı");
		
		return null;
	}

	public String getOdemeTipi()
	{
		if(transaction != null)
			return transaction.getPayment().getOdemeTipi();
		else
			System.err.println("Devam eden bir satış olmalı");
		
		return null;
	}
	
	public float getFiyat()
	{
		if(transaction != null)
			return transaction.getPayment().getUcret();
		else
			System.err.println("Devam eden bir satış olmalı");
		
		return 0;
	}
	
	public Date getOdemeTarihi()
	{
		if(transaction != null)
			return transaction.getPayment().getTarih();
		else
			System.err.println("Devam eden bir satış olmalı");
		
		return null;
	}
	
	public List<Urun> getUrunler()
	{
		if(loginUser != null)
			return productList;
		else
			return null;
	}
}





























