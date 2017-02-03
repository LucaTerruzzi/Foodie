<%--
  Created by IntelliJ IDEA.
  Authors: Luca, Riccardo, Mario
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<html>
<head>
    <title>Owned Restauranst</title>
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
    <div class="w3-container"><c:forEach items="${user.ownedRestaurants}" var="ownedRestaurant"><p>
        <h4>${ownedRestaurant.name}</h4>
        <a class="w3-btn fd-light-grey" href="RetrieveRestaurant?id=${ownedRestaurant.id}">Vai</a>
    </c:forEach></div>
</div>
</body>
</html>
