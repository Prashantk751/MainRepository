<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.beans.User" %>  
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
    .backRed {
        background: #CC0000;
        padding: 3px 7px;
        margin-right: 3px;
    }
</style>
</head>
<body>

<jsp:useBean id="cart" scope="session" class="com.beans.Cart"  ></jsp:useBean>
<%
    ArrayList<Integer> Qty = new ArrayList();
    Qty = cart.getQty();
    int totalQty = 0;
    for (int i=0; i<Qty.size(); i++){
        totalQty += Qty.get(i);
    }
    
        
    
    User user = (User)session.getAttribute("user");
    String email = user.getUserEmail();
    String userName = user.getUsername();
    int uid = user.getUserid();
    
    String printName;
    if (userName == null){
        printName = email;
    }
    else {
        printName = userName;
    }
%>



<a href="addToCart_view.jsp"><span class="backRed"><%= totalQty %></span> In Cart </li></a>
&nbsp;
<a href="#" style="color:white; text-decoration:none;"><%= printName %></a>
&nbsp;
<a href="LogoutServlet"><li class ="btn btn-success">Logout</li></a>
</body>
</html>