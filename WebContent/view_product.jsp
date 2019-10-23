<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.database.DB_Conn"%>
<%@page import="com.beans.User" %>

<jsp:include page="header.jsp"></jsp:include>


<!--============================== Sidebar Content ====================================-->
    <%
    Connection c = new DB_Conn().getConnection();
    Statement st = c.createStatement();
    String getCategory = "SELECT * FROM  `category`  ";
    
    ResultSet rs = st.executeQuery(getCategory);
    
%>

<section id="main">    
    <div class="container-fluid">
      <div class="row">
        <nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
          <ul class="nav nav-pills flex-column">
          	<li><b>Categories</b></li>
          	 <%
                while (rs.next()){
                   	int cat_id = rs.getInt("cat_id");
                    String cat = rs.getString ("category");
              %>
                    <li><a href="viewProducts_.jsp"><%= cat %></a>
                      <ol>
                      <%
                       Connection con = new DB_Conn().getConnection();
                       Statement stst = con.createStatement();
 				       String getSubCategory = "SELECT * FROM  `subcategory` where cat_id="+cat_id;
 					   ResultSet sub = stst.executeQuery(getSubCategory);

                           while(sub.next())
                           {
                        	   String subcategory = sub.getString("subcategory");
                       
                      %> 
      				    <li><a href="displayItems.jsp?cat_id=<%=cat_id%>&subcat=<%=subcategory%>"><%= subcategory %></a></li>
      				    <%
                           }
      				    %>
     				</ol>
                    </li>
               <%
                 }
               %>
          </ul>
    </nav>
    
    <!--================================ Product detail section ======================================-->
<%
String itemid = request.getParameter("itemid");
int id = Integer.parseInt(itemid);

Connection con = new DB_Conn().getConnection();
Statement state = con.createStatement();
String getitemdetails = "SELECT * FROM  `items` where item_id="+id;
ResultSet item = state.executeQuery(getitemdetails);
%>    
    

    <div class="col-sm-9 col-md-10 main">
  
				<h4><span class="head"><strong>Product Detail</strong></span></h4>				
				<div class="row">			
				<% if(item.next()){ %>			
					<div class="col-sm-12 col-md-5">
						<div class="product-box-new">
					    <a href=""><img src="<%=item.getString("img") %>" alt="ProductImage" /></a>
						</div>
				    </div>
				    
					<div class="col-sm-12 col-md-7">
								<div class="details">
									<strong>Product:</strong><%=item.getString("desc") %><br>
									<strong>Code:</strong> <span>#1234</span><br>
									<strong>Availability:</strong> <span>In Stock</span><br>
									<strong>Description:</strong><span><%=item.getString("info") %></span><br>								
								</div>									
								<h4><strong>Price:</strong>Rs.<%=item.getString("price") %></h4>
					
								<form class="form-horizontal">
									<label>Qty:</label>
									<input type="number" class="btnlength" value="1">
									<a href="add_to_cart.jsp?id=<%=item.getInt("item_id") %>" class="btn btn-info" type="submit">Add to cart</button>
								</form>
					</div>	
					<%
				      }
					%>						
				</div><!-- end of row -->
				    
    </div><!-- end of col -->
    </div><!-- end of row -->
    </div><!-- end of container -->
 </section>
 <jsp:include page="footer.jsp"></jsp:include>