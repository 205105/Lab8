package it.polito.tdp.metroparis.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

private static final String jdbcURL="jdbc:mysql://localhost/metroparis?user=root";
	
	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			return conn;
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("errore nella connesione", e);
	}
}
}
