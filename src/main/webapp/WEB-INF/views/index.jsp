<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<style>
    .container {
        height: 100vh;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .container form {
        padding: 20px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .container button {
        margin-top: 10px;
    }

    .container label {
        margin: 5px 0;
    }
</style>
<body>

<div class="container">
    <form action="login" method="post">
        <label for="login">Login </label>
        <input class="form-control" type="text" name="login" id="login" placeholder="Enter login">
        <label for="password">Password</label>
        <input class="form-control" type="password" id="password" name="password" placeholder="Enter password">
        <button type="submit" class="btn btn-success">Sign In</button>
    </form>
</div>

</body>
</html>
