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
<title>Add New Question</title>
<!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/examstyle.css" rel="stylesheet">

</head>
<body>

<%
int question_num = 0;

Connection con = ExamDao.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select question_number from questions");

while(rs.next())
{
question_num = rs.getInt("question_number");	

}

int quesnum = question_num + 1;

%>

<div class="container">
			<header>
				<h1>JAVA QUIZ</h1>
			</header>
			
			<main>
				<h1>Add a Question</h1>
	
				<form action="add" method="post">
					<p>
						<label>Question Number: </label>
						<input type="number" name="qnum" value="<%=quesnum %>"/>
					</p>
					<p>
						<label>Question Text: </label>
						<input type="text" name="question" />
					</p>
					<p>
						<label>Choice #1: </label>
						<input type="text" name="choice1" />
					</p>
					<p>
						<label>Choice #2: </label>
						<input type="text" name="choice2" />
					</p>
					<p>
						<label>Choice #3: </label>
						<input type="text" name="choice3" />
					</p>
					<p>
						<label>Choice #4: </label>
						<input type="text" name="choice4" />
					</p>
					<p>
						<label>Correct Choice Number: </label>
						<input type="number" name="correct" />
					</p>
					<p>
						<input type="submit" name="submit" value="submit" />
					</p>
				</form>
			</main>
			<footer>
				copyright &copy; 2017, Java Quizzer
			</footer>
		</div>
</body>
</html>