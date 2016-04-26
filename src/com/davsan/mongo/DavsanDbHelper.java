package com.davsan.mongo;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

import com.davsan.dto.Kullanici;
import com.davsan.dto.SatinAlma;
import com.davsan.dto.Urun;
import com.mongodb.MongoClient;


public class DavsanDbHelper {

	private static DavsanDbHelper instance = null;
	
	private MongoClient client = null;
	private Morphia morphia = null;
	private KullanicilarDAO kullaniciDAO = null;
	private UrunlerDAO urunlerDAO = null;
	private TransactionsDAO transactionsDAO = null;
	private static final String DB_NAME = "DAVSAN_DB";
	
	private DavsanDbHelper(){
		
		client = new MongoClient("192.168.106.129", 27017);
		morphia = new Morphia();
		
		kullaniciDAO = new KullanicilarDAO(morphia, client, DB_NAME);
		urunlerDAO = new UrunlerDAO(morphia, client, DB_NAME);
		transactionsDAO = new TransactionsDAO(morphia, client, DB_NAME);
	}
	
	public static DavsanDbHelper getInstance()
	{
		if(instance ==null)
			instance = new DavsanDbHelper();
		
		return instance;
	}
	
	public boolean save(Kullanici kullanici)
	{
		try
		{
			Query<Kullanici> query = kullaniciDAO.createQuery();

			query.and(query.criteria("id").equal(kullanici.getId()));

			QueryResults<Kullanici> exist = kullaniciDAO.find(query);

			Key<Kullanici> key = exist.getKey();

			if (key != null)
			{
				kullaniciDAO.getDs().merge(kullanici);
				System.err.println("----------------------------------------------- DB - Update Kullanıcı --------------------------------------------------------------------------");
			}
			else
			{
				kullaniciDAO.getDs().save(kullanici);
				System.err.println("----------------------------------------------- DB - Insert Kullanıcı --------------------------------------------------------------------------");
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Kullanici> getUsers()
	{
		try
		{
			Query<Kullanici> query = kullaniciDAO.createQuery();
			return query.asList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public boolean save(Urun urunler)
	{
		try
		{
			Query<Urun> query = urunlerDAO.createQuery();

			query.and(query.criteria("id").equal(urunler.getProductID()));

			QueryResults<Urun> exist = urunlerDAO.find(query);

			Key<Urun> key = exist.getKey();

			if (key != null)
			{
				kullaniciDAO.getDs().merge(urunler);

				System.err.println("----------------------------------------------- DB - Update Urun --------------------------------------------------------------------------");
			}
			else
			{
				urunlerDAO.getDs().save(urunler);
				System.err.println("----------------------------------------------- DB - Insert Urun --------------------------------------------------------------------------");
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Urun> getProducts()
	{
		try
		{
			Query<Urun> query = urunlerDAO.createQuery();
			return query.asList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public boolean save(SatinAlma transaction)
	{
		try
		{
			if(transaction == null)
				return false;
			
			transactionsDAO.getDs().save(transaction);
			System.err.println("----------------------------------------------- DB - Insert Transaction --------------------------------------------------------------------------");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
