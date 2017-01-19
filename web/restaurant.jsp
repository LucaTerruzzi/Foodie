<%--
  Created by IntelliJ IDEA.
  User: Luca
  Date: 19/01/2017
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="restaurant" class="it.progettoweb.data.Restaurant" scope="request" />
<html>
<head>
    <title>Restaurant</title>
</head>
<body>
<div class="container">
    <%@include file="WEB-INF/navbar.jsp" %>
    <h2><jsp:getProperty name="restaurant" property="name"/></h2>
    <img src="pics/<jsp:getProperty name="restaurant" property="id"/>/owner_1.jpg" alt="foto"/>
    <h5><jsp:getProperty name="restaurant" property="openingHours"/></h5>
    <p><jsp:getProperty name="restaurant" property="description"/></p>
    <a href="<jsp:getProperty name="restaurant" property="link"/>">Sito</a>
    <p><c:forEach items="${restaurant.cuisine}" var="cuisine">
        ${cuisine}
    </c:forEach></p>
    <p><c:choose>
        <c:when test="${restaurant.priceRange == 1}">€</c:when>
        <c:when test="${restaurant.priceRange == 2}">€€</c:when>
        <c:when test="${restaurant.priceRange == 3}">€€€</c:when>
    </c:choose></p>
    <p><jsp:getProperty name="restaurant" property="rating"/></p>
    <p><c:out value="${restaurant.location.city}"/>, <c:out value="${restaurant.location.address}"/></p>
</div>
</body>
</html>
