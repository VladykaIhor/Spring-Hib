<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>


<div align="center">


    <form action="/admin/register" method="post">
        Login <input type="text" name="login" value=""> <br>
        E-mail <input type="email" name="email" value=""> <br>
        Password <input type="password" name="password"> <br>
        Confirm password <input type="password" name="repeatPassword"> <br>
        Role <input type="radio" value="admin" name="role"> Admin<br>
        <input type="radio" value="user" name="role"> User<br>
        <input type="submit" value="Register">
    </form>

    <button type="submit" formaction="/admin/users" formmethod="get">Back to all users list</button>
</div>
</body>
</html>
