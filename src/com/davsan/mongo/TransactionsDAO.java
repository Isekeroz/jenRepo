package com.davsan.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.davsan.dto.SatinAlma;
import com.mongodb.MongoClient;

public class TransactionsDAO extends BasicDAO<SatinAlma, String>
{

	public TransactionsDAO(Morphia morphia, MongoClient mongo, String dbName)
	{
		super(mongo, morphia, dbName);	
	}
}
