package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.Cart;
import com.beans.User;
import com.database.DB_Conn;
import java.sql.*;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class BuyProducts
 */
@WebServlet("/BuyProducts")
public class BuyProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProducts() {
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
		
		
		// GETTING ALL THE VALUES FROM THE USER
        String name, city, address, mobile;
        int order_id;
        Connection c = null;
        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");
        Cart cart;
        cart = (Cart) session.getAttribute("cart");
        
        name = request.getParameter("name");
        city = request.getParameter("city");
        address = request.getParameter("address");
        mobile = request.getParameter("mobile");
        
        int userid = user.getUserid();
        String email = user.getUserEmail();
        
        if (name.trim().length() > 1
                && address.trim().length() >= 5
                && mobile.trim().length() >= 5
                && mobile.trim().length() <= 12) {
            
            if (!(session.getAttribute("user") == null)
                    && !(session.getAttribute("cart") == null)) {
                
                try {
                    response.setContentType("text/html;charset=UTF-8");
                    
                    c = new DB_Conn().getConnection();
                    
                    //******* Starting a Transaction
                    Double price = cart.getTotalPriceOfCart();
                    String sprice = price.toString();
                    c.setAutoCommit(false);
                    String insertOrder;
                    insertOrder = "    INSERT INTO  `shop`.`orders_placed` ("
                            + "    `order_id` ,"
                            + "    `user_id` ,"
                            + "    `status` ,"
                            + "    `shipper_name` ,"
                            + "    `address` ,"
                            + "    `mobile` ,"
                            + "    `shipper_email` ,"
                            + "    `total_order_price` ,"
                            + "    `ordered_on`"
                            + "    )"
                            + "    VALUES ("
                            + "    NULL ,  ?,  'pending',  ?,  ?,  ?, ?,?, NOW( ) "
                            + "    );";
                    
                    PreparedStatement pstat =
                            c.prepareStatement(insertOrder);
                  
                    pstat.setInt(1, userid); // user iD
                    pstat.setString(2, name); //`shipper_name` 
                    pstat.setString(3, address); //`address` 
                    pstat.setString(4, mobile); //`mobile` 
                    pstat.setString(5, email); //`shipper_email` 
                    pstat.setString(6, sprice); //`total_order_price`` 
    
                   
                   
                    int executeupd = pstat.executeUpdate();
                    
                    if (executeupd == 1) {
                        //get the latest order id of the recent update

                        //out.println("Ok here we are onr order updted");
                        String getLatestOrderId = "SELECT  `order_id` "
                                + "FROM  `orders_placed` "
                                + "WHERE  `user_id` = " + user.getUserid() + " "
                                + "ORDER BY  `ordered_On` DESC; ";
                        
                        pstat.close();
                        
                        Statement st = c.createStatement();
                        ResultSet GetOrderId = st.executeQuery(getLatestOrderId);
                        GetOrderId.next();

                        //Here we get the latest order id for the current user
                        order_id = GetOrderId.getInt("order_id");
                        
                        GetOrderId.close();
                      //  PrintWriter out = response.getWriter();
                        //out.println("You user :" + user.getUserEmail() + " has bought items worth "
                       // + cart.getTotalPriceOfCart() + " and order id of " + order_id);
                    	response.sendRedirect("order_confirm.jsp?order_id="+order_id);
		
                    }
		
                    
                } catch (Exception ex) {
                	PrintWriter out = response.getWriter();
                    out.println("An exception occurred: " + ex);
                    //response.sendRedirect("buyitems.jsp");
                    
                }
                session.removeAttribute("cart");
            } else {
                //response.sendRedirect("index.jsp");
            	PrintWriter out = response.getWriter();
                out.println ("No items in cart");
            }
            
        } else {
            //response.sendRedirect("buyitems.jsp");
        	PrintWriter out = response.getWriter();
            out.println ("NOt validated");
        }
                    
	}
}
