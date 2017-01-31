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
    <title>Notifications</title>
    <!--W3-CSS-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
</head>
<body>
<div>
    <%@include file="WEB-INF/navbar.jsp" %>
    <div class="w3-container"><c:forEach items="${user.notifications}" var="notification"><p>
            ${notification.text} <!--- ${notification.type}<br>
            ${notification.restaurantClaimer} claims:-->
                <br><a href="RetrieveRestaurant?id=${notification.restaurantClaimed}">Questo Ristorante</a>
    <form method="post" action="AssignRestaurant">
        <input type="hidden" name="id" value="${notification.id}">
        <button class="fd-dark-grey w3-btn" type="submit" name="dismiss" value="dismiss">Dismiss</button>
        <button  class="fd-dark-grey w3-btn" type="submit" name="accept" value="accept">Accept</button>
    </form></p>
    </c:forEach></div>
</div>
</body>
</html>
