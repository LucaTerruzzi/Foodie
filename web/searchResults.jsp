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
    <title>Restaurant</title>
</head>
<body>
<div class="container">
    <%@include file="WEB-INF/navbar.jsp" %>
    <form method="post" action="Search">
        <input type="hidden" id="type" name="type" value="<c:out value="${type}"/>">
        <input type="hidden" id="term" name="term" value="<c:out value="${term}"/>">
        <div>
            FILTER:
        <label></label><input type="radio" name="pricefilter" value="1" <c:if test="${pricefilter == 1}">checked</c:if>>€</label>
        <label></label><input type="radio" name="pricefilter" value="2" <c:if test="${pricefilter == 2}">checked</c:if>>€€</label>
        <label></label><input type="radio" name="pricefilter" value="3" <c:if test="${pricefilter == 3}">checked</c:if>>€€€</label>
        <label></label><input type="radio" name="pricefilter" value="0" <c:if test="${pricefilter == 0}">checked</c:if>>All</label>
        </div>
        <div>
            ORDER:
        <label></label><input type="radio" name="order" value="1" <c:if test="${order == 1}">checked</c:if> <c:if test="${type == 0}">disabled</c:if>>Classifica</label>
        <label></label><input type="radio" name="order" value="2" <c:if test="${order == 2}">checked</c:if>>Alfabetico</label>
        <label></label><input type="radio" name="order" value="3" <c:if test="${order == 3}">checked</c:if>>Prezzo</label>
        </div>
        <button type="submit">Apply</button>
    </form>
    <p><c:forEach items="${results}" var="result">
    <h4>${result.getName()} - ${result.getRank()}</h4>
    <h5>${result.getCity()}, ${result.getState()}</h5>
        ${result.getRating()}
        N. reviews: ${result.getNrev()}
        Price: ${result.getRange()}
        <c:forEach items="${result.getCuisine()}" var="cuisine">
            ${cuisine},
        </c:forEach>
        <form method="POST" action="RetrieveRestaurant">
            <input type="hidden" name="id" value="${result.getId()}"/>
            <button type="submit" class="btn btn-default">Search</button>
        </form>
    </c:forEach></p>
</div>
</body>
</html>
