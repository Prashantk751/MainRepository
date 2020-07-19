package com.employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

	public static Connection getConnection(){
		Connection con=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/user","root","9716690060");
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return con;
	}
	
	public static int save(Emp e)
	{
		int status = 0;
	    try{
		   Connection con = EmpDao.getConnection();
		   PreparedStatement st = con.prepareStatement("insert into user905(uname,pass,email,country) values(?,?,?,?)");
		   st.setString(1, e.getName());
		   st.setString(2,e.getPassword());
		   st.setString(3, e.getEmail());
		   st.setString(4, e.getCountry());
		
		   status = st.executeUpdate();
		   con.close();
	  }catch(Exception ex){
			ex.printStackTrace();
	       }
	
	return status;

	}
	
	public static Emp getEmployeeById(int id)
	{
		Emp e = new Emp();
		
		try{
			Connection con = EmpDao.getConnection();
			PreparedStatement st = con.prepareStatement("select * from user905 where id=?");
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return e;
	}
	
	public static int update(Emp e)
	{
		int status = 0;
		try{
			Connection con = EmpDao.getConnection();
			PreparedStatement st = con.prepareStatement("update user905 set uname=?,pass=?,email=?,country=? where id=?");
			st.setString(1, e.getName());
			st.setString(2, e.getPassword());
			st.setString(3, e.getEmail());
			st.setString(4, e.getCountry());
			st.setInt(5, e.getId());
			
			status = st.executeUpdate();
			con.close();
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return status;
	}
	
	
	public static int delete(int id)
	{
		int status = 0;
		try{
			Connection con = EmpDao.getConnection();
			PreparedStatement st = con.prepareStatement("delete from user905 where id=?");
			st.setInt(1, id);
			status = st.executeUpdate();
			con.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return status;
	}
	
	public static List<Emp> getAllEmployees()
	{
		List<Emp> list = new ArrayList<Emp>();
		try{
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user905");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				
				list.add(e);
			}
			con.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    }
		
		return list;
	
	}
	

}
