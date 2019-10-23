<jsp:include page="header.jsp"></jsp:include>

<%@page import="java.util.ArrayList"%>
<%@page import="com.beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cart" scope="session" class="com.beans.Cart"></jsp:useBean>

        <div class="container">
            <div id="whiteBox">
                    <h2 class="text-center" style="padding:10px 00px">Products In Your Cart</h2>

                    <%
                        User user = new User();
                        session.getAttribute("user");
                        //out.println(session.getAttribute("user"));
                        user = (User) session.getAttribute("user");
                        if (session.getAttribute("user") == null) {
                    %>
                    <h3 style="padding:10px 00px">Please Login before buying...</h3>

                    <%                } else {
                        //out.println("login by " + User.getUserEmail());

                    %>

                    <%
                        String sid = request.getParameter("id");
                        int id;
                        if (request.getParameter("id") == null) {
                            response.sendRedirect("view_product.jsp?itemid="+sid);
                        } else {
                            id = Integer.parseInt(sid);
							//Cart c = new Cart();
                            boolean b = cart.addProduct(id);

                            if (b == true) {
                               // out.println(id + " " + c.getProductName(id) + " added !! with price of " + c.getProductPrice(id));
                            } else {
                                out.println("Not added !!");
                            }

                            //out.println("<br/>Total value price of the cart " + c.getTotalPriceOfCart());
                            ArrayList<String> productNames = new ArrayList();
                            ArrayList<Double> productPrices = new ArrayList();
                            ArrayList<Integer> Qty = new ArrayList();
                            ArrayList<Integer> ids = new ArrayList();

                            productNames = cart.getProductNames();
                            productPrices = cart.getPrices();
                            Qty = cart.getQty();
                            ids = cart.getId();
                    %>


                    
                     <%
                          for (int i = 0; i < productNames.size(); i++) {
                      %>
                      <div class="row cart-box">
                        <div class="col-md-4">
                            <h5><strong>Name Of Product</strong></h5>
                             <%=productNames.get(i)%> 
                        </div>
                        <div class="col-md-4">
                            <h5><strong>Price</strong></h5>
                            Rs. <%=productPrices.get(i)%>
                        </div>
                        <div class="col-md-4">
                            <h5><strong>Quantity</strong></h5>
                            x<%=Qty.get(i)%>
                            &nbsp;&nbsp;
                             Rs. <%= Qty.get(i) * productPrices.get(i)%>
                        </div>
                   </div>

                        <%
                            }
                            productNames.clear();
                            productPrices.clear();

                        %>
                        <br/>

                        <div class="row total-price-box">
                        	<div class="col-md-6 offset-md-6">
                            <strong>Total Price of your Cart</strong>
                            &nbsp;
                            Rs <%= Math.ceil(cart.getTotalPriceOfCart()) %>
                            </div>
  						</div>
                        <br/>
                        <br/>
                        <div class="row">
                        	<div class="col-md-6 offset-md-6">
                        <a href="buyitems.jsp" class="btn btn-primary text-right">Buy These Items</a>
                        <a href="index.jsp" class="btn btn-success text-right">Continue Shopping</a>
                        	</div>
                        </div>	
                    </div>

                    <br/>
                    <br/>
                    
                    
                    <br/>
                    <%
                           }
                        }
                    %>
                </div>

            </div>
        </div>

<jsp:include page="footer.jsp"></jsp:include>