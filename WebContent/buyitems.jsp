<jsp:include page="header.jsp"></jsp:include>
<%@page import="com.beans.User"%>

<jsp:useBean class="com.beans.User" id="user" scope="session"></jsp:useBean>

        <%
           
            String email=null;
           if ((session.getAttribute("user")==null)){
               response.sendRedirect("index.jsp");
           }else {
               user = (User)session.getAttribute("user");
               email = user.getUserEmail();
               
        %>
        
           <%
               if (user.getAddress() != null && user.getMobileNum() != null && user.getUserEmail() != null && user.getUsername() != null){
                   %>
               <div class="container">  
                 <div class="row">
                   <div class="col-md-5 useraddress">
                    <h3>Your Shipping Address</h3> <br/>
          
                            <div><strong>Name:</strong>
             			    <%= user.getUsername() %>
             			    </div>
                            <div><strong>Mobile:</strong>
                            <%= user.getMobileNum() %>
                            </div>
                            <div><strong>Address:</strong>
                            <span id ="useAddress"><%= user.getAddress() %></span> 
                            </div>               
       				</div>
        
        <%
               }else {
               %>   <div class="row">
               		<div class="col-md-5">
                    <a href="register.jsp">
                        <div>
                            <h1> <strong></strong>Add your Details for quick Checkout</h1>
                        </div>
                    </a>
                    </div>
                    
                        <%
               }
          }
         %>       
            <div class="col-md-7 details-form">
                    <h1>Buy Items</h1> <br/>
                    <form action="BuyProducts" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control"  name="name"  value="<%=user.getUsername() %>" />
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" maxlength="10" name="mobile" value="<%= user.getMobileNum()%>"/>
                        </div>
                        <div class="form-group">
                            <textarea class="form-control"  name="address" required><%=user.getAddress() %></textarea>    
                        </div>
                        <div class="form-group">
                            <input type="text" name="city" class="form-control" value="<%=user.getCity() %>" required/>
                        </div>
                        <div class="form-group">
                            <input  type="submit" class="btn btn-danger" value="Place Order"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
     
        
       
</body>
</html>        