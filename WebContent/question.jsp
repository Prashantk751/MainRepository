<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %> 
<%@page import="java.util.List" %>  
<%@page import="com.exam.ExamDao" %>
<%@page import="com.exam.Choices" %>
<%@page import="com.exam.User" %>
<%@page import="com.exam.Result" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question page</title>
<!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/examstyle.css" rel="stylesheet">
 
</head>
<body>
<%

User user = (User)session.getAttribute("user");
Result result = (Result)session.getAttribute("result");

if(user == null)
{
 out.print("Please register first...");
}



String sqnum = request.getParameter("num");
int quesnum = Integer.parseInt(sqnum);

int total = ExamDao.getTotalNumberQues();

String questext = ExamDao.getQuesByNum(quesnum);

List<Choices> list = new ArrayList<Choices>();

//choices of selected question is stored in list
list = ExamDao.getChoicesForQues(quesnum);


%>

	<div class='container'>
		<header>
			<h1>JAVA QUIZ</h1>
		</header>
		<!--  
		<div>Time left: <span id="time">05:00</span> minutes!</div> 
		-->
		<main>
		<div class="current">Question <%=quesnum %> of <%=total %></div>
			<p class="question"><%=questext %></p>
					
			<form  action="ProcessServlet" method="post">
			<ul class="choices">
			   <% 
				for(Choices c:list){
			   %>
					<li class="choices">
					<input type="radio" name="choice" value="<%=c.getId() %>"><%=c.getText() %>
					</li>
			   <%
				}
			   %>
			</ul>
			<input type="hidden" name="number" value="<%=quesnum %>">
			<input type="submit" name="submit" value="submit">
			</form>
			</main>
				
			<footer>
				copyright &copy; 2017, Java Quizzer
			</footer>
		</div>
</body>
</html>