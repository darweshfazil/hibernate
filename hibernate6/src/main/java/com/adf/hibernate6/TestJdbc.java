package com.adf.hibernate6;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String user = "hbstudent";
		String pass = "Hbstudent12$";
		
		try {
			System.out.println("Connecting to database: "+ jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println(myConn);
			System.out.println("Connection Established Successfully");
		}
		catch(Exception SQLException) {
			System.out.println("Error");
		}
	}
}
