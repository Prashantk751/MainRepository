package com.database;
import java.sql.*;

public class DB_Conn {

	    private Connection con;
	    
	    public Connection getConnection() throws SQLException, ClassNotFoundException  {
	        Class.forName("com.mysql.jdbc.Driver"); 
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","9716690060"); 
	       // stm=con.createStatement(); 
	        return con;
	    }  
	
}
