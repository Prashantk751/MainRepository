<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"  href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"  href="bootstrap/css/loginstyle.css">
<style >
body{
	background-image:url("images/features/img3.png");
	}
	
.mar {
    margin:120px auto;
}

.box {
background-image:url("images/features/img2.png");

}

</style>

		
<title>Login Form</title>
</head>
<body>

<div class="container-fluid">	
		<div class="row mar">
			<div class="col-md-6 offset-md-3">
			<h1 class="text-center" style="font-weight:700; color:#243342;">Login Page</h1>
				<form action="login" method="post" class="box ">
				<div class="form-group ">
				<h2 style="font-size:25px; font-weight:700; color:white">Login:</h2>
					<input type="text" name="email" class="form-control" placeholder="Email">
				</div>
				<br>
				<div class="form-group ">
					<input type="password" name="password" class="form-control" placeholder="Password">
				</div>
				<br>
				<input type="submit" name="submit" value="Login" class="btn btn-primary">
				<a href="index.jsp" class="btn btn-success">Cancel</a>
			</form>
		</div>
		</div>
		</form>
</div>

</body>
</html>