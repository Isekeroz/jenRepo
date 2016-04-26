package com.davsan.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.davsan.dto.Urun;
import com.mongodb.MongoClient;

public class UrunlerDAO extends BasicDAO<Urun, String>
{

	public UrunlerDAO(Morphia morphia, MongoClient mongo, String dbName)
	{
		super(mongo, morphia, dbName);
	}
}
