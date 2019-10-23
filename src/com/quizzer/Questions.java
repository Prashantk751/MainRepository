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
 * Servlet implementation class Questions
 */
public class Questions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public int total_number;
	public String question;
	public int question_num;
	String[] choices = new String[3];
    public Questions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		ServletContext context = getServletContext();
		question_num = (int)context.getAttribute("number");

		
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
		    
		    //get question with question number
		    ResultSet ques = st.executeQuery("select * from questions where question_number = question_num");
		    ques.next();
		    question = ques.getString(question);
		    
		    //get choices
		    ResultSet cho =st.executeQuery("select * from choices where question_number = question_num");
	
		    
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
			"<div class='current'>Question "+question_num+" of "+total_number+"</div>"+
			"<p class='question'>"+question+"</p>"+
			"<form method='post' action='process.java'>"+
			"<ul class='choices'>"+
			"<li class='choices'><input type='radio' name='choice' value''> Hypertext Preprocessor</li>"+
			"</ul>"+
			"<input type='hidden' name='number' value='' >"+
			"<input type='submit' value='submit'>"+
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
