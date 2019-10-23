<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.exam.ExamDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome page</title>
<!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/examstyle.css" rel="stylesheet">
    <script type="text/javascript" src="timer.js"></script>
</head>
<body>
<%
int total = ExamDao.getTotalNumberQues();
%>
	<div class='container'>
		<header>
			<h1>JAVA QUIZ</h1>
		</header>
		<main>
			<h1>Test Your Java Knowledge</h1>
			<p>This is a multiple choice quiz on Java</p>
			<ul>
				<li><strong>No. of questions:</strong> <%=total %></li>
				<li><strong>Type:</strong>Multiple choice</li>
				<li><strong>Estimated Time:</strong> <%=total %> minutes</li>
			</ul>
			<a href="question.jsp?num=1" class="start btn btn-primary">Start Quiz</a>
		</main>
		<footer>
			copyright &copy; 2017, Java Quizzer
		</footer>
	</div>

</body>
</html>