<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title> Products List </title>>
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
            <c:forEach var="products" items="${products}">
            <tr>
                <td>${products.name}</td>
                <td>${products.description}</td>
                <td>${products.price}</td>
                <td>
                    <form action=/admin/products/remove?id=${products.id} method="post">
                        <input type="submit" value="Remove">
                    </form>
                <td>
                    <form>
                        <button><a href="/admin/products/edit?id=${products.id}">Edit</a></button>
                    </form>
                </td>
            </tr>
            </c:forEach>

            <br>

            <form action="/admin/products" method="post">
                Name <input name="name" type="name"/> <br>
                Description <input name="description" type="description"> <br>
                Price <input name="price" type="price"> <br>
                <button type="submit">Add Product</button>
            </form>

    </center>
</div>
</body>
</html>
