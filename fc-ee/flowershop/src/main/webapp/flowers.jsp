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
<div>
<form action="flowers" method="post">
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <c:forEach items="${flowers}" var="fl" varStatus="rowStatus">
            <tr>
                <td>${fl.id}</td>
                <td>${fl.name}</td>
                <td>${fl.price}</td>
                <td>${fl.quantity}</td>
                <td><button type="submit" name="${fl.id}" id="${fl.id}" method="post">Add</button></td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit" name="buy" id="buy" method="post">Buy</button>
    </form>
</div>
<button type="button" name="back" class="submit-button" onclick="history.back()">back</button>
<script type="text/javascript">
    let selectedTd;
    const table = document.querySelector('table');

    table.onclick = function (event){
        let target = event.target;
        if(target.tagName == 'TD'){
            getSelectedElement(target);
        }
    }
    function getSelectedElement(td){
    const tr = td.parentNode;
    const tds = tr.querySelectorAll('td');
    selectedTr = {
        id : tds[0].innerHTML,
        name : tds[1].innerHTML,
        price : tds[2].innerHTML,
        quantity : tds[3].innerHTML,
    }
    }
    </script>
</body>

</html>

