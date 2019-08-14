<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Authorization</title>
</head>
<body>
<h2>Spring Security 4 - Custom login form example</h2>
<hr />
<a href="/login">Authorization</a>
<br>
<a href='<spring:url value="/signout"/>'>Logout</a>
</body>
</html>