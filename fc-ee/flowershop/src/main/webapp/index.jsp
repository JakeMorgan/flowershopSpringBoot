<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.accenture.be.entity.Order"%>
<%@page import="com.accenture.be.entity.User"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
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
<h3>Personal Area</h3>
<form action="index" method="post">
<b><p>Login: <input type="text" name="username" size="40" value= "${user.userName}"></p></b>
<b><p>Password: <input type="text" name="password" size="40" value= "${user.password}"></p></b>
<b><p>Address: <input type="text" name="address" size="40" value= "${user.address}"></p></b>
<b><p>Phone: <input type="text" name="phone" size="40" value= "${user.phone}"></p></b>
<b><p>Money: ${user.balance}</p></b>
<br>
<button type="submit" name="save" id="save" method="post">Save</button>
<br>
<button type="submit" name="flowers" id="flowers">Flowers</button>
</form>
</div>
<div>
<br>
<form action="index">
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Address</th>
            <th>Create Date</th>
            <th>Complete Date</th>
            <th>Total</th>
            <th>Status</th>
        </tr>

        <c:forEach items="${userOrdersList}" var="fl" varStatus="rowStatus">
                    <tr>
                        <td>${fl.id}</td>
                        <td>${fl.user.address}</td>
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