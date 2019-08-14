<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<div style="text-align: center; background-color: aqua">
    <a href="index.jsp">Home </a>
    <center>
        <br>
        <form action="/logout" method="post">
            <button type="submit">Logout</button>
        </form>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            Items in shopping cart : ${count}
            <br>
            <c:forEach var="products" items="${products}">
            <tr>
                <td>${products.name}</td>
                <td>${products.description}</td>
                <td>${products.price}</td>
                <td>
                    <form action="/user/products/add_to_cart" method="post">
                        <input type="hidden" name="id" value="${products.id}">
                        <button type="submit">To Cart</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
            <br>
            <form action="/user/orderConfirmation" method="post">
                <button type="submit">Confirm Order</button>
            </form>
    </center>
</div>
<div>

</div>
</body>
</html>
