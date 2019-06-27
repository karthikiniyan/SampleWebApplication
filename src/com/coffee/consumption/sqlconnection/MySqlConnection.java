package com.coffee.consumption.sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

	public static void main(String[] args)
	{
		
		Connection c = getMySQLConnection();
		System.out.println(c);
	}
	
	public static Connection getMySQLConnection()
	  {
	     String hostName = "remotemysql.com";
	     String dbName = "7br6vZNxbF";
	     String userName = "7br6vZNxbF";
	     String password = "sOjCk6Fbp7";
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
	     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?autoReconnect=true&useSSL=false";
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     return conn;
	 }
}
