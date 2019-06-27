package com.coffee.consumption.sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;  
import java.io.*;  

public class MySqlConnection {
	
	static String hostName ;
	static String dbName ;
	static String userName ;
	static String port ;
	static String password ;

	static
	{
		try
		{
			FileReader reader=new FileReader("..//webapps//CoffeeConsumption//programmeurs.properties");  
			Properties p=new Properties();  
			p.load(reader); 
			hostName = p.getProperty("hostName");
			dbName = p.getProperty("dbName");
			userName = p.getProperty("userName");
			port = p.getProperty("port");
			password = p.getProperty("password");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		
		Connection c = getMySQLConnection();
		System.out.println(c);
	}
	
	public static Connection getMySQLConnection()
	  {
	     
	     try {
			return getMySQLConnection(hostName, dbName, userName, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	  
	 public static Connection getMySQLConnection(String hostName, String dbName,
	         String userName, String password) throws SQLException,
	         ClassNotFoundException {
	    
	     Class.forName("com.mysql.jdbc.Driver");
	     String connectionURL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName+"?autoReconnect=true&useSSL=false";
		 System.out.println(connectionURL);
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     return conn;
	 }
}
