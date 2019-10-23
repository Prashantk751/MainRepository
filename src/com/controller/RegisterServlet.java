package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.database.DB_Conn;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import com.beans.User;

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
		 String message = "";
		 String messageDetail = "";
	     boolean isRegistered = false;

	        String messageUrl = "/message.jsp";
	        RequestDispatcher dispatchMessage =
	                request.getServletContext().getRequestDispatcher(messageUrl);

	        String username = request.getParameter("username");
	        String email = request.getParameter("email");
	        String pass = request.getParameter("password");
	        String passAgain = request.getParameter("passAgain");
	        String address = request.getParameter("address");
	        String phone = request.getParameter("phone");
	        String city = request.getParameter("city");
	        

	        PrintWriter out = response.getWriter();

	        HttpSession userSession = request.getSession();

	        try {
	            DB_Conn con = new DB_Conn();
	            Connection c = con.getConnection();
	                    if (pass.length() > 7) {
	                        if (pass.equals(passAgain)) {
	    
	                            String sql = "INSERT INTO  `userdetails` VALUES (NULL,?,?,?,?,?,?)";

	                            PreparedStatement psmt = c.prepareStatement(sql);
	                            psmt.setString(1, username);
	                            psmt.setString(2, email);
	                            psmt.setString(3, pass);
	                            psmt.setString(4, phone);
	                            psmt.setString(5, address);
	                            psmt.setString(6, city);

	                            int i = psmt.executeUpdate();

	                            if (i == 1) {
	                                isRegistered = true;
	                                out.println("You are registered ");
	                                User u = new User();
	                                u.setUserEmail(email);
	                                userSession.setAttribute("user", u);
	                                response.sendRedirect(request.getContextPath());
	                            } else {
	                                isRegistered = false;
	                                out.println("You are not registered");
	                            }

	                        } //Else both passwords do not match
	                        else {
	                            isRegistered = false;
	                            message = "Passwords do not match";
	                            messageDetail = "Please provide a matching passwords";
	                            out.print(" nOT Success!  both passwords do not match!");

	                        }
	                    } //or the paasword length is less than 7
	                    else {
	                        isRegistered = false;
	                        message = "Password length is less than 7 characters";
	                        messageDetail = "Please provide a passwords that has password length of minimum of seven alphanumeric characters";
	                        out.print(" nOT Success!! the paasword length is less than 7");
	                    }
	        
	            if (isRegistered == false) {
	                request.setAttribute("message", message);
	                request.setAttribute("messageDetail", messageDetail);
	                dispatchMessage.forward(request, response);
	            }

	            //try ends here
	        } catch (SQLIntegrityConstraintViolationException ex) {
	            // user exsts but wrong passwotd ask to CHANGE THE PASSWORD
	            messageDetail = ex.getMessage();
	            message = "You have been registered earlier please try your right password again, else change your password...";
	            out.print(" nOT Success!!" + ex);
	            request.setAttribute("message", message);
	            request.setAttribute("messageDetail", messageDetail);
	            dispatchMessage.forward(request, response);
	        } catch (Exception ex) {
	            messageDetail = ex.getMessage();
	            message = "There was a problem in registering your account please do retry again later...";
	            out.print(" nOT Success!!" + ex);
	            request.setAttribute("message", message);
	            request.setAttribute("messageDetail", messageDetail);
	            dispatchMessage.forward(request, response);
	            response.sendError(404);
	        }
	}

}
