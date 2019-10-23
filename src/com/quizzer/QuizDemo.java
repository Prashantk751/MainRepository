package com.quizzer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizDemo
 */
public class QuizDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public int total_number;
    public QuizDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		ServletContext context = getServletContext();
		context.setAttribute("number","1");
		
		//fetching database parameters from web.xml
		ServletConfig config = getServletConfig();
		String driver = config.getInitParameter("driver");
		String url = config.getInitParameter("url");
		String dbname = config.getInitParameter("dbname");
		String password = config.getInitParameter("pass");
		
		//database connection

		try{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,dbname,password);
			Statement st = con.createStatement();
			
			//get total number of questions 
			ResultSet rs = st.executeQuery("select * from questions");
		    rs.last();
		    total_number = rs.getInt("question_number");
		   

		}catch(Exception e){
			System.out.println("exception occured"+e);
		}
		
		//html part
		pw.println("<html>"+
	    "<head>"+
		"<title>PHP Quiz</title>"+
		"<meta charset='utf-8' />"+
		"<link rel='stylesheet' href='style.css' type='text/css' />"+
	    "</head>"+
	    "<body>"+
		"<div class='container'>"+
			"<header>"+
				"<h1>PHP QUIZ</h1>"+
			"</header>"+
			"<main>"+
			"<h1>Test Your PHP Knowledge</h1>"+
			"<p>This is a multiple choice quiz on PHP</p>"+
			"<ul>"+
				"<li><strong>No. of questions:</strong>"+total_number+"</li>"+
				"<li><strong>Type:</strong>multiple choice</li>"+
				"<li><strong>Estimated Time:</strong>"+total_number*0.5+"minutes</li>"+
			"</ul>"+
			"<a href='Questions' class='start'>Start Quiz</a>"+
			"</main>"+
		"<footer>"+
			"copyright &copy; 2016, PHP Qizzer"+
		"</footer>"+
	"</div>"+
"</body>"+
"</html>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
