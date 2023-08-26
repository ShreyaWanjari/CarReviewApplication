package com.example.carreview;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs{
	Connection dbConnection;
	public Connection getConnection() throws Exception {
		String ConnectionSTring = "jdbc:mysql://localhost:3306/shreya";
		Class.forName("com.mysql.cj.jdbc.Driver");
		dbConnection = DriverManager.getConnection(ConnectionSTring , "root","123");


		return dbConnection;
	}
}
