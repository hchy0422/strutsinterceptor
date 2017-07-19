<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
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

	<form action="UserServlet" method="POST">
	<input type="hidden" name="operation" value="insert" />
	<h2>Add New User</h2>
	First Name: <input type="text" name="first" placeholder="First Name"
	    value="<c:out value="${user.firstName}"/>" required/> <br/>
	Last Name: <input type="text" name="last" placeholder="Last Name"
	    value="<c:out value="${user.lastName}"/>" required/> <br/>
	Email: <input type="email" name="email" 
	    value="<c:out value="${user.email}"/>" required/> <br/>
	<input type="submit" value="Save">
	</form>
	
</div>

</body>
</html>