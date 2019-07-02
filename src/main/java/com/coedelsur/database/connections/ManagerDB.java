package com.coedelsur.database.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class ManagerDB {
	
    
    private static String DB_PROVIDER = "jdbc:postgresql://localhost:5432/gonzaf21_coedelsur";
    private static String DB_NAME = "gonzaf21_coedelsur";
    private static String DB_PASSWORD = "gon11gon";
    
    //private static String DB_PROVIDER = "jdbc:postgresql://qdjjtnkv.db.elephantsql.com:5432/lajisaul";
    //private static String DB_NAME = "lajisaul";
    //private static String DB_PASSWORD = "njdWJDkVae2pUYiwuj4KVcxGbjMCD71o";

	
	public static Connection getDBConection() throws Exception {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(DB_PROVIDER, DB_NAME, DB_PASSWORD);
	}

	
}
