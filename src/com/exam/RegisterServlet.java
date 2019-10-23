package com.exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.ExamDao.*;
import java.sql.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		//fetching the parameters from the form
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String rollnum = request.getParameter("rollnum");
		String mobile = request.getParameter("mobile");
		String yop = request.getParameter("yop");
		int yearop = Integer.parseInt(yop);
		String course = request.getParameter("course");

		
		
		
		//inserting the parameters in the database
		try{
			Connection con = ExamDao.getConnection();
			PreparedStatement st = con.prepareStatement("insert into user values(NULL,?,?,?,?,?,?)");
			st.setString(1, username);
			st.setString(2, email);
			st.setString(3, rollnum);
			st.setString(4, course);
			st.setInt(5, yearop);
			st.setString(6, mobile);
			
			int status = st.executeUpdate();
			
			if(status == 1)
			{
				Statement stt = con.createStatement();
				ResultSet res = stt.executeQuery("select * from user");
				while(res.next())
				{
					int userid = res.getInt("user_id");
					User user = new User();
					user.setUserid(userid);
					user.setUsername(username);
					user.setEmail(email);
					user.setRollNumber(rollnum);
					user.setCourse(course);
					user.setMobile(mobile);
					user.setYop(yearop);
				
					Result result = new Result();
					result.setUserid(userid);
					result.setUsername(username);
					result.setRollnumber(rollnum);
					result.setScore(0);
					session.setAttribute("result", result);
					session.setAttribute("user", user);
				}
				
				response.sendRedirect("welcomepage.jsp");
			}
			else
			{
				out.println("Error in registering your records.......");
				
			}
			
		}catch(Exception e)
		{
			out.print("Exception occurred:"+e);
		}
		
		
	}

}
