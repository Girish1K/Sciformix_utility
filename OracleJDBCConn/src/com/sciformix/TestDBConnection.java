package com.sciformix;

import java.sql.ResultSet;

public class TestDBConnection {

	public static void main(String[] args) {
			DBConnection dbConnection=new DBConnection();
			try{			
			/*	Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection(  
						"jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","root");				
				boolean flag=con.isValid(15);
				System.out.println("Connection flag-->> "+flag);
				
				Statement stmt=con.createStatement();  			
				//stmt.executeQuery("create table employee(id int not null, name varchar(100),primary key(id))");
				stmt.executeQuery("insert into employee (id, name) values (1,'girish')");
				stmt.executeQuery("insert into employee (id, name) values (2,'girish11')");
				stmt.executeQuery("insert into employee (id, name) values (3,'girish111')");
				ResultSet resultSet=stmt.executeQuery("select *from employee");
				System.out.println(resultSet.getRow());			
				while(resultSet.next()){
					//System.out.println("hiiiiiiiii");
					System.out.println("empID  empName\n"+resultSet.getInt(1)+"      "+resultSet.getString(2));
				}			
			*/
				
				boolean con=dbConnection.getConnection();
				//ResultSet resultSet=dbConnection.executeQuery("insert into employee (id, name) values (4,'nagesh')");
				ResultSet resultSet=dbConnection.executeQuery("select *from employee");
				while(resultSet.next()){
					//System.out.println("hiiiiiiiii");
					System.out.println("empID  empName\n"+resultSet.getInt(1)+"      "+resultSet.getString(2));
				}
				
				
				
			//	System.out.println(con);
			}catch(Exception e){
				
			}
	}

}
