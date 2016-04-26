package com.davsan.mongo;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.davsan.dto.Kullanici;
import com.mongodb.MongoClient;

public class KullanicilarDAO extends BasicDAO<Kullanici, String> 
{
	public KullanicilarDAO(Morphia morphia, MongoClient mongo, String dbName)
	{
		super(mongo, morphia, dbName);
	}
}
