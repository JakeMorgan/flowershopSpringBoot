<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.accenture.be.entity.Flower"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="css/flowershop.css">
    <title>Flowers</title>
</head>

<body>
<center><h2>Basket</h2></center>
<div>
<form action="basket" method="post">
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Total sum</th>
        </tr>

        <c:forEach items="${basketList}" var="fl" varStatus="rowStatus">
            <tr>
                <td>${fl.id}</td>
                <td>${fl.flower.name}</td>
                <td>${fl.flower.price}</td>
                <td>${fl.amount}</td>
                <td>${fl.cost}</td>
                <td><button type="submit" name="${fl.id}" id="${fl.id}" method="post">Del</button></td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit" name="back" id="back" method="post">Back Index</button>
    </form>
</div>
</body>

</html>

