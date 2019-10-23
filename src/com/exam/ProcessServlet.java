package com.exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcessServlet
 */
@WebServlet("/ProcessServlet")
public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//creating a session variable
		HttpSession session = request.getSession();
		Result result = (Result)session.getAttribute("result");
		
		int score = result.getScore();
		
		String selected_choice = request.getParameter("choice");
		int selected_id = Integer.parseInt(selected_choice);
		
		String snumber = request.getParameter("number");
		int number = Integer.parseInt(snumber);
		int next = number+1;
		
		int total = ExamDao.getTotalNumberQues();
		
		int correct_choice = ExamDao.getCorrectChoice(number);
		int count;
		if(correct_choice==selected_id)
		{
	
			score = score +1;
			result.setScore(score);
		}
		
		if(number==total)
		{
			response.sendRedirect("final.jsp?num="+number);
		}
		else
		{
			response.sendRedirect("question.jsp?num="+next);
			
		}
		out.close();
	}

}
