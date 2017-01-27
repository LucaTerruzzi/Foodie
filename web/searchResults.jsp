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
    <p><c:forEach items="${results}" var="result">
    <h4>${result.getName()} - ${result.getRank()}</h4>
    <h5>${result.getCity()}, ${result.getState()}</h5>
        ${result.getRating()}
        N. reviews: ${result.getNrev()}
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
