package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.database.DB_Conn;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import com.beans.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		 String email;
		 String pass;
	     String db_email;
	     String db_pass;
	     String db_username;
	     String db_address;
	     String db_phone;
	     String db_city;
	     int userid;
	     boolean isLoggedIn = false;
	     
	     HttpSession userSession = request.getSession();
	     
	     PrintWriter out = response.getWriter();
	     
	     email = request.getParameter("email");
	     pass = request.getParameter("password");
	     
	     String message = "";
	     String messageDetail = "";
	        
	     String messageUrl = "/message.jsp";
	     
	     RequestDispatcher dispatchMessage =
	                 request.getServletContext().getRequestDispatcher(messageUrl);
	        
	        try {
	            
	            out.println("email " + email + " pass " + pass);
	            DB_Conn con = new DB_Conn();
	            Connection c = con.getConnection();
	            String sqlGetUsers = "SELECT * FROM  `userdetails`; ";

	            PreparedStatement st = c.prepareStatement(sqlGetUsers);

	            ResultSet rs = st.executeQuery();

	            while (rs.next()) {
	                db_email = rs.getString("email_id");
	                db_pass = rs.getString("pass");
	                db_username = rs.getString("username");
	                db_address = rs.getString("address");
	                db_city = rs.getString("city");
	                db_phone = rs.getString("phone");
	                userid = rs.getInt("user_id");
	                if (email.equals(db_email)) {
	                    message = "Your email-id exists with us!";
	                    //you exist with us
	                    if (pass.equals(db_pass)) {
	                        isLoggedIn = true;
	                        //user exists and password is matching
	                        out.print("You are logged in");
	                        User user = new User();
	                        user.setUserEmail(email);
	                        user.setUsername(db_username);
	                        user.setAddress(db_address);
	                        user.setCity(db_city);
	                        user.setMobileNum(db_phone);
	                        user.setUserid(userid);
	                        userSession.setAttribute("user", user);
	                        response.sendRedirect(request.getContextPath()+"/index.jsp");
	                      }
	                    else {
	                        isLoggedIn = false;
	                        message = "Wrong Password...!";
	                        messageDetail = "Password does not match with the password during registeration... Please re-login with correct password";
	                        break;
	                    }
	                }
	                else {
	                    out.println(" no such email Register an account now!");
	                    message = "Email does not exists";
	                    messageDetail = "Please register with us right now to buy items on the go!";
	                    isLoggedIn = false;
	                }
	            }
	            
	            if (isLoggedIn == false){
	                request.setAttribute("message", message);
	                request.setAttribute("messageDetail", messageDetail);
	                dispatchMessage.forward(request, response);
	            }
	        } catch (SQLException e) {
	            out.println(" Problem in the process execution...");
	            //response.sendError(404);
	            message = "An Error occoured during the process of login";
	            messageDetail = "We are extremely sorry to have this but we had an error during your process of login please do try after some time,";
	                   
	            request.setAttribute("message", message);
	            request.setAttribute("messageDetail", messageDetail);
	            dispatchMessage.forward(request, response);
	        
	        } catch (Exception e) {
	            out.println(" Problem in the process execution...");
	            //response.sendError(404);
	        }
	}

}
