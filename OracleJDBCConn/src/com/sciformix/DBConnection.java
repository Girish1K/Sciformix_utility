package com.sciformix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static Connection con=null;
	
	public boolean getConnection(){
		boolean flag=false;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","root");				
			flag=con.isValid(15);
			
		}catch(Exception e){
			System.out.println("Error in making DB connection");
			e.printStackTrace();
		}
		return flag;
	}
	
	public ResultSet executeQuery(String query) throws SQLException{
		Statement statement = null;
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet=statement.executeQuery(query);
		return resultSet;
	}
	
	public void closeConnection(){		
		try{
			con.close();			
		}catch (Exception e) {
			System.out.println("Error in closing the connection");
			e.printStackTrace();
		}				
	}
}
