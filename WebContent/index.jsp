<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/examstyle.css" rel="stylesheet">
    
    <style>
    .button {
    margin-top:20px;
    width:300px;
    }
    
    .yop {
     width:100px;
     height:30px;
     margin-top:10px;
     border-radius:5px;
    
    }
    
    .label {
    font-size: 20px;;
    }
    
    .box {
    margin-top:25px;
    }
    
    .outer {
    border: 2px dotted white;
    padding:20px;
    border-radius:5px;
    }
    </style>
    
</head>
<body>

<div class="container-fluid">	
		<div class="row ">
			<div class="col-xs-12 col-sm-6 offset-sm-3 outer">
			<h1 class="text-center" style="font-weight:700; color:#243342;">Registration Page</h1>
				<form action="register" method="post" class="box ">
				<div class="form-group ">
					<label class="label">UserName:</label>
					<input type="text" name="username" class="form-control" required>
				</div>
				<div class="form-group ">
				<label class="label">Email-Id</label>
					<input type="email" name="email" class="form-control" required>
				</div>
				<div class="form-group ">
				<label class="label">Roll Number:</label>
					<input type="text" name="rollnum" class="form-control" required>
				</div>
				
				<div class="form-group ">
				<label class="label">Mobile Number:</label>
					<input type="text" name="mobile" class="form-control" required>
				</div>
				
				<div class="form-group ">
				<label class="label">Year Of Passing:</label>
					<select name="yop" class="yop" required>
					<option>2017</option>
					<option>2018</option>
					<option>2019</option>
					</select>
				</div>
				
				<div class="form-group ">
				<label class="label">Course:</label>
					<select name="course" class="yop"  required>
					<option>B.Tech</option>
					<option>M.Tech</option>
					<option>MCA</option>
					</select>
				</div>
	     		<input type="submit" name="submit" value="Register" class="btn btn-primary button">
				<a href="register.jsp" class="btn btn-success button">Cancel</a>
			</form>
		</div>
	</div>	
</div>

</body>
</html>