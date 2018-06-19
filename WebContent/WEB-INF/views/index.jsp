<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Store management - Welcome</title>
</head>
<body>
	<h1>Login</h1>
	<form:form method="POST" action="${pageContext.request.contextPath}/login" modelAttribute="loginUser">
		<form:input type="text" placeholder="Username" path="username"/>
		<form:input type="text" placeholder="Password" path="password"/>
		<input type="submit" value="Login"/>
	</form:form>
	<hr />
	<h1>Search</h1>
	<form method="GET" action="${pageContext.request.contextPath}/show_all">
		<label>Sort by: </label><br/>
		<input type="radio" name="sort_type" value=0><label>Name</label>
  		<input type="radio" name="sort_type" value=1><label>Likes</label><br>
		<input name="searchbox" type="text" placeholder="Search..."/>
		<input type="submit" value="Search" />
	</form>
	<form method="GET" action="${pageContext.request.contextPath}/show_all">
		<input type="submit" value="Show all" />
	</form>
</body>
</html>