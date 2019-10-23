<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %> 
<%@page import="java.util.List" %>  
<%@page import="com.exam.ExamDao" %>
<%@page import="com.exam.Choices" %> 
<%@page import="com.exam.Result" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test completed</title>
<!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/examstyle.css" rel="stylesheet">
</head>
<body>

<%
String number = request.getParameter("num");
int num = Integer.parseInt(number);
int total = ExamDao.getTotalNumberQues();

Result result = (Result)session.getAttribute("result");

int score = result.getScore();
int userid = result.getUserid();
String username = result.getUsername();
String rollnum = result.getRollnumber();


%>
<%

int status = 0;
try{
Connection con = ExamDao.getConnection();
PreparedStatement st = con.prepareStatement("insert into result values(?,?,?,?)");
st.setInt(1, result.getUserid());
st.setString(2, result.getUsername());
st.setString(3,result.getRollnumber());
st.setInt(4,result.getScore());

 status = st.executeUpdate();
}catch(Exception e)
{
System.out.println("Exception occurred: "+e);
}
%>



		<div class='container'>
			<header>
				<h1>JAVA QUIZ</h1>
			</header>
			<main>
			<% if(status > 0){ %>
				<h3>Congratulations you have completed the test.</h3>
				<p>Your Response has been recorded.</p>
				<a href="LogoutServlet" class="start">Logout</a>
				<% }else{ %>
				<h3>Something went wrong!</h3>
				<h4>Result is not saved.......</h4>
				
				<%} %>
			</main>
			<footer>
				copyright &copy; 2017, Java Quizzer
			</footer>
		</div>
	

</body>
</html>