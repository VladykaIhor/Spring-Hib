<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Edit User</title>
</head>

<body>
<h3>
    ${incompleteFormError}
</h3>

<h3>
    ${userIsAlreadyPresentError}
</h3>

<center>
    <form action="admin/edit/users" method="post">
        <table>
            <tr>
                <td>
                    <input name="id" type="hidden" value="${id}">
                </td>
            </tr>
            <tr>
                <td>Login :</td>
                <td><input name="login" type="text" value="${oldLogin}"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input name="password" type="password" value="${oldPassword}"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit">Confirm</button>
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
