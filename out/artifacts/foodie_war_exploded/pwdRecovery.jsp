<%--
    Document   : register
    Created on : Jul 5, 2016, 11:08:21 AM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Password recovery</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
    <link rel="stylesheet" href="css/fdIndex.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
    <%@include file="WEB-INF/navbar.jsp" %>
    <c:choose>
        <c:when test="${param.error == 1}">
            <div class="w3-panel w3-red">Wrong email<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
    </c:choose>
    <div class="w3-container fd-text-dark-grey">
        <h2>Recupero Password</h2>
        <form role="form" class="w3-container" method="POST" action="PwdRecoverySender">
            <input class="w3-input w3-animate-input w3-margin-bottom" type="email" id="email" placeholder="Inserisci la tua email" name="email" style="width:30%">
            <button type="submit" class="fd-dark-grey w3-btn">Submit</button>
        </form>
    </div>
</body>
</html>