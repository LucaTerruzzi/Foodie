<%--
    Document   : register
    Created on : Jul 5, 2016, 11:08:21 AM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<!DOCTYPE html>
<html>
<head>
    <title>User Info</title>
    <meta charset="utf-8">
    <!--W3-CSS-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
</head>
<body>
<%@include file="WEB-INF/navbar.jsp" %>
<c:choose>
    <c:when test="${param.error == 1}">
        <div class="w3-panel w3-red">Invalid Rating<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 2}">
        <div class="w3-panel w3-red">Invalid Title<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 3}">
        <div class="w3-panel w3-red">Invalid Description<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.message == 1}">
        <div class="w3-panel w3-green">Review posted successfully
            <span onclick="this.parentElement.style.display='none'" class="w3-closebtn">
                    <form action="RetrieveRestaurant" method="post">
                        <input type="hidden" name="id" value="${param.id}">
                        <button class="w3-btn w3-green" type="submit">&times;</button>
                    </form>
                </span>
        </div>

    </c:when>
</c:choose>

<div class="w3-container w3-padding-16" style="max-width: 50%">
    <form class="w3-container" role="form" method="POST" action="StoreReview">
        <input class="w3-input w3-margin-bottom fd-text-dark-grey" type="text" id="name" placeholder="Titolo" name="title">
        <textarea class="w3-input w3-margin-bottom fd-text-dark-grey" rows="10"  id="desc" placeholder="Corpo" name="desc"></textarea>
        <p class="fd-text-dark-grey">Voto:
            1 <input class="w3-radio" type="radio" name="rating" value="1">
            2 <input class="w3-radio" type="radio" name="rating" value="2">
            3 <input class="w3-radio" type="radio" name="rating" value="3">
            4 <input class="w3-radio" type="radio" name="rating" value="4">
            5 <input class="w3-radio" type="radio" name="rating" value="5">
        </p>
        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
        <button type="submit" class="w3-btn fd-light-grey">Submit</button>
    </form>
</div>

</body>
</html>