<%--
  Created by IntelliJ IDEA.
  User: Yngwa
  Date: 21-Jul-19
  Time: 10:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm payment</title>
</head>
<body>
<div class="base">
    <form action="/order/payment" method="post">
        <input type="text" name="confCode" value="${confCode}">
        <input type="hidden" name="email" value="${email}">
        <input type="submit" formaction="/order/payment" formmethod="post" value="Confirm">
    </form>
    <h1>${message}</h1>
</div>
</body>
</html>
