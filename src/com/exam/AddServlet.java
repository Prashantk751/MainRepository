package com.exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
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
		
		String qnum = request.getParameter("qnum");
		int quesnum = Integer.parseInt(qnum);
		
		String question = request.getParameter("question");
		
		String[] choice = new String[4];
		choice[0] = request.getParameter("choice1");
		choice[1] = request.getParameter("choice2");
		choice[2] = request.getParameter("choice3");
		choice[3] = request.getParameter("choice4");
		
		String correct = request.getParameter("correct");
		int correct_choice = Integer.parseInt(correct);
		
		//adding question
		Questions ques = new Questions();//object of Question class
		ques.setQuesNumber(quesnum);
		ques.setQuestion(question);
	
		int statusques = ExamDao.addQues(ques);
		
		if(statusques > 0)
		{
		//adding choices
		for(int i=0;i<choice.length;i++)
		{
			Choices c = new Choices();//creatinig object for Choices Class
			c.setQuesnum(quesnum);
			c.setText(choice[i]);
		
			int statusforchoice = ExamDao.addChoices(c,correct_choice,i+1);
			if(statusforchoice > 0)
			{
				continue;
			}
			else{
				out.print("sorry unable to add Choice "+choice[i]);
			}
					
		}

		
		}//statusques if block
		else{
			out.println("sorry unable to add Question");
		}
	
		out.println("<h4>Question has been added successfully</h4>");
		out.println("<a href='addquestion.jsp'>Click to add more questions</a>");
	}

}
