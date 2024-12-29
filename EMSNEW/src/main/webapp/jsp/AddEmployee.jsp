<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container addEmployee">
		<form method="POST" action="employee">
			<div class="row">
			<div class="col-100">
			<p style="color: red"><%= request.getAttribute("errorMessage")==null?"":request.getAttribute("errorMessage") %> </p>
			</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="ename">Name</label>
				</div>
				<div class="col-75">
					<input type="text" id="ename" name="ename"
						placeholder="Enter employee name">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="email">Email</label>
				</div>
				<div class="col-75">
					<input type="text" id="email" name="email"
						placeholder="Employee email id" onblur="checkEmail()" /> <br />
					<p id=erroremail style="color: red"></p>
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="phone">Phone no</label>
				</div>
				<div class="col-75">
					<input type="text" id="phone" name="phone"
						placeholder="Enter phone number"> <br /> <span
						id=errorphone></span>
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="doj">Date Of joining</label>
				</div>
				<div class="col-75">
					<input type="date" id="doj" name="doj"
						placeholder="Enter date of joining"> <br /> <span
						id=errordoj></span>
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="doj">Password</label>
				</div>
				<div class="col-75">
					<input type="password" name="password" id="password"
						placeholder="Enter the password">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="row-cnt">
					<input type="submit" value="Submit"
						style="align-content: left; padding: 10px" /> <input
						type="button" value="reset" onclick="reset()"
						style="align-content: left; padding: 10px" />
				</div>
			</div>

		</form>

		<div class="row">
			<span><a href="/EMSNEW/viewEmployee">View Employees list</a></span>
		</div>

	</div>
</body>
<script type="text/javascript">
	function reset() {
		$("#ename").val("");
		$("#email").val("");
		$("#phone").val("");
		$("#password").val("");
		$("#ename").val("");

	}

	function validateEmail(email) {
		const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		return re.test(String(email).toLowerCase());
	}

	function checkEmail() {

		var email = document.getElementById("email").value
		if (!validateEmail(email)) {
			document.getElementById("erroremail").innerHTML = "Invalid email address!!";
			// $("#erroremail").html("<p>Invalid email address</p>"); 
			return false;
		} else {

			const xhttp = new XMLHttpRequest();
			xhttp.onload = function() {
				if(this.responseText=="true"){
					document.getElementById("erroremail").innerHTML = "Email Id is already present!!"
					setTimeout(() => {
						document.getElementById("email").value="";
					}, 3000);
					return false;
				}
				
			}
			xhttp.open("GET", "ValidateEmail?email=" + email);
			xhttp.send();
		}
	}
</script>
</html>