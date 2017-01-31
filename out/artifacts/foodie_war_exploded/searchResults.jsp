<%--
  Created by IntelliJ IDEA.
  User: Luca
  Date: 19/01/2017
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Restaurants</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
</head>
<body>
<%@include file="WEB-INF/navbar.jsp" %>
<div class="w3-container w3-padding-16">

    <form method="post" action="Search">
        <input type="hidden" id="type" name="type" value="<c:out value="${type}"/>">
        <input type="hidden" id="term" name="term" value="<c:out value="${term}"/>">
        Filter by Price
        <select name="pricefilter">
            <option value="1" <c:if test="${pricefilter == 1}">selected</c:if>>€</option>
            <option value="2" <c:if test="${pricefilter == 2}">selected</c:if>>€€</option>
            <option value="3" <c:if test="${pricefilter == 3}">selected</c:if>>€€€</option>
            <option value="0" <c:if test="${pricefilter == 0}">selected</c:if>>All</option>
        </select>
        Order by
        <select name="order">
            <option value="1" <c:if test="${order == 1}">selected</c:if> <c:if test="${type == 0}">disabled</c:if>>Classifica</option>
            <option value="2" <c:if test="${order == 2}">selected</c:if>>Alfabetico</option>
            <option value="3" <c:if test="${order == 3}">selected</c:if>>Prezzo</option>
        </select>
        <button class="w3-btn fd-light-grey"type="submit">Apply</button>
    </form>

    <div><c:forEach items="${results}" var="result">
        <div class="w3-padding-8">
            <div class="w3-card-2">
                <header class="w3-container fd-green">
                    <h4>${result.getName()} - ${result.getRank()}</h4>
                </header>
                <div class="w3-container w3-layout-cell">
                    ${result.getCity()}, ${result.getState()}<br>
                    Rating ${result.getRating()}<br>
                    N. reviews: ${result.getNrev()}<br>
                    Price: ${result.getRange()}<br>
                    Cucine: <c:forEach items="${result.getCuisine()}" var="cuisine">${cuisine} </c:forEach><br>
                    <form method="POST" action="RetrieveRestaurant">
                        <input type="hidden" name="id" value="${result.getId()}"/>
                        <button type="submit" class="w3-btn fd-light-grey">Search</button>
                    </form>
                </div>
                <div class="w3-container w3-layout-cell w3-layout-middle">
                    <img class="fd-small-image fd-dark-grey w3-round" src="pics/${result.getId()}/${result.getId()}_owner_1.jpg" alt="foto" style="max-width: 100px; max-height: 100px"/>
                </div>
                <!--footer class="w3-container fd-green">
                </footer-->
            </div>
        </div>
    </c:forEach></div>
</div>
</body>
</html>
