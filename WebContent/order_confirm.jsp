<jsp:include page="header.jsp"></jsp:include>
<%@page import="java.sql.*" %>
<%@page import="com.database.DB_Conn" %>
<%@page import="com.beans.User" %>
<%@page import="com.beans.Cart" %>
<%
User user = (User)session.getAttribute("user");
String username = user.getUsername();

Cart cart = (Cart)session.getAttribute("cart");
Double price = cart.getTotalPriceOfCart();

String order = request.getParameter("order_id");
int order_id = Integer.parseInt(order);
// Connection con = new DB_Conn().getConnection();
// Statement st = con.createStatement();
// String query = "select * from orders_placed where order_id="+order_id;
// ResultSet rs = st.executeQuery(query);

// rs.next();
// String price = rs.getString(columnIndex)

%>



<div class="order" style="margin-top:30px; height:500px; padding:20px;">
	<h5>
	Thank you <%=username %> for shopping with us.<br>Your order has been confirmed and will reach you shortly.
	<br>
	Your order id is:<%=order_id %>
	</h5>

</div>




<jsp:include page="footer.jsp"></jsp:include>