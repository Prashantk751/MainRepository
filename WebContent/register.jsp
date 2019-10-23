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
background-color:#e8e4d7;
}


</style>

		
<title>Registration Form</title>
</head>
<body>


<div class="container-fluid">	
		<div class="row mar">
			<div class="col-md-6 offset-md-3">
			<h1 class="text-center" style="font-weight:700; color:#243342;">Registration Page</h1>
				<form action="register" method="post" class="box ">
				<div class="form-group ">
				<h2 style="font-size:25px; font-weight:700; color:white">Enter Details:</h2>
					<input type="text" name="username" class="form-control" placeholder="UserName" required>
				</div>
				<br>
				<div class="form-group ">
					<input type="password" name="password" class="form-control" placeholder="Password" required>
				</div>
				<br>
				<div class="form-group ">
					<input type="password" name="passAgain" class="form-control" placeholder="Confirm-Password" required>
				</div>
				<br>
				<div class="form-group ">
					<input type="email" name="email" class="form-control" placeholder="Email-Id" required>
				</div>
				<br>
				<div class="form-group ">
					<input type="text" name="phone" class="form-control" placeholder="Mobile Number" required>
				</div>
				<br>
				<div class="form-group ">
					<input type="text" name="address" class="form-control" placeholder="Address" required>
				</div>
				<br>
				<div class="form-group ">
					<input type="text" name="city" class="form-control" placeholder="City" required>
				</div>
				<br>
		
	     		<input type="submit" name="submit" value="Register" class="btn btn-primary">
				<a href="index.jsp" class="btn btn-success">Cancel</a>
			</form>
		</div>
		</div>
		</form>
</div>

</body>
</html>