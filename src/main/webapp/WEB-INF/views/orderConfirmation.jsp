<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<div name="cart" align="center">
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            </th>
        </tr>
            <c:forEach var="product" items="${cartProducts}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                </tr>
            </c:forEach>
    </table>
</div>
<div name="order" align="center">
    <br><br><br>
    <form action="/user/order_confirmation" method="get">
        Name <input name="name" type="text"/> <br> <br>
        Surname <input name="surname" type="text"/> <br> <br>
        Country <input name="country" type="text"/> <br> <br>
        State <input name="state" type="text"/> <br> <br>
        City <input name="city" type="text"/> <br> <br>
        Phone number <input name="phoneNumber" type="text"/> <br> <br>
        ZIP/Postal Code <input name="zip" type="text"/> <br> <br>
        <button type="submit"> Confirm order </button>
    </form>
</div>
</body>
</html>
