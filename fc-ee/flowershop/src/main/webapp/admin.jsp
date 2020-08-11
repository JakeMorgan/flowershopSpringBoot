<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import= "com.accenture.be.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="css/flowershop.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FlowerShop</title>
</head>
<body>
<div style="text-align: center">
<h2>FlowerShop</h2>
<h3>Admin page</h3>
<form action="admin" method="post">
<b><p>Name: <input type="text" name="name" size="40"></p></b>
<b><p>Price: <input type="text" name="price" size="40"></p></b>
<b><p>Quantity: <input type="text" name="quantity" size="40"></p></b>
<br>
<button type="submit" name="Add" id="Add" method="post">Save</button>
<br>
</form>
</div>
<div>
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Create Date</th>
            <th>Complete Date</th>
            <th>Total</th>
            <th>Status</th>
        </tr>

        <c:forEach items="${ordersList}" var="fl" varStatus="rowStatus">
            <tr>
                <td>${fl.id}</td>
                <td>${fl.orderCreateDate}</td>
                <td>${fl.orderCompleteDate}</td>
                <td>${fl.total}</td>
                <td>${fl.status}</td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>