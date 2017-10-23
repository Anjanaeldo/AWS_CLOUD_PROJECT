package com.anjana.controllers;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class connectionRDS {

	public static void main(String[] args) throws Exception{

	
	getConnection();
	}
	
	public static Connection getConnection() throws Exception{
		try{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://dbinstance.cdamcjojbqhe.us-east-1.rds.amazonaws.com:3306/CloudDB";
		String username = "";//Provide username
		String password = "";//provide password
		Class.forName(driver); 
		Connection conn = (Connection) DriverManager.getConnection(url,username,password);
		System.out.println("Connected");
		return conn;
		} catch(Exception e){System.out.println(e);} return null;
		}
	
}
