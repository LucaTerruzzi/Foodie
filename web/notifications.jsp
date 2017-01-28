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
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<html>
<head>
    <title>Restaurant</title>
</head>
<body>
<div class="container">
    <%@include file="WEB-INF/navbar.jsp" %>
    <hr>
    <div><c:forEach items="${user.notifications}" var="notification"><p>
            ${notification.text} - ${notification.type}<br>
            ${notification.restaurantClaimer} claims: <a href="RetrieveRestaurant?id=${notification.restaurantClaimed}">THIS</a>
    <form method="post" action="AssignRestaurant">
        <input type="hidden" name="id" value="${notification.id}">
        <button type="submit" name="dismiss" value="dismiss">Dismiss</button>
        <button type="submit" name="accept" value="accept">Accept</button>
    </form></p>
    </c:forEach></div>
</div>
</body>
</html>
