package com.coedelsur.database.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class ManagerDB {
	
    
    private static String DB_PROVIDER = "__?????___"; //aqui la conexion
    private static String DB_NAME =  "__?????___"; //aqui la conexion
    private static String DB_PASSWORD =  "__?????___";//aqui la conexion
    
	public static Connection getDBConection() throws Exception {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(DB_PROVIDER, DB_NAME, DB_PASSWORD);
	}

	
}
