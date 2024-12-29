
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
<link
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css"> -->
	
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
	
<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable();
});

function deleteEmp(id){
	
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		window.location.reload()
			
			//window.location.href="/EMS/viewEmployee";
		
	}
	xhttp.open("POST", "/EMSNEW/DeleteEmployee?empId=" + id);
	xhttp.send();
}

function loadData(empId,empName,email,phone,dateOfJoin) {
	
	
	document.getElementById('id02').style.display='block';
	
	document.getElementById("empId").value=empId;
	document.getElementById("ename").value=empName;
	document.getElementById("email").value=email;
	document.getElementById("phone").value=phone;
	document.getElementById("dateOfJoining").value=dateOfJoin;
}

var modal = document.getElementById('id02');

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}
</script>
<style type="text/css">
td.highlight {
	background-color: rgba(var(--dt-row-hover), 0.052) !important;
}
</style>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<div><a href="/EMSNEW">Back to home page</a></div>
	<%
	List<Map<String, String>> list = (List) request.getAttribute("employeeList");
	%>
	<table id="example" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>Employee Id</th>
				<th>Employee Name</th>
				<th>Email</th>
				<th>phone No.</th>
				<th>Date Of joining</th>
				<th>Action</th>

			</tr>
		</thead>
		<tbody>
			<%
			for (Map m : list) {
			%>
			<tr>
				<td><%=m.get("empId")%></td>
				<td><%=m.get("name")%></td>
				<td><%=m.get("email")%></td>
				<td><%=m.get("phone")%></td>
				<td><%=m.get("dateOfJoining")%></td>
				<td><button onclick="loadData(
						<%=m.get("empId")%>,
						'<%=m.get("name")%>',
						'<%=m.get("email")%>',
						'<%=m.get("phone")%>',
						'<%=m.get("dateOfJoining")%>'
						)">EDIT</button>
					<button onclick="deleteEmp(<%=m.get("empId")%>)">DELETE</button></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	
	<div id="id02" class="modal">

		<form class="modal-content animate" action="employee"  method="PUT"
			method="post">
			<div class="imgcontainer">
				<span onclick="document.getElementById('id02').style.display='none'"
					class="close" title="Close Modal">&times;</span> 
			</div>

			<div class="container">
				<label for="empId"><b>Employee Id:</b></label> 
				<input type="text"  name="empId" id="empId" readonly="readonly" />
				
				<label for="ename"><b>Employee Name:</b></label> 
				<input type="text"  name="ename" id="ename"/>
				
				
				<label for="email"><b>Email:</b></label> 
				<input type="text" name="email" id="email" readonly="readonly"/>
				
				<label for="phone"><b>Phone Number:</b></label> 
				<input type="text" name="phone" id="phone"/>
				
				<label for="dateOfJoining"><b>Joining Date:</b></label> 
				<input type="text" name="dateOfJoining" id="dateOfJoining">

				<button type="submit">Update</button>
				
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button"
					onclick="document.getElementById('id02').style.display='none'"
					class="cancelbtn">Cancel</button>
			</div>
		</form>
	</div>
	<jsp:include page="./footer.jsp"></jsp:include>
</body>

<style>
/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}

</style>
</html>