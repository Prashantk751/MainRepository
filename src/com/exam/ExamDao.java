package com.exam;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.exam.Choices;
import com.exam.Questions;
public class ExamDao {
	public static Connection getConnection()
	{
		Connection con=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/exam","root","9716690060");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return con;
	}
	
	public static int getTotalNumberQues()
	{
		int total=0;
		try{
			Connection con = ExamDao.getConnection();
			PreparedStatement st = con.prepareStatement("select * from questions");
			ResultSet rs = st.executeQuery();
			
			rs.last();
			total = rs.getInt(1);
			//total = rs.getInt("question_number");
			con.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return total;
	}

	
	public static String getQuesByNum(int num)
	{
		String question="";
		try{
			Connection con = ExamDao.getConnection();
			PreparedStatement st = con.prepareStatement("select question from questions where question_number=?");
			st.setInt(1, num);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				question = rs.getString(1);
			}
			con.close();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return question;
	}
	
	public static List<Choices> getChoicesForQues(int num)
	{
		List<Choices> list = new ArrayList<Choices>();
		
		try{
			Connection con = ExamDao.getConnection();
			PreparedStatement st = con.prepareStatement("select * from choices where question_number=?");
			st.setInt(1, num);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Choices c = new Choices();
				c.setId(rs.getInt(1));
				c.setText(rs.getString(4));
				
				list.add(c);
			}
		
			con.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	
	public static int getCorrectChoice(int number)
	{
		int correct_id=0;
		try{
			Connection con = ExamDao.getConnection();
			PreparedStatement st = con.prepareStatement("select * from choices where question_number="+number+" and is_correct=1");
			ResultSet rs = st.executeQuery();
			rs.next();
			correct_id = rs.getInt("id");
	
			con.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return correct_id;
	}
	
	public static int addQues(Questions ques)
	{
		int status = 0;
		try{
			Connection con = ExamDao.getConnection();
			PreparedStatement st = con.prepareStatement("insert into questions(question_number, question) values(?,?)");
			st.setInt(1, ques.getQuesNumber());
			st.setString(2, ques.getQuestion());
			status = st.executeUpdate();
	
			con.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return status;
		
	}
	
	public static int addChoices(Choices c, int correct_choice,int ivalue)
	{
		int quesnum = c.getQuesnum();
		String choicetext = c.getText();
		//int totalid = ExamDao.getTotalId();
		//totalid++;
		
		int is_correct = 0;
			if(correct_choice==ivalue)
			{
				is_correct = 1;
			}
		
		int status=0;
			try{
				Connection con = ExamDao.getConnection();
				PreparedStatement st = con.prepareStatement("insert into choices(question_number,is_correct,sometext)values(?,?,?)");
				st.setInt(1, quesnum);
				st.setInt(2, is_correct);
				st.setString(3, choicetext);
				status = st.executeUpdate();
		
				con.close();
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return status;
	}
}
