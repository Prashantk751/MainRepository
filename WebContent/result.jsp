<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.exam.ExamDao" %>
<%@page import="com.exam.Choices" %> 
<%@page import="com.exam.Result" %>
<%@page import="java.sql.*" %>
<%@page import="com.exam.AddServlet" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Results</title>
<!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/examstyle.css" rel="stylesheet">

</head>
<body>

<%
Connection con = ExamDao.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from result");
%>

<h1 style="text-align:center; margin-top:20px;">Results</h1>

<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">UserId</th>
      <th scope="col">UserName</th>
      <th scope="col">Roll Number</th>
      <th scope="col">Score</th>
    </tr>
  </thead>
  <tbody>
  <%while(rs.next()){ %>
    <tr>
      <td><%=rs.getInt("user_id") %></td>
      <td><%=rs.getString("username") %></td>
      <td><%=rs.getString("rollnumber") %></td>
      <td><%=rs.getInt("score") %></td>
    </tr>
    <% } %>
  </tbody>
</table>




</body>
</html>