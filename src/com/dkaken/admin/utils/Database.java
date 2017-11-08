/**
* Database Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private Connection conn = null;
	
	public Database(String dbname, String url, String username, String password){
		System.out.println("inside database constructor");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url+dbname, username, password);
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return this.conn;
	}
}