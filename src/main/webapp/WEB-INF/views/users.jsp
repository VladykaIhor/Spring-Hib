<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<div align="center">
    <h2> List of all users</h2>
    <a href="register"> Add new user</a> <br>
    <a href="products">List of all items</a> <br>
    <form action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>
    <table border="1">
        <tr>Email</tr>
        <tr> Password</tr>
        <tr> Role</tr>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <button><a href="/admin/edit/users?id=${user.id}" name="edit"> Edit </a></button>
                </td>
                <td>
                    <form action="/admin/remove/users" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
