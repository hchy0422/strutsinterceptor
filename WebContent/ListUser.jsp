<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Info Management</title>
</head>
<body>
<h1>Student Info Management</h1>

<a href="UserServlet?operation=new">Add New User</a>
 | 
<a href="UserServlet?opearation=list">List All Users</a>


<div>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Operation</th>
		</tr>
		<c:forEach var="user" items="${listUser}">
			<tr>
				<td><c:out value="${user.getId()}"></c:out></td>
				<td><c:out value="${user.getFirstName()}"></c:out></td>
				<td><c:out value="${user.getLastName()}"></c:out></td>
				<td><c:out value="${user.getEmail()}"></c:out></td>
				<td>
						<a href="UserServlet?operation=edit&id=${user.getId()}">Update</a>
						 | 
						<a href="UserServlet?operation=delete&id=${user.getId()}">Delete</a>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>