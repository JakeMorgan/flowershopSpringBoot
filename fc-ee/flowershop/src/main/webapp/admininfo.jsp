<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.accenture.be.entity.OrderItem"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="css/flowershop.css">
<title>Order info</title>
</head>
<body>
<div style="text-align: center">
<h2>Order info</h2>
</div>
<div>
<form action="admin/orderinfo" method="post">
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Flower</th>
            <th>Amount</th>
            <th>Cost</th>
        </tr>

        <c:forEach items="${orderItemsList}" var="fl" varStatus="rowStatus">
            <tr>
                <td>${fl.id}</td>
                <td>${fl.flower.name}</td>
                <td>${fl.amount}</td>
                <td>${fl.cost}</td>
            </tr>
        </c:forEach>
    </table>
    </form>
</div>
<button type="button" name="back" class="submit-button" onclick="history.back()">Back</button>
</body>
</html>