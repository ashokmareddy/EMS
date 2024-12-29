<%@page import="java.util.*"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function logout(){
	window.location.href = "/EMSNEW";
}

</script>
</head>
<body>
	
	<jsp:include page="./header.jsp"></jsp:include>
	<div class="row" style="text-align: right">
		<a href="javascript:void(0)" onclick="logout()">logout</a>
	
	</div>
	<% Map map = (Map)request.getAttribute("employee");
		
	%>
	<table border="1" align="center">
		<thead>
			<tr>
				<th colspan="2">Employee Details</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Employee Id:</td><td><%=map.get("empId") %></td>
			</tr>
			<tr>
				<td>Employee Name:</td><td><%=map.get("name") %></td>
			</tr>
			<tr>
				<td>Email:</td><td><%=map.get("email") %></td>
			</tr>
			<tr>
				<td>Phone Number:</td><td><%=map.get("phone") %></td>
			</tr>
			<tr>
				<td>Joining Date:</td><td><%=map.get("dateOfJoining") %></td>
			</tr>
		</tbody>
	</table>

	<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>